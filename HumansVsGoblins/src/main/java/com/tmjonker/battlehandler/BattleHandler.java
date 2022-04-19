package com.tmjonker.battlehandler;

import com.tmjonker.humanoid.Goblin;
import com.tmjonker.humanoid.Human;

import java.util.Scanner;

public class BattleHandler {

    private Scanner scanner;

    public BattleHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    // Contains all the battle logic.
    public void commenceBattle(Human player) {

        Goblin goblin = new Goblin();
        goblin.setX(player.getX());
        goblin.setY(player.getY());

        while (goblin.getHealth() > 0 && player.getHealth() > 0) {

            int humanRoll = (int) (Math.random() * 20) + 1;
            int goblinRoll = (int) (Math.random() * 20) + 1;
            int hitPercentage = (int) (Math.random() * 20) + 1;
            int goblinStartingHealth = goblin.getHealth();
            int humanStartingHealth = player.getHealth();

            if (humanRoll > goblinRoll) {
                if ((hitPercentage * 5) > 30) {
                    System.out.println("\nYou try to attack " + goblin.getName() + ", ");
                    System.out.println("AND YOU'RE SUCCESSFUL!");

                    goblin = (Goblin) player.attack(goblin);
                    int damageDealt = goblinStartingHealth - goblin.getHealth();

                    System.out.println("You deal " + damageDealt + " damage to " + goblin.getName() + ".");
                    System.out.println(goblin.getName() + " has " + goblin.getHealth() + " health remaining.\n");
                    try {
                        Thread.sleep(2000);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("\nYou try to attack " + goblin.getName() + ".");
                    System.out.println("but you MISS!");
                    System.out.println(goblin.getName() + " rejoices.\n");
                    try {
                        Thread.sleep(2000);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                if ((hitPercentage * 5) > 50) {
                    System.out.println("\nThe goblin tries to attack you, ");
                    System.out.println("AND THEY'RE SUCCESSFUL!");

                    player = (Human) goblin.attack(player);
                    int damageDealt = humanStartingHealth - player.getHealth();

                    System.out.println("They deal " + damageDealt + " damage to you.");
                    System.out.println("You have " + player.getHealth() + " health remaining.\n");
                    try {
                        Thread.sleep(2000);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("\n" + goblin.getName() + " tries to attack you, ");
                    System.out.println("but they MISS!");
                    System.out.println("You rejoice!\n");
                    try {
                        Thread.sleep(2000);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (player.getHealth() <= 0) {
                System.out.println("You have fought valiantly, but you have died!");
                System.out.println("Game Over...");
                try {
                    Thread.sleep(2000);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                System.exit(0); // Ends game if player dies.
            } else if (goblin.getHealth() <= 0) {
                System.out.println("You ruthlessly slaughtered " + goblin.getName() + ", ");
                System.out.println("You step over their lifeless corpse to continue your journey.");
                try {
                    Thread.sleep(2000);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }

            if (!fleeOrBattle()) {
                System.out.println("You turn and run away.");
                System.out.println(player.getName() + " lives to fight another day!\n");
                try {
                    Thread.sleep(2000);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
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
