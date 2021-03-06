package com.tmjonker.gamecontroller;

import com.tmjonker.battlehandler.BattleHandler;
import com.tmjonker.humanoid.Human;
import com.tmjonker.land.Land;
import com.tmjonker.treasure.Treasure;

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
        showInstructions();
        land = new Land();
        battleHandler = new BattleHandler(scanner);
        player.setX(land.getHumanLocation().get("x"));
        player.setY(land.getHumanLocation().get("y"));
        gameLoop();
    }

    private void showTitleScreen() {
        System.out.println("\n" +
                "██╗  ██╗██╗   ██╗███╗   ███╗ █████╗ ███╗   ██╗███████╗    ██╗   ██╗███████╗\n" +
                "██║  ██║██║   ██║████╗ ████║██╔══██╗████╗  ██║██╔════╝    ██║   ██║██╔════╝\n" +
                "███████║██║   ██║██╔████╔██║███████║██╔██╗ ██║███████╗    ██║   ██║███████╗\n" +
                "██╔══██║██║   ██║██║╚██╔╝██║██╔══██║██║╚██╗██║╚════██║    ╚██╗ ██╔╝╚════██║\n" +
                "██║  ██║╚██████╔╝██║ ╚═╝ ██║██║  ██║██║ ╚████║███████║     ╚████╔╝ ███████║\n" +
                "╚═╝  ╚═╝ ╚═════╝ ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚══════╝      ╚═══╝  ╚══════╝\n" +
                "                                                                           \n" +
                " ██████╗  ██████╗ ██████╗ ██╗     ██╗███╗   ██╗███████╗                    \n" +
                "██╔════╝ ██╔═══██╗██╔══██╗██║     ██║████╗  ██║██╔════╝                    \n" +
                "██║  ███╗██║   ██║██████╔╝██║     ██║██╔██╗ ██║███████╗                    \n" +
                "██║   ██║██║   ██║██╔══██╗██║     ██║██║╚██╗██║╚════██║                    \n" +
                "╚██████╔╝╚██████╔╝██████╔╝███████╗██║██║ ╚████║███████║   ");
        System.out.println("\nPlease choose a name for your character: \n");
    }

    private void showInstructions() {
        System.out.println("\nLet's get ready to slay some goblins!");
        System.out.println("Your goal is to eliminate all goblins on the map.\n");
        System.out.println("Your position is marked on the map with an H.");
        System.out.println("Goblins are marked on the map with a G.");
        System.out.println("Treasure is marked on the map with a T.");
        System.out.println("Potions are marked on the map with a P.");
        sleep(10000);
    }

    private void promptForDirection() {
        System.out.println("\nWhich direction would you like to go, " + player.getName() + "?");
        System.out.println("(N, S, E, W, or Q to quit)\n");
    }

    // Main game loop
    private void gameLoop() {
        String userInput = "";
        while (true) {
            while (!userInput.matches("[nNsSeEwWqQ]")) {
                promptForDirection();
                try {
                    userInput = scanner.nextLine().toLowerCase();
                } catch (Exception e) {
                    userInput = "q";
                    System.out.println("Invalid input.  Game will now exit.");
                    System.exit(0);
                }
            }

            if (userInput.equals("q"))
                break;

            char interactionEncountered = movePlayer(userInput);

            switch (interactionEncountered) {
                case 'G' -> {
                    land.printGrid();
                    System.out.println("\nYou have encountered a goblin!");
                    System.out.println("Let the battle commence!\n");

                    sleep(2000);

                    // returns an int[] array containing goblin coordinates if player decides to flee.
                    // if player does not flee, then it returns null.
                    int[] coordinates = battleHandler.commenceBattle(player);

                    // if coordinates != null then the goblin is added back onto the map and player is moved to
                    // previous known location.
                    if (coordinates != null) {
                        land.addGoblin(coordinates);
                        movePlayer(player.getPx(), player.getPy());
                    }

                    if (!land.printGrid()) {

                        sleep(2000);

                        System.out.println("\nCongratulations, " + player.getName() + ", you have beaten the game by defeating all goblins!");
                        System.exit(0);
                    }

                    userInput = ""; // resets user input to restart the loop.
                }

                case 'T' -> {
                    land.printGrid();
                    System.out.println("\nYou have found a treasure chest!");
                    System.out.println("Let's see what's inside...\n");

                    sleep(2000);

                    new Treasure().collectTreasure(player);

                    sleep(2000);

                    land.printGrid();
                    userInput = ""; // resets user input to restart the loop.
                }

                case 'P' -> {
                    land.printGrid();
                    player.setHealth(100);
                    System.out.println("\nYou have found a POTION!");
                    System.out.println("You health has increased to " + player.getHealth() + "!\n");

                    sleep(2000);

                    land.printGrid();
                    userInput = ""; // resets user input to restart the loop.
                }

                case 'N' -> {
                    land.printGrid();
                    userInput = ""; // resets user input to restart the loop.
                }
            }
        }
    }

    // Delays printing to console by 2 seconds.
    public static void sleep(int millis) {

        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // checks to see if move will be out of bounds, if not moves player according to selection.
    private char movePlayer(String direction) {

        int x = player.getX();
        int y = player.getY();
        char collision = 'N'; // defaults to 'N' which stands for NOTHING. Returns P for potion, G for goblin,
        // or T for treasure.

        switch (direction) {
            case "n" -> { // moves player north on map.
                if (x-1 >= 0) {
                    collision = land.updateHumanLocation(x, y, x-1, y);
                    player.setX(land.getHumanLocation().get("x"));
                    player.setY(land.getHumanLocation().get("y"));
                }
            }
            case "s" -> { // moves player south on map.
                if (x+1 < 11) {
                    collision = land.updateHumanLocation(x, y, x+1, y);
                    player.setX(land.getHumanLocation().get("x"));
                    player.setY(land.getHumanLocation().get("y"));
                }
            }
            case "e" -> { // moves player east on map.
                if (y+1 < 11) {
                    collision = land.updateHumanLocation(x, y, x, y+1);
                    player.setX(land.getHumanLocation().get("x"));
                    player.setY(land.getHumanLocation().get("y"));
                }
            }
            case "w" -> { // moves player west on map.
                if (y-1 >= 0) {
                    collision = land.updateHumanLocation(x, y, x, y-1);
                    player.setX(land.getHumanLocation().get("x"));
                    player.setY(land.getHumanLocation().get("y"));
                }
            }
        }

        return collision;
    }

    // overloaded method to move player to a new location based on x and y inputs.
    private void movePlayer(int x, int y) {

        land.updateHumanLocation(x, y);
        player.setX(x);
        player.setY(y);
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