package com.tmjonker;

import java.util.Scanner;

public class GameController {

    private Land land;
    private Scanner scanner;
    private Human player;
    private BattleHandler battleHandler;

    public GameController() {

        startGame(); // constructor automatically initializes gameplay.
    }

    public void startGame() {
        scanner = new Scanner(System.in);
        showTitleScreen();
        player = generateHuman();
        land = new Land();
        battleHandler = new BattleHandler(scanner);
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
        System.out.println("Treasure is marked on the map with a T.");
    }

    private void promptForDirection() {
        System.out.println("\nWhich direction would you like to go, " + player.getName() + "?");
        System.out.println("(N, S, E, W, or Q to quit)\n");
    }

    // Main game loop
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

            char interactionEncountered = movePlayer(userInput);

            if (interactionEncountered == 'G') {
                land.printGrid();
                System.out.println("\nYou have encountered a goblin!");
                System.out.println("Let the battle commence!\n");

                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                battleHandler.commenceBattle(player);
                land.printGrid();
                userInput = ""; // resets user input to restart the loop.
            } else if (interactionEncountered == 'T') {
                land.printGrid();
                System.out.println("\nYou have found a treasure chest!");
                System.out.println("Let's see what's inside...\n");

                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                new Treasure().collectTreasure(player);

                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                land.printGrid();
                userInput = ""; // resets user input to restart the loop.
            } else {
                land.printGrid();
                userInput = ""; // resets user input to restart the loop.
            }
        }
    }

    // checks to see if move will be out of bounds, if not moves player according to selection.
    private char movePlayer(String direction) {

        int x = player.getX();
        int y = player.getY();
        char battle = 'N'; // defaults to 'N' which stands for NOTHING.

        switch (direction) {
            case "n" -> {
                if (x-1 >= 0) {
                    battle = land.updateHumanLocation(x, y, x-1, y);
                    player.setX(land.getHumanLocation().get("x"));
                    player.setY(land.getHumanLocation().get("y"));
                }
            }
            case "s" -> {
                if (x+1 < 11) {
                    battle = land.updateHumanLocation(x, y, x+1, y);
                    player.setX(land.getHumanLocation().get("x"));
                    player.setY(land.getHumanLocation().get("y"));
                }
            }
            case "e" -> {
                if (y+1 < 11) {
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

    // Generates a new human player.
    private Human generateHuman() {

        String name = "";

        while (name.equals("")) {
            try {
                name = scanner.nextLine();

            } catch (Exception e) {
                System.out.println("A fatal error has occurred.  Game will now restart.");
                name = "q";
                startGame();
            }
        }
        return new Human(name);
    }
}