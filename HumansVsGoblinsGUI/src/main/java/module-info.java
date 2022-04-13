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

    exports com.tmjonker.demo.humansvsgoblinsgui.accessory;
    opens com.tmjonker.demo.humansvsgoblinsgui.accessory to javafx.fxml;
    exports com.tmjonker.demo.humansvsgoblinsgui.sprite;
    opens com.tmjonker.demo.humansvsgoblinsgui.sprite to javafx.fxml;
    exports com.tmjonker.demo.humansvsgoblinsgui.input;
    opens com.tmjonker.demo.humansvsgoblinsgui.input to javafx.fxml;
    exports com.tmjonker.demo.humansvsgoblinsgui.settings;
    opens com.tmjonker.demo.humansvsgoblinsgui.settings to javafx.fxml;
    exports com.tmjonker.demo.humansvsgoblinsgui.main;
    opens com.tmjonker.demo.humansvsgoblinsgui.main to javafx.fxml;
    exports com.tmjonker.demo.humansvsgoblinsgui.game;
    opens com.tmjonker.demo.humansvsgoblinsgui.game to javafx.fxml;
}