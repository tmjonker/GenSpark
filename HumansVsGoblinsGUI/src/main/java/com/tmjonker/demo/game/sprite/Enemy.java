package com.tmjonker.demo.game.sprite;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.List;

public class Enemy extends Sprite {

    public Enemy(Pane layer, Image image, List<Image> images, double x, double y, double r, double dx, double dy, double dr, double health, double damage) {
        super(layer, image, images, x, y, r, dx, dy, dr, health, damage);
    }

    @Override
    public void move() {
        super.move();
        animation.play();
    }
}
