package com.automataproj.automataproject;

import com.automataproj.automataproject.Metier.Etat;
import com.automataproj.automataproject.Metier.TypeEtat;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Etat s1 = new Etat("s1", TypeEtat.INIT);
        Etat s2 = new Etat("s2", TypeEtat.FINAL);

        s1.addTransition("0", s2);
        s1.addTransition("0", s2);
        s1.addTransition("1", s1);

        System.out.println(s1);
//        launch(args);
    }
}