package com.tmjonker;

import java.util.ArrayList;

public class RandomNameGenerator {

    private ArrayList<String> nameList = new ArrayList<>();

    public RandomNameGenerator() {

        nameList.add("Ragnok");
        nameList.add("Rory");
        nameList.add("Snorlax");
        nameList.add("Alakazam");
        nameList.add("Gormley");
        nameList.add("Britney");
        nameList.add("Jindal");
        nameList.add("Zimzam");
        nameList.add("Alfonz");
        nameList.add("Muk`tar");
        nameList.add("Tal-amu");
    }

    public String next() {

        int random = (int) (Math.random() * nameList.size());

        return nameList.get(random);
    }
}
