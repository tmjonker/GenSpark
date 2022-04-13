package com.tmjonker.demo.game.sprite;

import com.tmjonker.demo.game.input.Input;
import com.tmjonker.demo.game.settings.Settings;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.List;

public class Player extends Sprite {

    private double playerMinX;
    private double playerMaxX;
    private double playerMinY;
    private double playerMaxY;

    private Input input;

    private double speed;

    public Player(Pane layer, Image image, List<Image> images, double x, double y, double r, double dx, double dy,
                  double dr, double health, double damage, double speed, Input input) {

        super(layer, image, images, x, y, r, dx, dy, dr, health, damage);

        this.speed = speed;
        this.input = input;

        init();
    }

    private void init() {

        playerMinX = image.getWidth() / 2.0 - 15;
        playerMaxX = Settings.PLAYFIELD_WIDTH - (image.getWidth() + image.getWidth() / 2);
        playerMinY = image.getHeight() / 2.0 - 15;
        playerMaxY = Settings.PLAYFIELD_HEIGHT - (image.getHeight() + image.getHeight() / 2 + 10);

    }

    public void processInput() {

        // vertical direction
        if( input.isMoveUp()) {
            dy = -speed;
        } else if( input.isMoveDown()) {
            dy = speed;
        } else {
            dy = 0d;
        }

        // horizontal direction
        if( input.isMoveLeft()) {
            dx = -speed;
        } else if( input.isMoveRight()) {
            dx = speed;
        } else {
            dx = 0d;
        }
    }

    @Override
    public void move() {

        super.move();
        animation.play();
        checkBounds();
    }

    private void checkBounds() {

        // vertical
        if(Double.compare(y, playerMinY) < 0) {
            y = playerMinY;
        } else if(Double.compare(y, playerMaxY) > 0) {
            y = playerMaxY;
        }

        // horizontal
        if(Double.compare( x, playerMinX) < 0) {
            x = playerMinX;
        } else if(Double.compare(x, playerMaxX) > 0) {
            x = playerMaxX;
        }
    }

    public void drinkPotion() {
        health = Settings.PLAYER_HEALTH;
    }
}