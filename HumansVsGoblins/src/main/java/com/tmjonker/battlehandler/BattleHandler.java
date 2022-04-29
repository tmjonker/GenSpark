package com.tmjonker.battlehandler;

import com.tmjonker.gamecontroller.GameController;
import com.tmjonker.humanoid.Goblin;
import com.tmjonker.humanoid.Human;

import java.util.Scanner;

public class BattleHandler {

    private final int DICE_SIDES = 20;
    private final int HUMAN_MIN_HIT_PERCENTAGE = 30; // Player must roll greater than a 30 to hit the goblin.
    private final int GOBLIN_MIN_HIT_PERCENTAGE = 50; // Goblins must roll greater than a 50 to hit the human.

    private Scanner scanner;

    public BattleHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    // Contains all the battle logic.
    public int[] commenceBattle(Human player) {

        Goblin goblin = new Goblin();
        goblin.setX(player.getX());
        goblin.setY(player.getY());

        while (goblin.getHealth() > 0 && player.getHealth() > 0) {

            int humanRoll = (int) (Math.random() * DICE_SIDES) + 1; // equivalent to a 20 sided dice roll.
            int goblinRoll = (int) (Math.random() * DICE_SIDES) + 1; // equivalent to a 20 sided dice roll.
            int hitPercentage = (int) ((Math.random() * DICE_SIDES) + 1) * 5; // Generates a hit percentage between 5 and 100.
            int goblinStartingHealth = goblin.getHealth();
            int humanStartingHealth = player.getHealth();

            // if humanRoll is greater, then player gets the chance to attack the goblin.
            if (humanRoll > goblinRoll) {
                // if hitPercentage > the minimum hit percentage then the hit is successful.
                if ((hitPercentage) > HUMAN_MIN_HIT_PERCENTAGE) {
                    System.out.println("\nYou try to attack " + goblin.getName() + ", ");
                    System.out.println("AND YOU'RE SUCCESSFUL!");

                    goblin = (Goblin) player.attack(goblin);
                    int damageDealt = goblinStartingHealth - goblin.getHealth();

                    System.out.println("You deal " + damageDealt + " damage to " + goblin.getName() + ".");
                    System.out.println(goblin.getName() + " has " + goblin.getHealth() + " health remaining.\n");

                    GameController.sleep(2000);
                    // if hitPercentage < the minimum hit percentage then the hit fails.
                } else {
                    System.out.println("\nYou try to attack " + goblin.getName() + ".");
                    System.out.println("but you MISS!");
                    System.out.println(goblin.getName() + " rejoices.\n");

                    GameController.sleep(2000);

                }

                // if goblinRoll is greater, then the goblin gets the chance to attack the player.
            } else {
                // if the goblin hitPercentage > the goblin minimum hit percentage, then the attack is successful.
                if ((hitPercentage) > GOBLIN_MIN_HIT_PERCENTAGE) {
                    System.out.println("\nThe goblin tries to attack you, ");
                    System.out.println("AND THEY'RE SUCCESSFUL!");

                    player = (Human) goblin.attack(player);
                    int damageDealt = humanStartingHealth - player.getHealth();

                    System.out.println("They deal " + damageDealt + " damage to you.");
                    System.out.println("You have " + player.getHealth() + " health remaining.\n");

                    GameController.sleep(2000);
                    // if the goblin hitPercentage < the goblin minimum hit percentage, then the attack fails.
                } else {
                    System.out.println("\n" + goblin.getName() + " tries to attack you, ");
                    System.out.println("but they MISS!");
                    System.out.println("You rejoice!\n");

                    GameController.sleep(2000);

                }
            }

            // if player health drops to 0 or below, then the human dies and the game ends.
            if (player.getHealth() <= 0) {
                System.out.println("You have fought valiantly, but you have died!");
                System.out.println("Game Over...");

                GameController.sleep(2000);

                System.exit(0); // Ends game if player dies.

                // if the goblin health drops to 0 or below, then the goblin dies, and the player continues on with the game.
            } else if (goblin.getHealth() <= 0) {
                System.out.println("You ruthlessly slaughtered " + goblin.getName() + ", ");
                System.out.println("You step over their lifeless corpse to continue your journey.");

                GameController.sleep(2000);

                break;
            }

            // if the player chooses to flee, then false is returned and the player skips the battle.
            if (!fleeOrBattle()) {
                System.out.println("You turn and run away.");
                System.out.println(player.getName() + " lives to fight another day!\n");

                GameController.sleep(2000);

                int[] goblinCoordinates = {goblin.getX(), goblin.getY()};

                return goblinCoordinates;
            }
        }

        return null;
    }

    // Prompts user to choose whether or not they want to flee battle or continue to fight.
    private boolean fleeOrBattle() {
        System.out.println("Fight or flee?\n");

        String battleChoice = "";

        while (!battleChoice.equals("flee") && !battleChoice.equals("fight"))
            try {
                battleChoice = scanner.nextLine().toLowerCase();
            } catch (Exception e){
                e.printStackTrace();
            }

        return !battleChoice.equals("flee");
    }
}
