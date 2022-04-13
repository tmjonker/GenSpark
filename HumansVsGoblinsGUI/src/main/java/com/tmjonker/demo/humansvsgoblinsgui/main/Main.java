package com.tmjonker.demo.humansvsgoblinsgui.main;

import com.tmjonker.demo.humansvsgoblinsgui.game.Game;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        new Game(stage);
    }

    public static void main(String[] args) {

        launch(args);
    }
}
