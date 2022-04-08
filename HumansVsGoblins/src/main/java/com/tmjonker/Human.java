package com.tmjonker;

import java.util.ArrayList;

public class Human implements Humanoid {

    private String name;
    private int health;
    private int weaponDamage;
    private int gold;
    private int x;
    private int y;

    public Human(String name) {
        this.name = name;
        health = 100;
        gold = 0;
        weaponDamage = 2; // Human starts off with fists, can upgrade for higher weapon damage.
    }

    @Override
    public Humanoid attack(Humanoid target) {
        int damageModifier = (int) (Math.random() * 3) + 1;
        int damageDealt = weaponDamage * damageModifier;

        ((Goblin) target).setHealth(((Goblin) target).getHealth() - damageDealt);

        return target;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public int getGold() {
        return gold;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setWeaponDamage(int weaponDamage) {
        this.weaponDamage = weaponDamage;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", weaponDamage=" + weaponDamage +
                ", gold=" + gold +
                '}';
    }
}
