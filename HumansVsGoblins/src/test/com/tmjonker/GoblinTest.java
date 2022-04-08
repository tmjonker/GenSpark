package com.tmjonker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoblinTest {

    Goblin goblin;
    Human human;

    @BeforeEach
    void setUp() {
        goblin = new Goblin();
        human = new Human("Rory");
    }

    @Test
    void attack() {

        assertEquals(human, goblin.attack(human), "Expected human to be returned.");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test Completed");
    }
}