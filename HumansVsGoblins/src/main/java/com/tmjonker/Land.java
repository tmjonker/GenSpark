package com.tmjonker;

import java.util.HashMap;

public class Land {

    private final int ROWS = 11;
    private final int COLUMNS = 11;

    private char[][] grid;
    private HashMap<Integer, Integer> goblinLocations;
    private HashMap<String, Integer> humanLocation;

    public Land() {

        goblinLocations = new HashMap<>();
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
        generateHumanStartingLocation(); // Adds human to the starting map.
    }

    // Determines random starting locations for Goblins.
    private void generateGoblinLocations() {

        for (int i = 0; i < 10; i++) {
            int row = (int) (Math.random() * ROWS);
            int column = (int) (Math.random() * COLUMNS);
            grid[row][column] = 'G';
            goblinLocations.put(row, column); // Saves goblin starting locations.
        }
    }

    private void generateHumanStartingLocation() {

        int row = (int) (Math.random() * ROWS);
        int column = (int) (Math.random() * COLUMNS);
        grid[row][column] = 'H';
        humanLocation.put("x", row);
        humanLocation.put("y", column);
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
    public boolean updateHumanLocation(int startX, int startY, int endX, int endY) {

        boolean goblinEncountered = false;

        goblinEncountered = grid[endX][endY] == 'G';

        grid[endX][endY] = 'H';
        grid[startX][startY] = '~';
        humanLocation.replace("x", startX, endX);
        humanLocation.replace("y", startY, endY);

        return goblinEncountered;
    }

    public HashMap<Integer, Integer> getGoblinLocations() {
        return goblinLocations;
    }

    public HashMap<String, Integer> getHumanLocation() {
        return humanLocation;
    }
}
