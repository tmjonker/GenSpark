package com.tmjonker.demo.humansvsgoblinsgui.input;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.BitSet;

public class Input {

    // Stores boolean values that are associated with certain numerical values
    private BitSet keyboardBitSet = new BitSet();

    private KeyCode upKey = KeyCode.UP;
    private KeyCode downKey = KeyCode.DOWN;
    private KeyCode leftKey = KeyCode.LEFT;
    private KeyCode rightKey = KeyCode.RIGHT;

    private Scene scene;

    public Input( Scene scene) {
        this.scene = scene;
    }

    // Adds event filter to process keyboard input to the scene.
    public void addListeners() {

        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyPressedEventHandler);
        scene.addEventFilter(KeyEvent.KEY_RELEASED, keyReleasedEventHandler);

    }

    public void removeListeners() {

        scene.removeEventFilter(KeyEvent.KEY_PRESSED, keyPressedEventHandler);
        scene.removeEventFilter(KeyEvent.KEY_RELEASED, keyReleasedEventHandler);

    }

    // Gets ordinal value of the key press and sets its value to true in the keyset to indicate that the key was pressed.
    private EventHandler<KeyEvent> keyPressedEventHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {

            keyboardBitSet.set(event.getCode().ordinal(), true);
        }
    };

    // Gets ordinal value of the  key press and sets its value to false to indicate that the key was released.
    private EventHandler<KeyEvent> keyReleasedEventHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {

            keyboardBitSet.set(event.getCode().ordinal(), false);

        }
    };

    // returns true if up key ordinal is true and down key ordinal is false indicating that the user is pressing the up key.
    public boolean isMoveUp() {
        return keyboardBitSet.get( upKey.ordinal()) && !keyboardBitSet.get( downKey.ordinal());
    }
    // returns true if down key ordinal is true and up key ordinal is false indicating that the down key is being pressed
    public boolean isMoveDown() {
        return keyboardBitSet.get( downKey.ordinal()) && !keyboardBitSet.get( upKey.ordinal());
    }
    // returns true if the left key ordinal is true and the right key ordinal is false indicating that the left key is pressed.
    public boolean isMoveLeft() {
        return keyboardBitSet.get( leftKey.ordinal()) && !keyboardBitSet.get( rightKey.ordinal());
    }
    // returns true if the right key ordinal is true and the left key ordinal is false indicating that the right key is pressed.
    public boolean isMoveRight() {
        return keyboardBitSet.get( rightKey.ordinal()) && !keyboardBitSet.get( leftKey.ordinal());
    }
}