package com.tmjonker.land;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Land {

    private final int ROWS = 11;
    private final int COLUMNS = 11;

    private final char[][] grid;
    private final HashMap<String, Integer> humanLocation;

    public Land() {

        humanLocation = new HashMap<>();
        grid = new char[ROWS][COLUMNS];
        createDefaultGrid();
        printGrid();
    }

    // Generate symbols representing land and also generate starting locations for Goblins.
    private void createDefaultGrid() {

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                grid[i][j] = '~';
            }
        }

        generateGoblinLocations(); // Adds goblins to the starting map.
        generateTreasureLocations(); // Adds treasure chests to the starting map.
        generatePotionLocations(); // Adds potions to the map.
        generateHumanStartingLocation(); // Adds human to the starting map.
    }

    // Determines random starting locations for Goblins.
    private void generateGoblinLocations() {

        for (int i = 0; i < 10; i++) {
            int row = (int) (Math.random() * ROWS);
            int column = (int) (Math.random() * COLUMNS);
            if (grid[row][column] == 'G')
                i--;
            else {
                grid[row][column] = 'G';
            }
        }
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

    public void printGrid() {
        System.out.println();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (j == COLUMNS - 1) {
                    System.out.println(grid[i][j]);
                } else
                    System.out.print(grid[i][j] + "  ");
            }
        }
    }

    //Update human location. Return TRUE is goblin is encountered, return FALSE if no goblin has been encountered.
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

    public HashMap<String, Integer> getHumanLocation() {
        return humanLocation;
    }
}
