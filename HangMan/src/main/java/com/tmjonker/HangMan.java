package com.tmjonker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

// Main game class.. contains most of the base game logic.
public class HangMan {

    private static GallowsDrawer gallowsDrawer;
    private static LetterField letterField;
    private static WordGenerator wordGenerator;
    private static int numberOfGuesses;
    private static ArrayList<String> missedLetters = new ArrayList<>();
    private static int numberOfWins = 0;
    private static String name = "";
    private static String word = "";

    public HangMan() {

    }

    public static void startGame() {

        gallowsDrawer = new GallowsDrawer();
        wordGenerator = new WordGenerator();
        word = wordGenerator.generateWord();
        letterField = new LetterField(word);
        missedLetters.clear();
        numberOfGuesses = 0;
        gallowsDrawer.drawGallows();
        letterField.drawLetterField();
    }

    public static void startGame(ArrayList<String> wordList) {

        gallowsDrawer = new GallowsDrawer();
        wordGenerator = new WordGenerator(wordList);
        word = wordGenerator.generateWord();
        letterField = new LetterField(word);
        missedLetters.clear();
        numberOfGuesses = 0;
        gallowsDrawer.drawGallows();
        letterField.drawLetterField();
    }

    // victory method that declares the player to be a winner.
    public static void victory() {

        numberOfWins++;
        System.out.println("\n\nYes! The secret word is \"" + word + "\"! you have won!");

        while (name.equals("")) {
            System.out.println("Please enter your name: ");
            Scanner scanner = new Scanner(System.in);
            name = scanner.nextLine();
        }

        addCheckHighScore();
    }

    // Loads High Score file, writes score to high score list, and reads and sorts the high score list.
    private static void addCheckHighScore() {

        String highScoreFileString = "";
        try {
            // Change path to reflect the Absolute path for your highscore.txt file.
            File highScoreFile = new File("src/main/resources/highscore.txt");
            FileWriter fileWriter = new FileWriter(highScoreFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(name + "-" + numberOfWins + "/");
            bufferedWriter.close();
            fileWriter.close();
            highScoreFileString = Files.readString(highScoreFile.toPath(), StandardCharsets.UTF_8);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        // split high score string by / character to separate score entries in file.
        String[] highScores = highScoreFileString.split("/");
        List<String> list = Arrays.stream(highScores)
                .filter(s -> Integer.parseInt(s.substring(s.indexOf("-") + 1, s.length())) >= numberOfWins).toList();

        // Sorts High Score List based on number of wins.
        ArrayList<String> highScoreList = new ArrayList<>(list);
        highScoreList.sort((s1, s2) -> s1.substring(s1.indexOf("-") + 1,
                s1.length()).compareTo(s2.substring(s2.indexOf("-") + 1, s2.length())));
        Collections.reverse(highScoreList);

        int highestScoreOnFile = 0;

        if (highScoreList.size() > 0) {
            String hs = highScoreList.get(0);  // Highest Score in the highscores.txt file.
            highestScoreOnFile = Integer.parseInt(hs.substring(hs.indexOf('-') + 1, hs.length()));
        }

        if (numberOfWins >= highestScoreOnFile) {
            System.out.println("\nCongratulations, you have the high score with " + numberOfWins + " wins!\n");
        } else {
            System.out.println("\nYou do not have the current high score!\n");
            System.out.print("High Score: ");
            System.out.println(highScoreList.get(0));
        }
    }
    // Updates hangman drawing based on quality of player's guess.
    public static boolean updateHangMan(String guess) {

        if (!word.contains(guess)) { //if game word does not contain guess.

            if (!missedLetters.contains(guess)) { // If user hasn't made this guess yet, then add it to missedLetters
                missedLetters.add(guess);         // & update hangman art

                switch (++numberOfGuesses) {
                    case 1 -> {
                        gallowsDrawer.drawHead();
                        System.out.print("Missed Letters: ");
                        updateMissedLetters(); // write out the missed letters on the screen.
                        System.out.println("\n");
                        letterField.updateLetterField();
                        System.out.println("\n\nGuess a letter!");
                    }
                    case 2 -> {
                        gallowsDrawer.drawChest();
                        System.out.print("Missed Letters: ");
                        updateMissedLetters();
                        System.out.println("\n");
                        letterField.updateLetterField();
                        System.out.println("\n\nGuess a letter!");
                    }
                    case 3 -> {
                        gallowsDrawer.drawRightArm();
                        System.out.print("Missed Letters: ");
                        updateMissedLetters();
                        System.out.println("\n");
                        letterField.updateLetterField();
                        System.out.println("\n\nGuess a letter!");
                    }
                    case 4 -> {
                        gallowsDrawer.drawLeftArm();
                        System.out.print("Missed Letters: ");
                        updateMissedLetters();
                        System.out.println("\n");
                        letterField.updateLetterField();
                        System.out.println("\n\nGuess a letter!");
                    }
                    case 5 -> {
                        gallowsDrawer.drawGroin();
                        System.out.print("Missed Letters: ");
                        updateMissedLetters();
                        System.out.println("\n");
                        letterField.updateLetterField();
                        System.out.println("\n\nGuess a letter!");
                    }
                    case 6 -> {
                        gallowsDrawer.drawRightLeg();
                        System.out.print("Missed Letters: ");
                        updateMissedLetters();
                        System.out.println("\n");
                        letterField.updateLetterField();
                        System.out.println("\n\nGuess a letter!");
                    }
                    case 7 -> {
                        gallowsDrawer.drawLeftLeg();
                        System.out.print("Missed Letters: ");
                        updateMissedLetters();
                        System.out.println("\n");
                        letterField.updateLetterField();
                        System.out.println("\n\nYou lose!  The man has been hung.");
                        System.out.println("The secret word was " + word + ".");
                    }
                }
            } else { // if user has already made this guess, then prompt the user to try again.
                System.out.println("You have already guessed that letter!  Try again.");
            }
            return false; // false, indicating that the game hasn't been won.
        } else { // if game word contains the guess then update the letter field and check to see if player has won the game.
            return letterField.updateLetterField(guess);
        }
    }

    // write out the missed letters on the screen.
    private static void updateMissedLetters() {

        missedLetters.forEach(missedLetter -> System.out.print(missedLetter + " "));
    }

    public static int getNumberOfGuesses() {
        return numberOfGuesses;
    }

    public static String getWord() {
        return word;
    }
}