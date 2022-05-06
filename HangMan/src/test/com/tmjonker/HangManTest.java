package com.tmjonker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HangManTest {


    @BeforeEach
    void setUp() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("dog");
        HangMan.startGame(arrayList);
    }

    @Test
    void getWord() {
        assertEquals("dog", HangMan.getWord(), "Expected dog");
    }

    @Test
    void updateHangMan() {

        assertFalse(HangMan.updateHangMan("o"), "Expected false; game has not been won yet.");
        assertFalse(HangMan.updateHangMan("d"), "Expected false; game has not been won yet.");
        assertTrue(HangMan.updateHangMan("g"), "Expected true; word has been solved.");
        assertFalse(HangMan.updateHangMan("c"), "Expected false; dog does not contain a c.");
    }

    @Test
    void getNumberOfGuesses() {
        assertEquals(0, HangMan.getNumberOfGuesses(), "Expected 1");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test Completed");
    }
}