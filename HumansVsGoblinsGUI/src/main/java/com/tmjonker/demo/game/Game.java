package com.tmjonker.demo.game;

import com.tmjonker.demo.game.accessory.Accessory;
import com.tmjonker.demo.game.accessory.Potion;
import com.tmjonker.demo.game.input.Input;
import com.tmjonker.demo.game.settings.Settings;
import com.tmjonker.demo.game.sprite.Enemy;
import com.tmjonker.demo.game.sprite.Player;
import com.tmjonker.demo.game.sprite.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
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

public class Game extends Application {

    Random rnd = new Random();

    Pane playfieldLayer;
    Pane scoreLayer;

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

    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();
        VBox rootBox = new VBox(root);

        // create layers
        playfieldLayer = new Pane();
        scoreLayer = new Pane();

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

        loadGame();

        createCollisionLayer();
        createScoreLayer();
        createPlayers();

        for (int i = 0; i < 15; i++) {
            spawnEnemies();
        }

        for (int i = 0; i < 5; i++) {
            spawnPotions();
        }

        gameLoop = new AnimationTimer() {

            private long sleepMs = 48_000_000;
            private long prevTime = 0;

            @Override
            public void handle(long now) {

                if ((now - prevTime) < sleepMs) {
                    return;
                }

                prevTime = now;

                player.processInput();

                player.move();

                enemies.forEach(enemy -> enemy.move());

                checkCollisionEnemy();
                checkCollisionPotion();

                player.updateUI();

                removeSprites(enemies);
                removeAccessories(potions);

                updateCollision();

                if (checkResetRound())
                    resetRound();
            }

        };
        gameLoop.start();

    }

    private void restartGame() {

        player.setHealth(Settings.PLAYER_HEALTH);
        score = 0;
        scoreText.setText(Integer.toString(score));
        gameLoop.start();
    }

    private void resetRound() {

        for (int i = 0; i < 15; i++) {
            spawnEnemies();
        }

        for (int i = 0; i < 5; i++) {
            spawnPotions();
        }
    }

    private void loadGame() {
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

    private void spawnPotions() {

        double x = rnd.nextDouble() * (Settings.PLAYFIELD_WIDTH - potionImage.getWidth() - 50);
        double y = rnd.nextDouble() * (Settings.PLAYFIELD_HEIGHT - potionImage.getHeight() - 50);

        double health = (rnd.nextDouble() * Settings.MIN_ENEMY_HEALTH) + Settings.MIN_ENEMY_HEALTH;
        double damage = (rnd.nextDouble() * Settings.MIN_ENEMY_DAMAGE) + Settings.MIN_ENEMY_DAMAGE;

        // create a sprite
        Potion potion = new Potion(playfieldLayer, potionImage, x, y, 0);

        // manage sprite
        potions.add(potion);
    }

    private void createPlayers() {

        input = new Input(scene);

        input.addListeners();

        double x = (Settings.PLAYFIELD_WIDTH - playerImage.getWidth()) / 2.0;
        double y = Settings.PLAYFIELD_HEIGHT * 0.7;
        double damage = (rnd.nextDouble() * Settings.MIN_PLAYER_DAMAGE) + Settings.MIN_PLAYER_DAMAGE;

        player = new Player(playfieldLayer, playerImage, banditImages, x, y, 0, 0, 0, 0, Settings.PLAYER_HEALTH,
                damage, Settings.PLAYER_SPEED, input);

        players.add(player);
    }

    private void spawnEnemies() {

        double x = rnd.nextDouble() * (Settings.PLAYFIELD_WIDTH - enemyImage.getWidth() - 50);
        double y = rnd.nextDouble() * (Settings.PLAYFIELD_HEIGHT - enemyImage.getHeight() - 50);

        double health = (rnd.nextDouble() * Settings.MIN_ENEMY_HEALTH) + Settings.MIN_ENEMY_HEALTH;
        double damage = (rnd.nextDouble() * Settings.MIN_ENEMY_DAMAGE) + Settings.MIN_ENEMY_DAMAGE;

        // create a sprite
        Enemy enemy = new Enemy(playfieldLayer, enemyImage, trollImages, x, y, 0, 0, 0, 0, health, damage);

        // manage sprite
        enemies.add(enemy);
    }

    private void checkCollisionEnemy() {

        collisionEnemy = false;

        for (Enemy enemy : enemies) {
            if (player.collidesWith(enemy)) {
                collisionEnemy = true;
                this.enemy = enemy;
            }
        }
    }

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

    private void updateCollision() {
        if(collisionEnemy) {
            collisionText.setText("Battle Initiated!");
            battle();
        } else {
            collisionText.setText("");
        }
    }

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

    public static void main(String[] args) {
        launch(args);
    }

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