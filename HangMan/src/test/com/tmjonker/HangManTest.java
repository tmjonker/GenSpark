package com.tmjonker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HangManTest {

    HangMan hangMan;
    @BeforeEach
    void setUp() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("dog");
        hangMan = new HangMan(arrayList);
    }

    @Test
    void getWord() {
        assertEquals("dog", hangMan.getWord(), "Expected dog");
    }

    @Test
    void getNumberOfGuesses() {
        assertEquals(0, hangMan.getNumberOfGuesses(), "Expected 1");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test Completed");
    }
}