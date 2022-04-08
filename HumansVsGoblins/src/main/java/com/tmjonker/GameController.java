package com.tmjonker;

import java.util.Scanner;


public class GameController {

    private Land land;
    private Scanner scanner;
    private Human player;

    public GameController() {

        startGame();
    }

    public void startGame() {
        scanner = new Scanner(System.in);
        showTitleScreen();
        player = generateHuman();
        land = new Land();
        player.setX(land.getHumanLocation().get("x"));
        player.setY(land.getHumanLocation().get("y"));
        showInstructions();
        gameLoop();
    }

    private void showTitleScreen() {
        System.out.println("\nWelcome to HUMANS VS. GOBLINS!");
        System.out.println("Please choose a name for your character: \n");
    }

    private void showInstructions() {
        System.out.println("\nYour position is marked on the map with an H.");
        System.out.println("Goblins are marked on the map with a G.");
    }

    private void promptForDirection() {
        System.out.println("\nWhich direction would you like to go, " + player.getName() + "?");
        System.out.println("(N, S, E, W, or Q to quit)\n");
    }

    private void gameLoop() {
        String userInput = "";
        while (!userInput.equals("Q") && !userInput.equals("q")) {
            while (!userInput.matches("[nNsSeEwWqQ]")) {
                promptForDirection();
                try {
                    userInput = scanner.nextLine().toLowerCase();
                } catch (Exception e) {
                    userInput = "q";
                    System.out.println("Invalid input.  Game will now restart.");
                    startGame();
                }
            }
            if (movePlayer(userInput)) {
                land.printGrid();
                System.out.println("\nYou have encountered a goblin!");
                System.out.println("Let the battle commence!\n");
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                commenceBattle();
            } else {
                land.printGrid();
                userInput = "";
            }
        }
    }

    private void commenceBattle() {

        Goblin goblin = new Goblin();

        while (goblin.getHealth() > 0 && player.getHealth() > 0) {

            int humanRoll = (int) (Math.random() * 20) + 1;
            int goblinRoll = (int) (Math.random() * 20) + 1;
            int hitPercentage = (int) (Math.random() * 20) + 1;
            int goblinStartingHealth = goblin.getHealth();
            int humanStartingHealth = player.getHealth();

            if (humanRoll > goblinRoll) {
                if ((hitPercentage * 5) > 30) {
                    System.out.println("\nYou try to attack " + goblin.getName() + ", ");
                    System.out.println("AND YOU'RE SUCCESSFUL!");

                    goblin = (Goblin) player.attack(goblin);
                    int damageDealt = goblinStartingHealth - goblin.getHealth();

                    System.out.println("You deal " + damageDealt + " damage to " + goblin.getName() + ".");
                    System.out.println(goblin.getName() + " has " + goblin.getHealth() + " health remaining.\n");
                    try {
                        Thread.sleep(2000);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("\nYou try to attack " + goblin.getName() + ".");
                    System.out.println("but you MISS!");
                    System.out.println(goblin.getName() + " rejoices.\n");
                    try {
                        Thread.sleep(2000);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                if ((hitPercentage * 5) > 50) {
                    System.out.println("\nThe goblin tries to attack you, ");
                    System.out.println("AND THEY'RE SUCCESSFUL!");

                    player = (Human) goblin.attack(player);
                    int damageDealt = humanStartingHealth - player.getHealth();

                    System.out.println("They deal " + damageDealt + " damage to you.");
                    System.out.println("You have " + player.getHealth() + " health remaining.\n");
                    try {
                        Thread.sleep(2000);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("\n" + goblin.getName() + " tries to attack you, ");
                    System.out.println("but they MISS!");
                    System.out.println("You rejoice!\n");
                    try {
                        Thread.sleep(2000);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (!fleeOrBattle()) {
                System.out.println("You turn and run away.");
                System.out.println(player.getName() + " lives to fight another day!\n");
                break;
            }
        }

     // Need to insert code to notify user that they died or that they killed the goblin.
    }

    private boolean fleeOrBattle() {
        System.out.println("Fight or flee?\n");

        String battleChoice = "";

        while (!battleChoice.equals("flee") && !battleChoice.equals("fight"))
        try {
            battleChoice = scanner.nextLine().toLowerCase();
        } catch (Exception e){
            e.printStackTrace();
        }

        return !battleChoice.equals("flee");
    }

    private boolean movePlayer(String direction) {

        int x = player.getX();
        int y = player.getY();
        boolean battle = false;

        switch (direction) {
            case "n" -> {
                if (x-1 >= 0) {
                    battle = land.updateHumanLocation(x, y, x-1, y);
                    player.setX(land.getHumanLocation().get("x"));
                    player.setY(land.getHumanLocation().get("y"));
                }
            }
            case "s" -> {
                if (x+1 <= 11) {
                    battle = land.updateHumanLocation(x, y, x+1, y);
                    player.setX(land.getHumanLocation().get("x"));
                    player.setY(land.getHumanLocation().get("y"));
                }
            }
            case "e" -> {
                if (y+1 <= 11) {
                    battle = land.updateHumanLocation(x, y, x, y+1);
                    player.setX(land.getHumanLocation().get("x"));
                    player.setY(land.getHumanLocation().get("y"));
                }
            }
            case "w" -> {
                if (y-1 >= 0) {
                    battle = land.updateHumanLocation(x, y, x, y-1);
                    player.setX(land.getHumanLocation().get("x"));
                    player.setY(land.getHumanLocation().get("y"));
                }
            }
        }

        return battle;
    }

    private Human generateHuman() {

        String name = "";

        while (name.equals("")) {
            try {
                name = scanner.nextLine();

            } catch (Exception e) {
                System.out.println("A fatal error has occurred.  Game will now restart.");
                startGame();
            }
        }
        return new Human(name);
    }
}