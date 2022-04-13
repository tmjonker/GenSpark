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
    exports com.tmjonker.demo.humansvsgoblinsgui.sprite;
    exports com.tmjonker.demo.humansvsgoblinsgui.input;
    exports com.tmjonker.demo.humansvsgoblinsgui.settings;
    exports com.tmjonker.demo.humansvsgoblinsgui.main;
    exports com.tmjonker.demo.humansvsgoblinsgui.game;
}