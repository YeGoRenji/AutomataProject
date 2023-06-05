package com.automataproj.automataproject;

import com.automataproj.automataproject.Metier.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import java.io.IOException;
import java.util.List;

public class Home extends Application {
    static AFD af = new AFD();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("homeView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
//        af.setIdAutomate("Automate 1");
//        fxmlLoader.<HomeController>getController().fillVbox(List.of(af));
        stage.setTitle("Théorie de la calculabilité");
//        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("newAutomataPopup.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 750, 400);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}