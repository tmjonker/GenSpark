package com.tmjonker.humanoid;

import com.tmjonker.randomnamegenerator.RandomNameGenerator;

public abstract class Humanoid {

    protected String name;
    protected int health;
    protected int damage;
    private int x = 0;
    private int y = 0;
    private int px; // previous x position
    private int py; // previous y position


    // primarily used for human generation
    public Humanoid(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    // primarily used for goblin creation.
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

    // assigns previous x position to px before assigning new x positiion.
    public void setX(int x) {

        px = this.x;

        this.x = x;
    }

    // assigns previous y position to py before assigning new y position.
    public void setY(int y) {

        py = this.y;

        this.y = y;
    }

    public int getPx() {
        return px;
    }

    public int getPy() {
        return py;
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
