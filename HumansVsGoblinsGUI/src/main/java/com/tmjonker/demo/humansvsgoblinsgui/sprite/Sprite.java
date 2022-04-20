package com.tmjonker.demo.humansvsgoblinsgui.sprite;

import com.tmjonker.demo.humansvsgoblinsgui.accessory.Accessory;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.List;

public abstract class Sprite {

    protected Image image;
    protected List<Image> images;
    protected ImageView imageView;
    protected Transition animation;

    protected Pane layer;

    protected double x;
    protected double y;
    protected double r;

    protected double dx;
    protected double dy;

    protected boolean removable = false;

    protected double w;
    protected double h;

    protected boolean canMove = true;

    protected double health;
    protected double damage;

    public Sprite(Pane layer, Image image, List<Image> images, double x, double y, double dx,
                  double dy, double health, double damage) {

        this.layer = layer;
        this.image = image;
        this.images = images;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.health = health;
        this.damage = (damage * Math.random()) + damage;

        this.imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        this.imageView.relocate(x, y);
        setupAnimation();

        this.w = image.getWidth(); // imageView.getBoundsInParent().getWidth();
        this.h = image.getHeight(); // imageView.getBoundsInParent().getHeight();

        addToLayer();

    }

    // sets up sprite animation that animates the actual character sprite.
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

    // Adds imageview of character sprite to the map.
    public void addToLayer() {
        this.layer.getChildren().add(this.imageView);
    }

    // removes the character sprite from the map.
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

    // checks to see if sprite is removable from map.
    public boolean isRemovable() {
        return removable;
    }

    public void setRemovable(boolean removable) {
        this.removable = removable;
    }

    // updates player x and y based on directions passed from keyboard input.
    public void move() {

        if(!canMove)
            return;

        x += dx;
        y += dy;
    }

    // moves character image to the new x and y coordinates so that the character can move around the screen.
    public void updateUI() {

        imageView.relocate(x, y);
        imageView.setRotate(r);
    }

    // checks to see if sprite has collided with another sprite and returns a boolean.
    public boolean collidesWith(Sprite otherSprite) {

        return (otherSprite.x + otherSprite.w >= x && otherSprite.y + otherSprite.h >= y && otherSprite.x <= x + w &&
                otherSprite.y <= y + h);

    }

    // checks to see if sprite has collided with an accessory and returns a boolean.
    public boolean collidesWith(Accessory accessory) {

        return (accessory.getX() + accessory.getW() >= x && accessory.getY() + accessory.getH() >= y &&
                accessory.getX() <= x + w && accessory.getY() <= y + h);

    }

    // modifies sprite health based on damage caused by the enemy or player that has been collided with.
    public void getDamagedBy( Sprite sprite) {
        health -= sprite.getDamage();
    }

    public void remove() {
        setRemovable(true);
    }
}
