module com {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires junit;

    opens com.translatorapp to javafx.fxml;
    exports com.translatorapp;
    exports com;
    opens com to javafx.fxml;
}