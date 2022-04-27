package com.tmjonker.humanoid;

import com.tmjonker.randomnamegenerator.RandomNameGenerator;

public abstract class Humanoid {

    protected String name;
    protected int health;
    protected int damage;
    private int x;
    private int y;
    private int[] coordinates = new int[2];

    public Humanoid(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    public Humanoid(int health, int damage) {
        RandomNameGenerator rng = new RandomNameGenerator();
        name = rng.next();
        this.health = health;
        this.damage = damage;
    }

    abstract Humanoid attack(Humanoid target);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
        coordinates[0] = x;
    }

    public void setY(int y) {
        this.y = y;
        coordinates[1] = x;
    }

    public int[] getCoordinates() {

        return coordinates;
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
}
