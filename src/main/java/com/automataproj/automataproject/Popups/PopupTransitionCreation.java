package com.automataproj.automataproject.Popups;

import com.automataproj.automataproject.Home;
import com.automataproj.automataproject.Metier.AutomateFini;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class PopupTransitionCreation {
    public static PopupTransitionReturn display(AutomateFini af) throws IOException {
        Stage window = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("newTransitionPopup.fxml"));

        window.setTitle("New Transition");
        window.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(fxmlLoader.load(), 750, 400);
        fxmlLoader.<PopupTransitionController>getController().setAf(af);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();
        return fxmlLoader.<PopupTransitionController>getController().returnObj;
    }
}
