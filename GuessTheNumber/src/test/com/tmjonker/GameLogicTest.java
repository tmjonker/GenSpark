package com.tmjonker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {

    GameLogic gameLogic;

    @BeforeEach
    void setUp() {
        gameLogic = new GameLogic();
    }

    @Test
    void getName() {
        gameLogic.setName("Tony");
        assertEquals("Tony", gameLogic.getName(), "getName failed.");
    }

    @Test
    void getUserGuess() {
        gameLogic.setUserGuess(15);
        assertEquals(15, gameLogic.getUserGuess(), "getUserGuess failed.");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test done...");
    }
}