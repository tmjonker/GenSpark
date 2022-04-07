package com.tmjonker;

import java.util.ArrayList;
import java.util.HashMap;

public class HangMan {

    private final WordGenerator wordGenerator;
    private String word;
    private int numberOfGuesses;
    private HashMap<Integer, Integer> letterMap = new HashMap<>();

    public HangMan() {

        ArrayList<String> wordList = new ArrayList<>();
        wordList.add("elephant");
        wordList.add("dog");
        wordList.add("ketchup");
        wordList.add("barstool");
        wordGenerator = new WordGenerator(wordList);
        word = wordGenerator.generateWord();
        numberOfGuesses = 0;

        drawGallows();
        drawLetterField();
    }

    public HangMan(ArrayList<String> wordList) {

        wordGenerator = new WordGenerator(wordList);
        word = wordGenerator.generateWord();
        numberOfGuesses = 0;

        drawGallows();
        drawLetterField();
    }

    public void drawGallows() {
        System.out.println("H A N G M A N");
        System.out.println("  +---+");
        System.out.println("      |");
        System.out.println("      |");
        System.out.println("      |");
        System.out.println("      |");
        System.out.println("      |");
        System.out.println("      |");
        System.out.println("    =====");
        System.out.println("Missed Letters:\n ");
    }

    public void drawHead() {
        System.out.println("  +---+");
        System.out.println("  O   |");
        System.out.println("      |");
        System.out.println("      |");
        System.out.println("      |");
        System.out.println("      |");
        System.out.println("      |");
        System.out.println("    =====");
        System.out.println("Missed Letters:\n ");
    }

    public void drawChest() {
        System.out.println("  +---+");
        System.out.println("  O   |");
        System.out.println("  |   |");
        System.out.println("      |");
        System.out.println("      |");
        System.out.println("      |");
        System.out.println("      |");
        System.out.println("    =====");
        System.out.println("Missed Letters:\n ");
    }

    public void drawRightArm() {
        System.out.println("  +---+");
        System.out.println("  O   |");
        System.out.println("  | \\ |");
        System.out.println("      |");
        System.out.println("      |");
        System.out.println("      |");
        System.out.println("      |");
        System.out.println("    =====");
        System.out.println("Missed Letters:\n ");
    }

    public void drawLeftArm() {
        System.out.println("  +---+");
        System.out.println("  O   |");
        System.out.println("/ | \\ |");
        System.out.println("      |");
        System.out.println("      |");
        System.out.println("      |");
        System.out.println("      |");
        System.out.println("    =====");
        System.out.println("Missed Letters: \n");
    }

    public void drawGroin() {

        System.out.println("  +---+");
        System.out.println("  O   |");
        System.out.println("/ | \\ |");
        System.out.println("  |   |");
        System.out.println("      |");
        System.out.println("      |");
        System.out.println("      |");
        System.out.println("    =====");
        System.out.println("Missed Letters:\n ");
    }

    public void drawRightLeg() {
        System.out.println("    +---+");
        System.out.println("    O   |");
        System.out.println("  / | \\ |");
        System.out.println("    |   |");
        System.out.println("     \\  |");
        System.out.println("        |");
        System.out.println("        |");
        System.out.println("      =====");
        System.out.println("Missed Letters: \n");
    }

    public void drawLeftLeg() {

        System.out.println("    +---+");
        System.out.println("    O   |");
        System.out.println("  / | \\ |");
        System.out.println("    |   |");
        System.out.println("   / \\  |");
        System.out.println("        |");
        System.out.println("        |");
        System.out.println("      =====");
    }

    public void drawLetterField() {

        int numberOfDashes = word.length();

        for (int i = 0; i < numberOfDashes; i++) {
            if (i == numberOfDashes - 1)
                System.out.println("_\n");
            else
                System.out.print("_");
        }

        System.out.println("Guess a letter!");
    }

    public boolean updateLetterField(String guess) {

        for (int i = 0; i < word.length(); i++) {
            String subString = word.substring(i, i+1);
            if (subString.equals(guess)) {
                if (!letterMap.containsKey(i))
                    letterMap.put(i, i+1);
                else {
                    System.out.println("You have already guess that letter. Choose again.");
                    return false;
                }
            }

        }

        for (int i = 0; i < word.length(); i++) {
            if (letterMap.containsKey(i)) {
                System.out.print(word.substring(i, i+1));
            } else {
                System.out.print("_");
            }
        }

        if (letterMap.size() != word.length())
            System.out.println("\n\nGuess a letter!");
        else
            System.out.println("\n\nYes! The secret word is \"" + word + "\"! you have won!");

        return letterMap.size() == word.length();
    }

    public boolean updateHangMan(String guess) {

        if (!word.contains(guess)) {

            switch (++numberOfGuesses) {
                case 1 -> {
                    drawHead();
                    System.out.println("\n\nGuess a letter!");
                }
                case 2 -> {
                    drawChest();
                    System.out.println("\n\nGuess a letter!");
                }
                case 3 -> {
                    drawRightArm();
                    System.out.println("\n\nGuess a letter!");
                }
                case 4 -> {
                    drawLeftArm();
                    System.out.println("\n\nGuess a letter!");
                }
                case 5 -> {
                    drawGroin();
                    System.out.println("\n\nGuess a letter!");
                }
                case 6 -> {
                    drawRightLeg();
                    System.out.println("\n\nGuess a letter!");
                }
                case 7 -> {
                    drawLeftLeg();
                    System.out.println("\nYou lose!  The man has been hung.");
                }
            }
            return false;
        } else {
            return updateLetterField(guess);
        }
    }

    public String getWord() {
        return word;
    }

    public int getNumberOfGuesses() {
        return numberOfGuesses;
    }
}
