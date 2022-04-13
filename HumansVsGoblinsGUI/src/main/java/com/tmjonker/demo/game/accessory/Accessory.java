package com.tmjonker.demo.game.accessory;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Accessory {

    private Pane layer;
    private Image image;
    private ImageView imageView;
    private double x;
    private double y;
    private double w;
    private double h;
    private boolean removable = false;

    public Accessory(Pane layer, Image image, double x, double y, double r) {

        this.layer = layer;
        this.image = image;
        this.x = x;
        this.y = y;

        imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        imageView.relocate(x, y);
        imageView.setRotate(r);

        this.w = image.getWidth();
        this.h = image.getHeight();

        addToLayer();
    }

    public void addToLayer() {
        this.layer.getChildren().add(this.imageView);
    }

    public double getH() {
        return h;
    }

    public double getW() {
        return w;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void remove() {
        setRemovable(true);
    }

    public boolean isRemovable() {
        return removable;
    }

    public void setRemovable(boolean removable) {
        this.removable = removable;
    }

    public void removeFromLayer() {
        this.layer.getChildren().remove(this.imageView);
    }
}
