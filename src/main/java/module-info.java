module com.automataproj.automataproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.automataproj.automataproject to javafx.fxml;
    exports com.automataproj.automataproject;
}