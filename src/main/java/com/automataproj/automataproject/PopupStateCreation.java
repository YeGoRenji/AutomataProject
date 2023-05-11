package com.automataproj.automataproject;

import guru.nidi.graphviz.attribute.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class PopupStateCreation {
    public static PopupStateReturn display() throws IOException {
        Stage window = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("newStatePopup.fxml"));

        window.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(fxmlLoader.load(), 750, 400);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();
        return fxmlLoader.<PopupStateController>getController().returnObj;
    }
}
