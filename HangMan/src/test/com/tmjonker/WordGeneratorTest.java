package com.tmjonker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WordGeneratorTest {

    WordGenerator wordGenerator;
    ArrayList<String> arrayList;

    @BeforeEach
    void setUp() {
        wordGenerator = new WordGenerator();
        arrayList = new ArrayList<>();
        arrayList.add("dog");
        arrayList.add("cat");
    }

    @Test
    void setWordList() {
        wordGenerator.setWordList(arrayList);

        assertEquals(2, wordGenerator.getWordList().size(), "Expected value is 2.");
    }

    @Test
    void addWord() {
        wordGenerator.setWordList(arrayList);
        wordGenerator.addWord("elephant");

        assertEquals(3, wordGenerator.getWordList().size(), "Expected value is 3");
    }

    @Test
    void addWords() {
        wordGenerator.setWordList(arrayList);
        ArrayList<String> newList = new ArrayList<>();
        newList.add("ketchup");
        newList.add("mustard");
        wordGenerator.addWords(newList);

        assertEquals(4, wordGenerator.getWordList().size(), "Expected value is 5.");
    }

    @AfterEach
    void tearDown() {

        System.out.println("Test Completed");
    }
}