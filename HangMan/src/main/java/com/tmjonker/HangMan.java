package com.tmjonker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class HangMan {

    private WordGenerator wordGenerator;
    private String word;
    private int numberOfGuesses;
    private HashMap<Integer, Integer> letterMap = new HashMap<>();
    private ArrayList<String> missedLetters = new ArrayList<>();
    private String[] gallowArray;
    private int numberOfWins = 0;
    private String name = "";

    public HangMan() {

    }

    public void startGame() {
        loadHangManFile();

        letterMap.clear();
        missedLetters.clear();

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

    public void startGame(ArrayList<String> wordList) {
        loadHangManFile();

        wordGenerator = new WordGenerator(wordList);
        word = wordGenerator.generateWord();
        numberOfGuesses = 0;

        drawGallows();
        drawLetterField();
    }

    public void resetGame() {


    }

    // Loads the hangman artwork file from 'hangman.txt'.
    private void loadHangManFile() {

        String hangManFileString = "";

        try {
            File hangManFile = new File(getClass().getClassLoader().getResource("hangman.txt").toURI());
            hangManFileString = Files.readString(hangManFile.toPath(), StandardCharsets.UTF_8);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        gallowArray = hangManFileString.split(",");
    }

    private void drawGallows() {
        System.out.println(gallowArray[0]);
    }

    private void drawHead() {
        System.out.println(gallowArray[1]);
    }

    private void drawChest() {
        System.out.println(gallowArray[2]);
    }

    private void drawRightArm() {
        System.out.println(gallowArray[3]);
    }

    private void drawLeftArm() {
        System.out.println(gallowArray[4]);
    }

    private void drawGroin() {

        System.out.println(gallowArray[5]);
    }

    private void drawRightLeg() {

        System.out.println(gallowArray[6]);

    }

    private void drawLeftLeg() {

        System.out.println(gallowArray[7]);
    }

    private void drawLetterField() {

        int numberOfDashes = word.length();

        for (int i = 0; i < numberOfDashes; i++) {
            if (i == numberOfDashes - 1)
                System.out.println("_\n");
            else
                System.out.print("_");
        }

        System.out.println("Guess a letter!");
    }

    private boolean updateLetterField(String guess) {

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
                System.out.print(word.charAt(i));
            } else {
                System.out.print("_");
            }
        }

        if (letterMap.size() != word.length())
            System.out.println("\n\nGuess a letter!");
        else
            victory();

        return letterMap.size() == word.length();
    }

    private void victory() {


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
            highScoreFileString = Files.readString(highScoreFile.toPath(), StandardCharsets.UTF_8);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        String[] highScores = highScoreFileString.split("/");
        List<String> list = Arrays.stream(highScores)
                .filter(s -> Integer.parseInt(s.substring(s.indexOf("-") + 1, s.length())) > 1).toList();

        // Sorts High Score List based on number of wins.
        ArrayList<String> highScoreList = new ArrayList<>(list);
        highScoreList.sort((s1, s2) -> s1.substring(s1.indexOf("-") + 1,
                s1.length()).compareTo(s2.substring(s2.indexOf("-") + 1, s2.length())));
        Collections.reverse(highScoreList);

        if (highScoreList.size() < 1) {
            System.out.println("\nCongratulations, you have the high score with " + numberOfWins + " wins!\n");
            System.out.println("High Scores: ");
            highScoreList.forEach(s -> System.out.println(s));

        } else {
            System.out.println("\nYou do not have the current high score!\n");
            System.out.println("High Scores: ");
            highScoreList.forEach(s -> System.out.println(s));
        }
    }

    private void updateLetterField() {

        for (int i = 0; i < word.length(); i++) {
            if (letterMap.containsKey(i)) {
                System.out.print(word.substring(i, i+1));
            } else {
                System.out.print("_");
            }
        }
    }

    public boolean updateHangMan(String guess) {

        if (!word.contains(guess)) {

            if (!missedLetters.contains(guess))
                missedLetters.add(guess);

            switch (++numberOfGuesses) {
                case 1 -> {
                    drawHead();
                    System.out.print("Missed Letters: ");
                    for (String missedLetter : missedLetters) {
                        System.out.print(missedLetter + " ");
                    }
                    System.out.println("\n");
                    updateLetterField();
                    System.out.println("\n\nGuess a letter!");
                }
                case 2 -> {
                    drawChest();
                    System.out.print("Missed Letters: ");
                    for (String missedLetter : missedLetters) {
                        System.out.print(missedLetter + " ");
                    }
                    System.out.println("\n");
                    updateLetterField();
                    System.out.println("\n\nGuess a letter!");
                }
                case 3 -> {
                    drawRightArm();
                    System.out.print("Missed Letters: ");
                    for (String missedLetter : missedLetters) {
                        System.out.print(missedLetter + " ");
                    }
                    System.out.println("\n");
                    updateLetterField();
                    System.out.println("\n\nGuess a letter!");
                }
                case 4 -> {
                    drawLeftArm();
                    System.out.print("Missed Letters: ");
                    for (String missedLetter : missedLetters) {
                        System.out.print(missedLetter + " ");
                    }
                    System.out.println("\n");
                    updateLetterField();
                    System.out.println("\n\nGuess a letter!");
                }
                case 5 -> {
                    drawGroin();
                    System.out.print("Missed Letters: ");
                    for (String missedLetter : missedLetters) {
                        System.out.print(missedLetter + " ");
                    }
                    System.out.println("\n");
                    updateLetterField();
                    System.out.println("\n\nGuess a letter!");
                }
                case 6 -> {
                    drawRightLeg();
                    System.out.print("Missed Letters: ");
                    for (String missedLetter : missedLetters) {
                        System.out.print(missedLetter + " ");
                    }
                    System.out.println("\n");
                    updateLetterField();
                    System.out.println("\n\nGuess a letter!");
                }
                case 7 -> {
                    drawLeftLeg();
                    System.out.print("Missed Letters: ");
                    for (String missedLetter : missedLetters) {
                        System.out.print(missedLetter + " ");
                    }
                    System.out.println("\n");
                    updateLetterField();
                    System.out.println("\n\nYou lose!  The man has been hung.");
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
