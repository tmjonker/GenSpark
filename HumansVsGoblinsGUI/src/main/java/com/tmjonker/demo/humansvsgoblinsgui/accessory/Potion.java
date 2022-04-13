package com.tmjonker.demo.humansvsgoblinsgui.accessory;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Potion extends Accessory{

    public Potion(Pane layer, Image image, double x, double y) {
        super(layer, image, x, y);
    }

    public Potion( Image image, double x, double y) {
        super(image, x, y);
    }
}
