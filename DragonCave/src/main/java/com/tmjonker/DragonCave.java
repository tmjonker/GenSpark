package com.tmjonker;

import java.util.Scanner;

public class DragonCave {

    private int userChoice;

    public DragonCave() {

        userChoice = 0;
    }

    public void runGame() {
        Scanner scanner = new Scanner(System.in);
        greetAndPromptUser();
        userChoice = getUserInput(scanner);
        displayGameText();
    }

    private void greetAndPromptUser() {
        System.out.println("You are in a aland full of dragons. In front of you,");
        System.out.println("you see two caves. In one cave, the dragon is friendly");
        System.out.println("and will share his treasure with you. The other dragon");
        System.out.println("is greedy and hungry and will eat you on sight.");
        System.out.println("Which cave will you go into? (1 or 2)");
    }

    private int getUserInput(Scanner scanner) {
        int userChoice = 0;
        while (userChoice != 1 && userChoice != 2) {

            try {
                int selection = scanner.nextInt();
                if (selection == 1 || selection == 2)
                    userChoice = selection;
                else {
                    System.out.println("Please enter 1 or 2.");
                    scanner.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Please enter 1 or 2.");
                scanner.nextLine();
            }
        }
        return userChoice;
    }

    private void displayGameText() {

        if (userChoice == 1) {
            System.out.println("You approach the cave...");
            System.out.println("It is dark and spooky...");
            System.out.println("A large dragon jumps out in front of you! He opens his jaws and...");
            System.out.println("Gobbles you down in one bite!");
        } else if (userChoice == 2) {
            System.out.println("You approach the cave...");
            System.out.println("It is bright and glowing...");
            System.out.println("A large dragon greets you with a smile! He opens his jaws and...");
            System.out.println("Says \"Hello!\"");
        }
    }

    public int getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(int userChoice) {
        this.userChoice = userChoice;
    }
}
