package com.tmjonker;

import java.util.Scanner;

public class Main {

    private static HangMan hangMan;
    private static Scanner scanner = new Scanner(System.in);
    private static String guess = "";
    private static boolean endGame = false;
    private static int guesses = 0;
    private static boolean playAgain = true;

    public static void main(String[] args) {

        hangMan = new HangMan();

        while (playAgain) {
            hangMan.startGame();

            startGame();

            System.out.println("\nDo you want to play again? (yes or no)");

            String userPrompt = "";
            try {
                userPrompt = scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.toString());
            }

            userPrompt = userPrompt.toLowerCase();

            if (userPrompt.equals("no"))
                playAgain = false;
            else {
                endGame = false;
                playAgain = true;
            }
        }
    }

    public static void startGame() {

        while (!endGame) {

            try {
                guess = scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.toString());
            }

            endGame = hangMan.updateHangMan(guess);

            guesses = hangMan.getNumberOfGuesses();

            if (guesses == 7) {
                endGame = true;
            }
        }
    }
}
