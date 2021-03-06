package com.tmjonker.treasure;

import com.tmjonker.humanoid.Human;

public class Treasure {

    // Adds gold that was found to the player.
    public void collectTreasure(Human player) {
        int gold = (int) (Math.random() * 100) + 1;
        System.out.println("You have found " + gold + " gold!");
        player.setGold((player.getGold() + gold));
        System.out.println("You now have a total of " + player.getGold() + " gold.");
    }
}
