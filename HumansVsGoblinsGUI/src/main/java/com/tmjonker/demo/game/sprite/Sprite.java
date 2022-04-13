package com.tmjonker.demo.game.sprite;

import com.tmjonker.demo.game.accessory.Accessory;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.List;

public abstract class Sprite {

    Image image;
    List<Image> images;
    ImageView imageView;
    Transition animation;

    Pane layer;

    double x;
    double y;
    double r;

    double dx;
    double dy;
    double dr;

    boolean removable = false;

    double w;
    double h;

    boolean canMove = true;

    double health;
    double damage;

    public Sprite(Pane layer, Image image, List<Image> images, double x, double y, double r, double dx,
                  double dy, double dr, double health, double damage) {

        this.layer = layer;
        this.image = image;
        this.images = images;
        this.x = x;
        this.y = y;
        this.r = r;
        this.dx = dx;
        this.dy = dy;
        this.dr = dr;
        this.health = health;
        this.damage = (damage * Math.random()) + damage;

        this.imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        this.imageView.relocate(x, y);
        this.imageView.setRotate(r);
        setupAnimation();

        this.w = image.getWidth(); // imageView.getBoundsInParent().getWidth();
        this.h = image.getHeight(); // imageView.getBoundsInParent().getHeight();

        addToLayer();

    }

    public void setupAnimation() {

        animation = new Transition() {
            {
                setCycleDuration(Duration.millis(500));
            }
            @Override
            protected void interpolate(double v) {
                int index = (int) (v * (images.size()-1));
                imageView.setImage(images.get(index));
            }
        };
    }

    public void addToLayer() {
        this.layer.getChildren().add(this.imageView);
    }

    public void removeFromLayer() {
        this.layer.getChildren().remove(this.imageView);
    }

    public double getHealth() {
        return health;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public boolean isAlive() {
        return Double.compare(health, 0) > 0;
    }

    public boolean isRemovable() {
        return removable;
    }

    public void setRemovable(boolean removable) {
        this.removable = removable;
    }

    public void move() {

        if(!canMove)
            return;

        x += dx;
        y += dy;
        r += dr;
    }

    public void updateUI() {

        imageView.relocate(x, y);
        imageView.setRotate(r);
    }

    public boolean collidesWith(Sprite otherSprite) {

        return (otherSprite.x + otherSprite.w >= x && otherSprite.y + otherSprite.h >= y && otherSprite.x <= x + w &&
                otherSprite.y <= y + h);

    }

    public boolean collidesWith(Accessory accessory) {

        return (accessory.getX() + accessory.getW() >= x && accessory.getY() + accessory.getH() >= y &&
                accessory.getX() <= x + w && accessory.getY() <= y + h);

    }

    public void getDamagedBy( Sprite sprite) {
        health -= sprite.getDamage();
    }

    public void remove() {
        setRemovable(true);
    }
}
