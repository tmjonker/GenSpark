package com.tmjonker;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class GallowsDrawer {

    private String[] gallowArray;

    public GallowsDrawer() {

        loadHangManFile();
    }

    // Loads the hangman artwork file from 'hangman.txt'.
    private void loadHangManFile() {

        String hangManFileString = "";

        try {
            File hangManFile = new File(getClass().getClassLoader().getResource("hangman.txt").toURI());
            hangManFileString = Files.readString(hangManFile.toPath(), StandardCharsets.UTF_8);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        gallowArray = hangManFileString.split(",");
    }

    public void drawGallows() {
        System.out.println(gallowArray[0]);
    }

    public void drawHead() {
        System.out.println(gallowArray[1]);
    }

    public void drawChest() {
        System.out.println(gallowArray[2]);
    }

    public void drawRightArm() {
        System.out.println(gallowArray[3]);
    }

    public void drawLeftArm() {
        System.out.println(gallowArray[4]);
    }

    public void drawGroin() {

        System.out.println(gallowArray[5]);
    }

    public void drawRightLeg() {

        System.out.println(gallowArray[6]);

    }

    public void drawLeftLeg() {

        System.out.println(gallowArray[7]);
    }
}
