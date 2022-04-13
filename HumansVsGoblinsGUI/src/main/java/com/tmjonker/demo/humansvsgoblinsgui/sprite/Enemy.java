package com.tmjonker.demo.humansvsgoblinsgui.sprite;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.List;

public class Enemy extends Sprite {

    public Enemy(Pane layer, Image image, List<Image> images, double x, double y, double dx, double dy, double health, double damage) {
        super(layer, image, images, x, y, dx, dy, health, damage);
    }

    @Override
    public void move() {
        super.move();
        animation.play();
    }
}
