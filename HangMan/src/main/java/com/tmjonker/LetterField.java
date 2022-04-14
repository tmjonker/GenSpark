package com.tmjonker;

import java.util.ArrayList;
import java.util.HashMap;

public class LetterField {


    private final String word;

    private final HashMap<Integer, Integer> letterMap = new HashMap<>();

    // Contains all the code pertaining to the letter field that populates guesses and matched letters.
    public LetterField(String word) {


        this.word = word;
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

    public void updateLetterField() {

        for (int i = 0; i < word.length(); i++) {
            if (letterMap.containsKey(i)) {
                System.out.print(word.substring(i, i+1));
            } else {
                System.out.print("_");
            }
        }
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
                System.out.print(word.charAt(i));
            } else {
                System.out.print("_");
            }
        }

        if (letterMap.size() != word.length())
            System.out.println("\n\nGuess a letter!");

        return letterMap.size() == word.length();
    }

    public String getWord() {
        return word;
    }
}
