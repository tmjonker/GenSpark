package com.tmjonker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumanTest {

    Human human;
    Goblin goblin;

    @BeforeEach
    void setUp() {
        human = new Human("Rory");
        goblin = new Goblin();
    }


    @Test
    void attack() {
        assertEquals(goblin, human.attack(goblin), "Expected goblin.");
    }

    @Test
    void getName() {
        assertEquals("Rory", human.getName(), "Name expected to be Rory.");
    }

    @Test
    void getHealth() {
        assertEquals(100, human.getHealth(), "Health expected to be 100.");
    }

    @Test
    void getWeaponDamage() {
        assertEquals(2, human.getWeaponDamage(), "Expected to be 2.");
    }

    @Test
    void getArmor() {
        assertEquals(0, human.getArmor(), "Expected to be 0");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test Completed.");
    }
}