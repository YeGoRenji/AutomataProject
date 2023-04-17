module com.automataproj.automataproject {
    requires javafx.controls;
    requires javafx.swing;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires guru.nidi.graphviz;
    requires java.desktop;
	requires javafx.graphics;

    opens com.automataproj.automataproject to javafx.fxml;
    exports com.automataproj.automataproject;
}