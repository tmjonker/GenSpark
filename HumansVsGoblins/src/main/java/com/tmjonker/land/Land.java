package com.tmjonker.land;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Land {

    private final int ROWS = 11;
    private final int COLUMNS = 11;

    private final char[][] grid;
    private final HashMap<String, Integer> humanLocation;
    private int numGoblins = 10;

    public Land() {

        humanLocation = new HashMap<>();
        grid = new char[ROWS][COLUMNS];
        createDefaultGrid();
        generateGoblinLocations(); // Adds goblins to the starting map.
        generateTreasureLocations(); // Adds treasure chests to the starting map.
        generatePotionLocations(); // Adds potions to the map.
        generateHumanStartingLocation(); // Adds human to the starting map.
        printGrid();
    }

    // Generate symbols representing land and also generate starting locations for Goblins.
    private void createDefaultGrid() {

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                grid[i][j] = '~';
            }
        }
    }

    // Determines random starting locations for Goblins.
    private void generateGoblinLocations() {

        for (int i = 0; i < numGoblins; i++) {
            int row = (int) (Math.random() * ROWS);
            int column = (int) (Math.random() * COLUMNS);
            if (grid[row][column] == 'G')
                i--;
            else {
                grid[row][column] = 'G';
            }
        }
    }

    public void addGoblin(int[] coordinates) {

        grid[coordinates[0]][coordinates[1]] = 'G';
    }

    private void generateTreasureLocations() {

        for (int i = 0; i < 10; i++) {
            int row = (int) (Math.random() * ROWS);
            int column = (int) (Math.random() * COLUMNS);

            if (grid[row][column] == 'G' || grid[row][column] == 'T') {
                i--;
            } else {
                grid[row][column] = 'T';
            }
        }
    }

    private void generatePotionLocations() {
        for (int i = 0; i < 3; i++) {
            int row = (int) (Math.random() * ROWS);
            int column = (int) (Math.random() * COLUMNS);

            if (grid[row][column] == 'G' || grid[row][column] == 'T' || grid[row][column] == 'P') {
                i--;
            } else {
                grid[row][column] = 'P';
            }
        }
    }

    private void generateHumanStartingLocation() {

        for (int i = 0; i < 1; i++) {
            int row = (int) (Math.random() * ROWS);
            int column = (int) (Math.random() * COLUMNS);

            if (grid[row][column] != 'T' && grid[row][column] != 'G') {
                grid[row][column] = 'H';
                humanLocation.put("x", row);
                humanLocation.put("y", column);
            } else {
                i--;
            }
        }
    }

    // prints the game grid/map to console.  returns TRUE if goblins are still alive on map, FALSE if all goblins have
    // been cleared.
    public boolean printGrid() {
        System.out.println();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (j == COLUMNS - 1) {
                    System.out.println(grid[i][j]);
                } else
                    System.out.print(grid[i][j] + "  ");
            }
        }

        return goblinsStillAlive();
    }

    //Update human location. Return TRUE is goblin, potion, or treasure is encountered, return FALSE if no goblin
    // has been encountered.
    public char updateHumanLocation(int startX, int startY, int endX, int endY) {

        char interactionEncountered = 'N'; // Default is N, which stands for Nothing.

        if (grid[endX][endY] == 'T' || grid[endX][endY] == 'G' || grid[endX][endY] == 'P')
            interactionEncountered = grid[endX][endY];

        grid[endX][endY] = 'H';
        grid[startX][startY] = '~';
        humanLocation.replace("x", startX, endX);
        humanLocation.replace("y", startY, endY);

        return interactionEncountered;
    }

    // overloaded method to move player based on simple x and y coordinates.  used to return player to previous known location
    // so map checks are not necessary.
    public void updateHumanLocation(int x, int y) {

        grid[x][y] = 'H';

        humanLocation.replace("x", x);
        humanLocation.replace("y", y);
    }

    // checks entire map to see if there are any goblins still alive.
    public boolean goblinsStillAlive() {

        boolean goblinsAlive = false;

        outer:
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (grid[i][j] == 'G') {
                    goblinsAlive = true;
                    break outer;
                }
            }
        }

        return goblinsAlive;
    }

    // returns human location on map.
    public HashMap<String, Integer> getHumanLocation() {
        return humanLocation;
    }
}
