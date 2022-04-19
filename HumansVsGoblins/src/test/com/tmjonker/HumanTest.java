package com.tmjonker;

import com.tmjonker.humanoid.Goblin;
import com.tmjonker.humanoid.Human;
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
        assertEquals(2, human.getDamage(), "Expected to be 2.");
    }

    @Test
    void getGold() {
        assertEquals(0, human.getGold(), "Expected 0");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test Completed.");
    }
}