package com.tmjonker;

import java.util.Scanner;

public class Main {

    private static HangMan hangMan;
    private static Scanner scanner = new Scanner(System.in);
    private static String guess = "";
    private static int guesses = 0;
    private static boolean playAgain = true;

    public static void main(String[] args) {

        hangMan = new HangMan();

        // Main game loop
        while (playAgain) {
            hangMan.startGame();

            startGame();  // Loop that continuously prompts user for guesses.

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
                playAgain = true;
            }
        }
    }

    public static void startGame() {

        // Main guess loop to get player's guesses.
        while (true) {

            try {
                guess = scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.toString());
            }

            if (hangMan.updateHangMan(guess)) { // returns true if player has guessed the correct word.
                hangMan.victory();  // if true, victory is initialized indicating that the player has won.
                break;
            }

            guesses = hangMan.getNumberOfGuesses();

            if (guesses == 7) {  // if the number of guesses by the player == 7, then the player loses.
                break;
            }
        }
    }
}
