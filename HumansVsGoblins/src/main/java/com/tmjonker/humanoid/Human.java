package com.tmjonker.humanoid;

public class Human extends Humanoid {

    private int gold;

    public Human(String name) {

        super(name, 100, 2); // initializes health to 100 and damage to 2.
        gold = 0;
    }

    @Override
    public Humanoid attack(Humanoid target) {
        // random damage modifier adds 1-3 to human's damage output.
        int damageModifier = (int) (Math.random() * 3) + 1;
        int damageDealt = damage * damageModifier;

        ((Goblin) target).setHealth(((Goblin) target).getHealth() - damageDealt);

        return target;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", weaponDamage=" + damage +
                ", gold=" + gold +
                '}';
    }
}
