module com.tmjonker.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.tmjonker.demo.game to javafx.fxml;
    exports com.tmjonker.demo.game;
    exports com.tmjonker.demo.game.accessory;
    opens com.tmjonker.demo.game.accessory to javafx.fxml;
    exports com.tmjonker.demo.game.sprite;
    opens com.tmjonker.demo.game.sprite to javafx.fxml;
    exports com.tmjonker.demo.game.input;
    opens com.tmjonker.demo.game.input to javafx.fxml;
    exports com.tmjonker.demo.game.settings;
    opens com.tmjonker.demo.game.settings to javafx.fxml;
}