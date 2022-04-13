package com.tmjonker.demo.humansvsgoblinsgui.accessory;

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

    public Accessory(Pane layer, Image image, double x, double y) {

        this.layer = layer;
        this.image = image;
        this.x = x;
        this.y = y;

        imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        imageView.relocate(x, y);

        this.w = image.getWidth();
        this.h = image.getHeight();

        addToLayer();
    }

    public Accessory(Image image, double x, double y) {

        this.image = image;
        this.x = x;
        this.y = y;

        imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        imageView.relocate(x, y);

        this.w = image.getWidth();
        this.h = image.getHeight();
    }

    // adds accessory image to map.
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

    public boolean isRemovable() {
        return removable;
    }

    /*
     sets removable to boolean value passed in.  If boolean is true, then this opens this accessory up to deletion
     during the next loop.
     */
    public void setRemovable(boolean removable) {
        this.removable = removable;
    }

    // removes the accessory from the map.
    public void removeFromLayer() {
        this.layer.getChildren().remove(this.imageView);
    }
}
