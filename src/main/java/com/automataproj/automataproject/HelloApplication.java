package com.automataproj.automataproject;

import com.automataproj.automataproject.Metier.*;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    static AFND af = new AFND();

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

        // To Print Automate on Screen !
        BufferedImage img = af.getAutomateImage().toImage();
        ImageView imgview = new ImageView(SwingFXUtils.toFXImage(img, null));
        imgview.setPreserveRatio(true);
        imgview.fitWidthProperty().bind(p.widthProperty());
        imgview.fitHeightProperty().bind(p.heightProperty());
        p.getChildren().add(imgview);

//        p.setOnMouseClicked(event -> {
//            System.out.println(event.getX() + " " + event.getY());
//            p.getChildren().add(new Circle(event.getX(), event.getY(), 20, Paint.valueOf("#FF5C00")));
//        });

//        canvas.setOnMouseClicked(event -> {
//            canvas.getGraphicsContext2D().fillOval(event.getX() - 25, event.getY() - 25, 50, 50);
//        });
    }

    public static void main(String[] args) throws IOException {

        af.ajouterEtat("s1", TypeEtat.INIT);
        af.ajouterEtat("s2", TypeEtat.FINAL);
        af.ajouterEtat("s3", TypeEtat.FINAL);
        af.setAlphabet(List.of('0', '1'));
        af.ajouterTransition("s1", '0', "s2");
        af.ajouterTransition("s2", '0', "s1");
        af.ajouterTransition("s1", '1', "s1");
        af.ajouterTransition("s2", '1', "s2");
        af.ajouterTransition("s3", '\0', "s3");

//        af.ajouterEtat("1", TypeEtat.INIT);
//        af.ajouterEtat("2", TypeEtat.MID);
//        af.ajouterEtat("3", TypeEtat.FINAL);
//        af.setAlphabet(List.of('a', 'b'));
//        af.ajouterTransition("1", 'a', "2");
//        af.ajouterTransition("2", 'b', "3");
//        af.ajouterTransition("3", 'a', "3");
//        af.ajouterTransition("3", 'b', "3");


        // System.out.println(af.reconnaissanceMot("0001"));

//        af.printAutomate("automatePNG/test2.png");
//        af.printAutomate("automatePNG/test.png");
        launch(args);
    }
}