package com.tmjonker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LetterFieldTest {

    LetterField letterField;

    @BeforeEach
    void setUp() {

        letterField = new LetterField("dog");
    }

    @AfterEach
    void tearDown() {

        System.out.println("Test has been completed.");
    }

    @Test
    void updateLetterField() {

        assertFalse(letterField.updateLetterField("d"), "Expected false because word has not been solved yet.");
        assertFalse(letterField.updateLetterField("o"), "Expected false because word has not been solved yet.");
        assertTrue(letterField.updateLetterField("g"), "Expected true because word has been solved.");
    }
}