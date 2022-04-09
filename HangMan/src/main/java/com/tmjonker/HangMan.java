package com.tmjonker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class HangMan {

    private GallowsDrawer gallowsDrawer;
    private LetterField letterField;
    private WordGenerator wordGenerator;
    private int numberOfGuesses;
    private ArrayList<String> missedLetters = new ArrayList<>();
    private int numberOfWins = 0;
    private String name = "";
    private String word = "";

    public HangMan() {

    }

    public void startGame() {

        gallowsDrawer = new GallowsDrawer();
        wordGenerator = new WordGenerator();
        word = wordGenerator.generateWord();
        letterField = new LetterField(word);
        missedLetters.clear();
        numberOfGuesses = 0;
        gallowsDrawer.drawGallows();
        letterField.drawLetterField();
    }

    public void startGame(ArrayList<String> wordList) {

        gallowsDrawer = new GallowsDrawer();
        wordGenerator = new WordGenerator(wordList);
        word = wordGenerator.generateWord();
        letterField = new LetterField(word);
        missedLetters.clear();
        numberOfGuesses = 0;
        gallowsDrawer.drawGallows();
        letterField.drawLetterField();
    }

    public void victory() {

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
    private void addCheckHighScore() {

        String highScoreFileString = "";
        try {
            // Change path to reflect the Absolute path for your highscore.txt file.
            File highScoreFile = new File("/home/tim/Projects/GenSpark/HangMan/src/main/resources/highscore.txt");
            FileWriter fileWriter = new FileWriter(highScoreFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(name + "-" + numberOfWins + "/");
            bufferedWriter.close();
            fileWriter.close();
            highScoreFileString = Files.readString(highScoreFile.toPath(), StandardCharsets.UTF_8);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

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

    public boolean updateHangMan(String guess) {

        if (!word.contains(guess)) {

            if (!missedLetters.contains(guess))
                missedLetters.add(guess);

            switch (++numberOfGuesses) {
                case 1 -> {
                    gallowsDrawer.drawHead();
                    System.out.print("Missed Letters: ");
                    for (String missedLetter : missedLetters) {
                        System.out.print(missedLetter + " ");
                    }
                    System.out.println("\n");
                    letterField.updateLetterField();
                    System.out.println("\n\nGuess a letter!");
                }
                case 2 -> {
                    gallowsDrawer.drawChest();
                    System.out.print("Missed Letters: ");
                    for (String missedLetter : missedLetters) {
                        System.out.print(missedLetter + " ");
                    }
                    System.out.println("\n");
                    letterField.updateLetterField();
                    System.out.println("\n\nGuess a letter!");
                }
                case 3 -> {
                    gallowsDrawer.drawRightArm();
                    System.out.print("Missed Letters: ");
                    for (String missedLetter : missedLetters) {
                        System.out.print(missedLetter + " ");
                    }
                    System.out.println("\n");
                    letterField.updateLetterField();
                    System.out.println("\n\nGuess a letter!");
                }
                case 4 -> {
                    gallowsDrawer.drawLeftArm();
                    System.out.print("Missed Letters: ");
                    for (String missedLetter : missedLetters) {
                        System.out.print(missedLetter + " ");
                    }
                    System.out.println("\n");
                    letterField.updateLetterField();
                    System.out.println("\n\nGuess a letter!");
                }
                case 5 -> {
                    gallowsDrawer.drawGroin();
                    System.out.print("Missed Letters: ");
                    for (String missedLetter : missedLetters) {
                        System.out.print(missedLetter + " ");
                    }
                    System.out.println("\n");
                    letterField.updateLetterField();
                    System.out.println("\n\nGuess a letter!");
                }
                case 6 -> {
                    gallowsDrawer.drawRightLeg();
                    System.out.print("Missed Letters: ");
                    for (String missedLetter : missedLetters) {
                        System.out.print(missedLetter + " ");
                    }
                    System.out.println("\n");
                    letterField.updateLetterField();
                    System.out.println("\n\nGuess a letter!");
                }
                case 7 -> {
                    gallowsDrawer.drawLeftLeg();
                    System.out.print("Missed Letters: ");
                    for (String missedLetter : missedLetters) {
                        System.out.print(missedLetter + " ");
                    }
                    System.out.println("\n");
                    letterField.updateLetterField();
                    System.out.println("\n\nYou lose!  The man has been hung.");
                }
            }
            return false;
        } else {
            return letterField.updateLetterField(guess);
        }
    }

    public int getNumberOfGuesses() {
        return numberOfGuesses;
    }

    public String getWord() {
        return word;
    }
}
