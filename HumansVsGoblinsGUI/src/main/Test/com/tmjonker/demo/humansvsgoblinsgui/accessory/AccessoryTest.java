package com.tmjonker.demo.humansvsgoblinsgui.accessory;


import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class AccessoryTest {

    Potion potion;

    @Start
    public void start(Stage stage) {

        Potion potion = new Potion(new Image("potion.png"), 100, 200);
    }

    @Test
    void getH() {
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