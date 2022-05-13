package com.tmjonker.demo.humansvsgoblinsgui.sprite;

import com.tmjonker.demo.humansvsgoblinsgui.settings.Settings;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.Random;

public class Enemy extends Sprite {

    private final Random RND = new Random();

    private double enemyMinX;
    private double enemyMaxX;
    private double enemyMinY;
    private double enemyMaxY;

    private double speed = Settings.ENEMY_SPEED;

    public Enemy(Pane layer, Image image, List<Image> images, double x, double y, double dx, double dy, double health, double damage) {
        super(layer, image, images, x, y, dx, dy, health, damage);

        init();
    }

    private void init() {

        enemyMinX = image.getWidth() / 2.0 - 15;
        enemyMaxX = Settings.PLAYFIELD_WIDTH - (image.getWidth() + image.getWidth() / 2);
        enemyMinY = image.getHeight() / 2.0 - 15;
        enemyMaxY = Settings.PLAYFIELD_HEIGHT - (image.getHeight() + image.getHeight() / 2 + 10);
    }

    @Override
    public void move() {
        super.move();
        animation.play();

        checkBounds();
    }

    public void changeDirection() {

        double directionYDeterminer = (RND.nextDouble() * 10) + 1;
        double directionXDeterminer = (RND.nextDouble() * 10) + 1;

        if (x == enemyMaxX) {
            dx = directionXDeterminer > 5 ? -speed : 0;
            dy = directionYDeterminer > 5 ? -speed : 0;
        } else if (x == enemyMinX) {
            dx = directionXDeterminer > 5 ? speed : 0;
            dy = directionYDeterminer > 5 ? speed : 0;
        }

        if (y == enemyMaxY) {
            dx = directionXDeterminer > 5 ? speed : 0;
            dy = directionYDeterminer > 5 ? -speed : 0;
        } else if (y == enemyMinY) {
            dx = directionXDeterminer > 5 ? -speed : 0;
            dy = directionYDeterminer > 5 ? speed : 0;
        }
    }

    private void checkBounds() {

        // vertical
        if (Double.compare(y, enemyMinY) < 0) {
            y = enemyMinY;
        } else if (Double.compare(y, enemyMaxY) > 0) {
            y = enemyMaxY;
        }

        // horizontal
        if (Double.compare(x, enemyMinX) < 0) {
            x = enemyMinX;
        } else if (Double.compare(x, enemyMaxX) > 0) {
            x = enemyMaxX;
        }
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }
}
