package com.automataproj.automataproject;

import com.automataproj.automataproject.Metier.*;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    static AFD af = new AFD();

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
        
        Button complementBtn = (Button) scene.lookup("#complementId");
        complementBtn.setOnAction(event -> {
        	Stage stageComplement = new Stage();
        	complementAFDbtn(af, stageComplement);
        });


    }
    
    private void complementAFDbtn(AFD M, Stage stage) {
    
    	VBox root = new VBox();
    	root.setStyle("-fx-background-color: white;");
    	Scene scene = new Scene(root, 1280, 720);
    	
    	Pane p = new Pane();
    	p.setStyle("-fx-background-color: white;");
    	 BufferedImage img = M.ComplementAFD().getAutomateImage().toImage();
         ImageView imgview = new ImageView(SwingFXUtils.toFXImage(img, null));
         imgview.setPreserveRatio(true);
         imgview.fitWidthProperty().bind(p.widthProperty());
         imgview.fitHeightProperty().bind(p.heightProperty());
         p.getChildren().add(imgview);
         root.getChildren().add(p);
         
         HBox reconnaissance = new HBox();
         reconnaissance.setStyle("-fx-background-color: white;");
         TextField mot = new TextField();
         Button reconnaissanceBtn = new Button("Reconnaissance");
         Label output = new Label();
         reconnaissanceBtn.setOnAction(event -> {
        	 if(M.ComplementAFD().reconnaissanceMot(mot.getText()))
        		 output.setText("True");
        	 else
        		 output.setText("False");
         });
         reconnaissance.getChildren().addAll(mot,reconnaissanceBtn, output);
         reconnaissance.setSpacing(20);
         reconnaissance.setPadding(new Insets(50));;
         
         root.getChildren().add(reconnaissance);
         
         stage.setTitle("Complémentaire");
         stage.setScene(scene);
         stage.show();
    }
    

    public static void main(String[] args) throws IOException {

//        af.ajouterEtat("s1", TypeEtat.INIT);
//        af.ajouterEtat("s2", TypeEtat.FINAL);
//        af.ajouterEtat("s3", TypeEtat.FINAL);
//        af.setAlphabet(List.of('0', '1'));
//        af.ajouterTransition("s1", '0', "s2");
//        af.ajouterTransition("s2", '0', "s1");
//        af.ajouterTransition("s1", '1', "s1");
//        af.ajouterTransition("s2", '1', "s2");
//        af.ajouterTransition("s3", '\0', "s3");

        af.ajouterEtat("1", TypeEtat.INIT);
        af.ajouterEtat("2", TypeEtat.MID);
        af.ajouterEtat("3", TypeEtat.FINAL);
        af.setAlphabet(List.of('a', 'b'));
        af.ajouterTransition("1", 'a', "2");
        af.ajouterTransition("2", 'b', "3");
        af.ajouterTransition("3", 'a', "3");
        af.ajouterTransition("3", 'b', "3");


//        System.out.println(af.reconnaissanceMot("00010"));
         
//        af.printAutomate("automatePNG/test2.png");
//        af.printAutomate("automatePNG/test.png");
//         af.printAutomate("automatePNG/test3.png");
        

        launch(args);
    }
}