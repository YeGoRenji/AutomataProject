package com.automataproj.automataproject;

import com.automataproj.automataproject.Metier.*;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import presentation.ShowResultAutomaton;
import presentation.showAutomataProduct;

import org.kordamp.bootstrapfx.BootstrapFX;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class HelloApplication {
    static AutomateFini af = new AFD(); // pour test
    static AutomateFini primeM = new AFD(); // pour test
    static AutomateFini afd = new AFD(); // pour test
//    static AFND afn = new AFND();
    static AFD afDeterminisation = new AFD();

//    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("automataCreation.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
//        Pane p = new Pane();
//        Circle c = new Circle(100,100,100, Paint.valueOf("#FF5500"));
//        c.addEventHandler(Eve);
//
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setTitle("Théorie de la calculabilité");
        stage.setScene(scene);
        stage.show();
//        Pane p = (Pane) scene.lookup("#paneId");
//
//        // To Print Automate on Screen !
//        BufferedImage img = af.getAutomateImage().toImage();
//        ImageView imgview = new ImageView(SwingFXUtils.toFXImage(img, null));
//        imgview.setPreserveRatio(true);
//        imgview.fitWidthProperty().bind(p.widthProperty());
//        imgview.fitHeightProperty().bind(p.heightProperty());
//        p.getChildren().add(imgview);

//        p.setOnMouseClicked(event -> {
//            System.out.println(event.getX() + " " + event.getY());
//            p.getChildren().add(new Circle(event.getX(), event.getY(), 20, Paint.valueOf("#FF5C00")));
//        });

//        canvas.setOnMouseClicked(event -> {
//            canvas.getGraphicsContext2D().fillOval(event.getX() - 25, event.getY() - 25, 50, 50);
//        });
        
        if (af instanceof AFND)
        	afDeterminisation = ((AFND) af).determiniser_2();
        else
        	afDeterminisation = (AFD) af;
        
        Button unionBtn = (Button) scene.lookup("#unionId");
        unionBtn.setOnAction(event -> {
        	Stage stageProduct = new Stage();    	
    		new showAutomataProduct(afDeterminisation, (AFD) primeM, stageProduct, "union");
        });
        
        Button intersectBtn = (Button) scene.lookup("#intersectId");
        intersectBtn.setOnAction(event -> {
        	Stage stageProduct = new Stage();    	
    		new showAutomataProduct(afDeterminisation, (AFD) primeM, stageProduct, "intersect");
        });
        
        
        Button complementBtn = (Button) scene.lookup("#complementId");
        complementBtn.setOnAction(event -> {
        	Stage stageComplement = new Stage();
        	new ShowResultAutomaton(afDeterminisation.ComplementAFD(), stageComplement, "Complémentaire");
        });
        
        Button mirrorBtn = (Button) scene.lookup("#mirrorId");
        mirrorBtn.setOnAction(event -> {
        	Stage stageMirror = new Stage();
        AFD imgDeterminisation = afDeterminisation.imageMirror().determiniser_2();
        	new ShowResultAutomaton(imgDeterminisation, stageMirror, "Image miroir");
        });

    }
    

}