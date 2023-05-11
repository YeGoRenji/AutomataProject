package com.automataproj.automataproject;

import com.automataproj.automataproject.Metier.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import java.io.IOException;

public class Home extends Application {
    static AFD af = new AFD();

//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("homeView.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
//        ((HomeController)fxmlLoader.getController()).fillVbox(List.of(af));
//        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
//        stage.setScene(scene);
//        stage.show();
//    }

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("homeView.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("newTransitionPopup.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
//        Scene scene = new Scene(fxmlLoader.load(), 750, 400);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
//        af.setAlphabet(List.of('a','b'));
//        af.ajouterEtat("s1", TypeEtat.INIT);
//        fxmlLoader.<PopupTransitionController>getController().setAf(af);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}