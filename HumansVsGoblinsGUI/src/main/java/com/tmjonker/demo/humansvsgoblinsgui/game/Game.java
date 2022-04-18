package com.tmjonker.demo.humansvsgoblinsgui.game;

import com.tmjonker.demo.humansvsgoblinsgui.accessory.Accessory;
import com.tmjonker.demo.humansvsgoblinsgui.accessory.Potion;
import com.tmjonker.demo.humansvsgoblinsgui.input.Input;
import com.tmjonker.demo.humansvsgoblinsgui.settings.Settings;
import com.tmjonker.demo.humansvsgoblinsgui.sprite.Enemy;
import com.tmjonker.demo.humansvsgoblinsgui.sprite.Player;
import com.tmjonker.demo.humansvsgoblinsgui.sprite.Sprite;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Game {

    private Random rnd = new Random();

    private Pane playfieldLayer;
    private Pane scoreLayer;

    private GridPane gridPane = new GridPane();
    private Rectangle[][] grid = new Rectangle[15][15];

    private AnimationTimer gameLoop;

    private Image playerImage;
    private Image enemyImage;
    private Image potionImage;
    private List<Image> trollImages;
    private List<Image> banditImages;

    private List<Enemy> enemies = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private List<Potion> potions = new ArrayList<>();
    private Enemy enemy;
    private Player player;

    private Input input;

    private boolean collisionEnemy = false;
    private boolean collisionPotion = false;
    private boolean endOfGame = false;

    private int score = 0;

    private Text collisionText = new Text();
    private Text scoreText = new Text();
    private TextArea instructions = new TextArea();
    private TextArea commentary = new TextArea();

    private Scene scene;

    public Game(Stage primaryStage) {
        Group root = new Group();
        VBox rootBox = new VBox(root);

        // create layers
        playfieldLayer = new Pane();
        scoreLayer = new Pane();

        // draw grassy grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {

                int row = i;
                int col = j;

                grid[i][j] = new Rectangle();
                grid[i][j].setWidth(35);
                grid[i][j].setHeight(35);
                Image img = new Image("Grass.png");
                grid[i][j].setFill(new ImagePattern(img));

                gridPane.add(grid[i][j], i, j);
                gridPane.setAlignment(Pos.CENTER);
            }
        }

        playfieldLayer.setMaxHeight(Settings.PLAYFIELD_HEIGHT);
        playfieldLayer.setMaxWidth(Settings.PLAYFIELD_WIDTH);

        // creates text for the instructions and commentary textareas.
        setUpText();

        VBox commentaryBox = new VBox(instructions, commentary);

        commentaryBox.setMaxWidth(Settings.PLAYFIELD_WIDTH);
        commentaryBox.setMaxHeight((Settings.SCENE_HEIGHT - Settings.PLAYFIELD_HEIGHT));

        rootBox.getChildren().add(commentaryBox);
        root.getChildren().add(gridPane);
        root.getChildren().add(playfieldLayer);
        root.getChildren().add(scoreLayer);

        scene = new Scene(rootBox, Settings.PLAYFIELD_WIDTH, Settings.SCENE_HEIGHT);
        scene.getStylesheets().add("style.css");

        primaryStage.setTitle("Humans Vs Goblins");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        // loads images
        loadImages();

        // creates text that indicates that a battle has been initiated.
        createCollisionLayer();
        // creates score text at top of the screen.
        createScoreLayer();
        createPlayers();

        // generates 15 enemies randomly on the map.
        for (int i = 0; i < 15; i++) {
            spawnEnemies();
        }

        // generates 5 potions randomly on the map.
        for (int i = 0; i < 5; i++) {
            spawnPotions();
        }

        // The main game loop that is responsible for all the gameplay and animation.
        gameLoop = new AnimationTimer() {

            private long sleepMs = 48_000_000;
            private long prevTime = 0;

            @Override
            public void handle(long now) {

                // loop is modified to be slower than default, so that battle text does not scroll as fast.
                if ((now - prevTime) < sleepMs) {
                    return;
                }

                prevTime = now;

                // processes input to detect key presses.
                player.processInput();

                // updates x and y coordinate of player sprite based on key presses.
                player.move();

                enemies.forEach(enemy -> enemy.move());

                // checks for collisions with enemies and potions.
                checkCollisionEnemy();
                checkCollisionPotion();

                // moves the player sprite across the screen based on changes to x and y from .move().
                player.updateUI();

                // if enemies have been defeated, then they are triggered for removal and then removed from the map.
                removeSprites(enemies);
                // if potions have been collided with, then they are removed from the map.
                removeAccessories(potions);

                // updates text on screen when enemy has been collided with to say "Battle Initiated".
                updateCollision();

                // if all enemies have been defeated, then round resets to new level.
                if (checkResetRound())
                    resetRound();
            }

        };

        // starts the main game loop.
        gameLoop.start();

    }

    // restarts game after player death.
    private void restartGame() {

        player.setHealth(Settings.PLAYER_HEALTH);
        score = 0;
        scoreText.setText(Integer.toString(score));
        gameLoop.start();
    }

    // spawns new enemies and potions after player has cleared a level.
    private void resetRound() {

        for (int i = 0; i < 15; i++) {
            spawnEnemies();
        }

        for (int i = 0; i < 5; i++) {
            spawnPotions();
        }
    }

    // loads all the image files that the game will be using.
    private void loadImages() {
        playerImage = new Image("bandit1.png");
        enemyImage = new Image("troll1.png");
        potionImage = new Image("potion.png");

        trollImages = new ArrayList<>();
        Image troll1 = new Image("troll1.png");
        Image troll2 = new Image("troll2.png");
        Image troll3 = new Image("troll3.png");
        Image troll4 = new Image("troll4.png");
        trollImages.add(troll1);
        trollImages.add(troll2);
        trollImages.add(troll3);
        trollImages.add(troll4);

        banditImages = new ArrayList<>();
        Image bandit1 = new Image("bandit1.png");
        Image bandit2 = new Image("bandit2.png");
        Image bandit3 = new Image("bandit3.png");
        Image bandit4 = new Image("bandit4.png");
        banditImages.add(bandit1);
        banditImages.add(bandit2);
        banditImages.add(bandit3);
        banditImages.add(bandit4);
    }

    // sets up the TextAreas for commentary and instructions.
    private void setUpText() {

        commentary.setEditable(false);
        commentary.setFont(Font.font(null, FontWeight.BOLD, 14));
        commentary.setWrapText(true);

        instructions.setEditable(false);
        instructions.setFont(Font.font(null, FontWeight.BOLD, 14));
        instructions.setText("WELCOME TO HUMANS VS GOBLINS!");
        instructions.appendText("\nExplore the map and do battle with goblins along the way!\nAs you approach a goblin, " +
                "the battle will automatically commence, and you will fight to the death.\nIf your health drops, grab a potion to restore health!" +
                "\nIf you die, press the space bar to respawn.\nYour score is displayed at the top of the game window.");
        instructions.setWrapText(true);
    }

    // Sets up the layer that contains the "Battle Initiated" text on the screen when you collide with an enemy.
    private void createCollisionLayer() {

        collisionText.setFont( Font.font( null, FontWeight.BOLD, 32));
        collisionText.setStroke(Color.BLACK);
        collisionText.setFill(Color.RED);

        scoreLayer.getChildren().add( collisionText);

        collisionText.setText("Battle Initiated!");
        double x = (Settings.PLAYFIELD_WIDTH - collisionText.getBoundsInLocal().getWidth()) / 2;
        double y = (Settings.PLAYFIELD_HEIGHT - collisionText.getBoundsInLocal().getHeight()) / 2;
        collisionText.relocate(x, y);
        collisionText.setText("");

        collisionText.setBoundsType(TextBoundsType.VISUAL);
    }

    // Sets up the text at the top of the screen that displays the player's score.
    private void createScoreLayer() {

        scoreText.setFont( Font.font( null, FontWeight.BOLD, 32));
        scoreText.setStroke(Color.BLACK);
        scoreText.setFill(Color.LIGHTGOLDENRODYELLOW);

        scoreLayer.getChildren().add(scoreText);

        scoreText.setText(Integer.toString(score));
        double x = (Settings.PLAYFIELD_WIDTH - scoreText.getBoundsInLocal().getWidth()) / 2;
        double y = (Settings.PLAYFIELD_HEIGHT - scoreText.getBoundsInLocal().getHeight()) / 8;
        scoreText.relocate(x, y);

        scoreText.setBoundsType(TextBoundsType.VISUAL);
    }

    // Spawns a potion in a random location and adds them to the map.
    private void spawnPotions() {

        double x = rnd.nextDouble() * (Settings.PLAYFIELD_WIDTH - potionImage.getWidth() - 50);
        double y = rnd.nextDouble() * (Settings.PLAYFIELD_HEIGHT - potionImage.getHeight() - 50);

        double health = (rnd.nextDouble() * Settings.MIN_ENEMY_HEALTH) + Settings.MIN_ENEMY_HEALTH;
        double damage = (rnd.nextDouble() * Settings.MIN_ENEMY_DAMAGE) + Settings.MIN_ENEMY_DAMAGE;

        // create a sprite
        Potion potion = new Potion(playfieldLayer, potionImage, x, y);

        // manage sprite
        potions.add(potion);
    }

    // Generates a new player character and adds them to the map.
    private void createPlayers() {

        input = new Input(scene);

        input.addListeners();

        double x = (Settings.PLAYFIELD_WIDTH - playerImage.getWidth()) / 2.0;
        double y = Settings.PLAYFIELD_HEIGHT * 0.7;
        double damage = (rnd.nextDouble() * Settings.MIN_PLAYER_DAMAGE) + Settings.MIN_PLAYER_DAMAGE;

        player = new Player(playfieldLayer, playerImage, banditImages, x, y, 0, 0, Settings.PLAYER_HEALTH,
                damage, Settings.PLAYER_SPEED, input);

        players.add(player);
    }

    // spawns an enemy in a random location and adds them to the map.
    private void spawnEnemies() {

        double x = rnd.nextDouble() * (Settings.PLAYFIELD_WIDTH - enemyImage.getWidth() - 50);
        double y = rnd.nextDouble() * (Settings.PLAYFIELD_HEIGHT - enemyImage.getHeight() - 50);

        double health = (rnd.nextDouble() * Settings.MIN_ENEMY_HEALTH) + Settings.MIN_ENEMY_HEALTH;
        double damage = (rnd.nextDouble() * Settings.MIN_ENEMY_DAMAGE) + Settings.MIN_ENEMY_DAMAGE;

        // create a sprite
        Enemy enemy = new Enemy(playfieldLayer, enemyImage, trollImages, x, y, 0, 0, health, damage);

        // manage sprite
        enemies.add(enemy);
    }

    // checks to see if a player has collided with an enemy and sets this.enemy to be the enemy that was collided with.
    private void checkCollisionEnemy() {

        collisionEnemy = false;

        for (Enemy enemy : enemies) {
            if (player.collidesWith(enemy)) {
                collisionEnemy = true;
                this.enemy = enemy;
            }
        }
    }

    // checks to see if player collides with a potion.  if so, player health increases to 100%.
    private void checkCollisionPotion() {

        collisionPotion = false;

        for (Potion potion : potions) {
            if (player.collidesWith(potion)) {
                collisionPotion = true;
                player.drinkPotion();
                potion.setRemovable(true);
                commentary.appendText("\nYou drink a potion and regain full health!");
            }
        }
    }

    // iterates through  list of sprites to determine if they are removable.  If they are removable, they get removed from map.
    private void removeSprites(  List<? extends Sprite> spriteList) {
        Iterator<? extends Sprite> iter = spriteList.iterator();
        while( iter.hasNext()) {
            Sprite sprite = iter.next();

            if(sprite.isRemovable()) {

                sprite.removeFromLayer();
                iter.remove();
                scoreText.setText(Integer.toString(++score));
            }
        }
    }

    // iterates through list of accessories to determine if they are removable. if they are, then they get removed from map.
    private void removeAccessories(  List<? extends Accessory> accessoryList) {
        Iterator<? extends Accessory> iter = accessoryList.iterator();
        while( iter.hasNext()) {
            Accessory accessory = iter.next();

            if(accessory.isRemovable()) {

                accessory.removeFromLayer();
                iter.remove();
            }
        }
    }

    // if all enemys have been defeated, then it will trigger a reset to the round.
    private boolean checkResetRound() {

        boolean reset = false;

        for (Enemy enemy : enemies) {
            if (enemy.getHealth() <= 0)
                reset = true;
            else {
                reset = false;
                break;
            }
        }

        return reset;
    }

    // updates the collisionText to display "Battle Initiated" if a collision has been detected.
    private void updateCollision() {
        if(collisionEnemy) {
            collisionText.setText("Battle Initiated!");
            battle();
        } else {
            collisionText.setText("");
        }
    }

    // contains all the battle logic that occurs when player collides with an enemy.
    private void battle() {

        double playerDiceRoll = (rnd.nextDouble() * 20);
        double enemyDiceRoll = (rnd.nextDouble() * 20);
        double hitChanceDiceRoll = (rnd.nextDouble() * 20);

        if (playerDiceRoll > enemyDiceRoll) {
            if (hitChanceDiceRoll > 10) {
                enemy.getDamagedBy(player);
                commentary.appendText("\nYou deal " + (int) player.getDamage() + " damage to the goblin!");
            }
        } else {
            if (hitChanceDiceRoll > 10) {
                player.getDamagedBy(enemy);
                commentary.appendText("\nThe goblin deals " + (int) enemy.getDamage() + " damage to you!\n" +
                        "You have " + (int) player.getHealth() + " health remaining...");
            }
        }
        // if enemy health drops to 0 or below, flag enemy for removal.
        if (enemy.getHealth() <= 0) {
            enemy.remove();
        }

        if (player.getHealth() <= 0) {
            gameLoop.stop();
            collisionText.setText("You have died!");
            commentary.appendText("\nYou have died...");
            endOfGame = true;
            Thread endOfGameChecker = new Thread(new GameRestarter());
            endOfGameChecker.start();
        }
    }

    /*
    Runnable class that checks to see if space bar gets hit after the player dies and endOfGame gets triggered.  If space
    bar gets hit, then the game restarts and the player is respawned.
     */
    public class GameRestarter implements Runnable {

        @Override
        public void run() {

            while (endOfGame) {
                scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                    if (event.getCode() == KeyCode.SPACE) {
                        endOfGame = false;
                        restartGame();
                    }
                });
            }
        }
    }
}