package com.tmjonker.demo.humansvsgoblinsgui.sprite;

import com.tmjonker.demo.humansvsgoblinsgui.settings.Settings;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.List;

public class Enemy extends Sprite {

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

        if (x == enemyMaxX && dx > 0) {
            dx = -speed;
            dy = -speed;
        } else if (x == enemyMinX && dx < 0) {
            dx = speed;
            dy = speed;
        }

        if (y == enemyMaxY && dy > 0) {
            dx = speed;
            dy = -speed;
        } else if (y == enemyMinY && dy < 0) {
            dx = -speed;
            dy = speed;
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
}
