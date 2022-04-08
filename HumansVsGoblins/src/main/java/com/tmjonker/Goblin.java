package com.tmjonker;

public class Goblin implements Humanoid {

    private String name;
    private int health;
    private int damage;
    private int x;
    private int y;

    public Goblin() {
        RandomNameGenerator rng = new RandomNameGenerator();
        name = rng.next();
        health = (int) (Math.random() * 20) + 21; // Initializes health up to 40 hit points.
        damage = (int) (Math.random() * 3) + 1; // Initializes damage up to 10 damage dealt per hit.
    }

    @Override
    public Humanoid attack(Humanoid target) {
        int damageModifier = (int) (Math.random() * 3) + 1;
        int damageDealt = damage * damageModifier;

        ((Human) target).setHealth(((Human) target).getHealth() - damageDealt);

        return target;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Goblin{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                '}';
    }
}
