package com.automataproj.automataproject;

import com.automataproj.automataproject.Metier.AFD;
import com.automataproj.automataproject.Metier.AutomateFini;
import com.automataproj.automataproject.Metier.Etat;
import com.automataproj.automataproject.Metier.TypeEtat;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("automataCreation.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
//        Pane p = new Pane();
//        Circle c = new Circle(100,100,100, Paint.valueOf("#FF5500"));
//        c.addEventHandler(Eve);
//
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setScene(scene);
        stage.show();
        Pane p = (Pane) scene.lookup("#paneId");
//        p.getChildren().add(new)
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        gc.setFill(Paint.valueOf("#FF5C00"));
        p.setOnMouseClicked(event -> {
            System.out.println(event.getX() + " " + event.getY());
            p.getChildren().add(new Circle(event.getX(), event.getY(), 20, Paint.valueOf("#FF5C00")));
        });

//        canvas.setOnMouseClicked(event -> {
//            canvas.getGraphicsContext2D().fillOval(event.getX() - 25, event.getY() - 25, 50, 50);
//        });
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
//
//        System.out.println(af.reconnaissanceMot("0001"));

        launch(args);
    }
}