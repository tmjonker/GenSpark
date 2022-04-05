package com.tmjonker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DragonCaveTest {

    DragonCave dragonCave;

    @BeforeEach
    void setUp() {
        dragonCave = new DragonCave();
    }

    @Test
    void getUserChoice() {
        dragonCave.setUserChoice(1);
        assertEquals(1, dragonCave.getUserChoice(), "Test should return 1.");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test Completed...");
    }
}