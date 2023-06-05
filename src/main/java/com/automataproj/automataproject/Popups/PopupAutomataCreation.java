package com.automataproj.automataproject.Popups;

import com.automataproj.automataproject.Home;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class PopupAutomataCreation {
    public static PopupAutomataReturn display() throws IOException {
        Stage window = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("newAutomataPopup.fxml"));

        window.setTitle("New Automata");
        window.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(fxmlLoader.load(), 750, 400);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();
        return fxmlLoader.<PopupAutomataController>getController().returnObj;
    }
}
