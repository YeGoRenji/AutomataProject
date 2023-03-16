package com.automataproj.automataproject;

import com.automataproj.automataproject.Metier.AFD;
import com.automataproj.automataproject.Metier.AutomateFini;
import com.automataproj.automataproject.Metier.Etat;
import com.automataproj.automataproject.Metier.TypeEtat;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

//        AFD af = new AFD();
//
//        af.ajouterEtat("s1", TypeEtat.INIT_FINAL);
//        af.ajouterEtat("s2", TypeEtat.MID);
//        af.setAlphabet(List.of('0', '1'));
//        af.ajouterTransition("s1", '0', "s2");
//        af.ajouterTransition("s2", '0', "s1");
//        af.ajouterTransition("s1", '1', "s1");
//        af.ajouterTransition("s2", '1', "s2");
//
//        System.out.println(af.reconnaissanceMot("00001"));

        launch(args);
    }
}