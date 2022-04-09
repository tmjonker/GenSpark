package com.tmjonker;

import java.util.ArrayList;

public class WordGenerator {

    private ArrayList<String> wordList;

    public WordGenerator() {
        wordList = new ArrayList<>();
        wordList.add("elephant");
        wordList.add("dog");
        wordList.add("ketchup");
        wordList.add("barstool");
        wordList.add("chicken");
        wordList.add("superman");
        wordList.add("cat");
        wordList.add("jewel");
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
