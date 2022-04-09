package com.tmjonker;

public class Treasure {

    public void collectTreasure(Human player) {
        int gold = (int) (Math.random() * 100) + 1;
        System.out.println("You have found " + gold + " gold!");
        player.setGold((player.getGold() + gold));
        System.out.println("You now have a total of " + player.getGold() + " gold.");
    }
}
