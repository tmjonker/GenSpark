package com.tmjonker;

import java.util.Scanner;

public class GameLogic {

    private String name;
    private final Scanner scanner;
    private int randomNumber;
    private int counter;
    int userGuess;

    public GameLogic() {
        randomNumber = (int) (Math.random() * 20) + 1;
        counter = 0;
        scanner = new Scanner(System.in);
        name = "";
        userGuess = 0;
    }

    public void runGame() {
        name = getNameFromUser();
        promptForGuess();
        userGuess = getGuessFromUser();
        testUserGuess();
    }

    private String getNameFromUser() {

        String name = "";
        System.out.println("Hello! What is your name?");

        try {
            name = scanner.nextLine();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return name;
    }

    private void promptForGuess() {
        System.out.println("Well, " + name + ", I am thinking of a number between 1 and 20.\nTake a guess.");

        try {
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a number between 1 and 20.");
                scanner.nextLine();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private int getGuessFromUser() {
        int userGuess = 0;

        try {
            userGuess = scanner.nextInt();
            counter++;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return userGuess;
    }

    private void testUserGuess() {
        boolean validEntry = true;

        while (userGuess != randomNumber && counter <= 5) {

            if (validEntry) {
                if (userGuess > randomNumber) {
                    System.out.println("Your guess is too high.\nTake a guess.");
                    counter++;
                } else if (userGuess < randomNumber) {
                    System.out.println("Your guess is too low.\nTake a guess.");
                    counter++;
                }
            }
            try {
                userGuess = scanner.nextInt();
                validEntry = true;
            } catch (Exception e) {
                System.out.println("Your guess is invalid.\nPlease enter a number between 1 and 20.");
                validEntry = false;
                scanner.nextLine();
            }

            if (userGuess == randomNumber) {
                System.out.println("Good job, " + name + "! You guessed my number in " + counter + " guesses!");
            } else if (counter == 6) {
                System.out.println("Sorry, " + name + "! You did not guess my number in 6 guesses!");
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getUserGuess() {
        return userGuess;
    }

    public void setUserGuess(int userGuess) {
        this.userGuess = userGuess;
    }

    public void setName(String name) {
        this.name = name;
    }
}
