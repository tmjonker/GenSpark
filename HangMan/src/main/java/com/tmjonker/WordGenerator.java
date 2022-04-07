package com.tmjonker;

import java.util.ArrayList;

public class WordGenerator {

    private ArrayList<String> wordList;

    public WordGenerator() {

    }

    public WordGenerator(ArrayList<String> wordList) {

        this.wordList = wordList;
    }

    public String generateWord() {

        int randomNumber = (int) (Math.random() * wordList.size());

        return wordList.get(randomNumber);
    }

    public void setWordList(ArrayList<String> wordList) {

        this.wordList = wordList;
    }

    public void addWord(String word) {

        wordList.add(word);
    }

    public void addWords(ArrayList<String> wordList) {

        this.wordList.addAll(wordList);
    }

    public ArrayList<String> getWordList() {
        return wordList;
    }
}
