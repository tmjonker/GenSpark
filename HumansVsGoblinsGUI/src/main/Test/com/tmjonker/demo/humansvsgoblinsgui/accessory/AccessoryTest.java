package com.tmjonker.demo.humansvsgoblinsgui.accessory;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccessoryTest {

    Potion potion;

    @BeforeEach
    void setUp() {
        potion = new Potion(new Pane(), new Image("potion.png"), 100, 200);
    }

    @Test
    void getH() {
        assertEquals(32.0, potion.getH(), "Expected 32.0");
    }

    @Test
    void getW() {
    }

    @Test
    void getX() {
    }

    @Test
    void getY() {
    }

    @Test
    void isRemovable() {
    }

    @AfterEach
    void tearDown() {
    }
}