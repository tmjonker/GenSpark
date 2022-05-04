package com.tmjonker;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class LetterField {


    private final String word;

    private final ArrayList<String> letterList = new ArrayList<>();

    // Contains all the code pertaining to the letter field that populates guesses and matched letters.
    public LetterField(String word) {


        this.word = word;
    }

    public void drawLetterField() {

        word.chars().mapToObj(c -> (char) c).forEach((c) -> {
            if (c == word.charAt(word.length() - 1))
                System.out.println("_\n");
            else
                System.out.print("_");
        });

        System.out.println("Guess a letter!");
    }

    public void updateLetterField() {

        word.chars().mapToObj(c -> (char) c).forEach(c -> {
            String character = Character.toString(c);

            if (letterList.contains(character))
                System.out.print(character);
            else
                System.out.print("_");
        });
    }

    public boolean updateLetterField(String guess) {

        AtomicBoolean trueFalse = new AtomicBoolean(true);

        word.chars().mapToObj(c -> (char) c).forEach(c -> {
            String character = Character.toString(c);

            if (character.equals(guess))
                if (!letterList.contains(character))
                    letterList.add(character);
                else {
                    System.out.println("You have already guessed that letter. Choose again.");
                    trueFalse.set(false);
                }
        });

        if (!trueFalse.get())
            return false;

        word.chars().mapToObj(c -> (char) c).forEach(c -> {
            String character = Character.toString(c);

            if (letterList.contains(character))
                System.out.print(character);
            else
                System.out.print("_");
        });

        if (letterList.size() != word.length())
            System.out.println("\n\nGuess a letter!");

        return letterList.size() == word.length();
    }

    public String getWord() {
        return word;
    }
}
