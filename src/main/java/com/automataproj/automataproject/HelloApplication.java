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

public class HelloApplication extends Application {
    static AutomateFini af = new AFD(); // pour test
    static AutomateFini primeM = new AFD(); // pour test
    static AutomateFini afd = new AFD(); // pour test
//    static AFND afn = new AFND();
    static AFD afDeterminisation = new AFD();

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
        stage.setTitle("Théorie de la calculabilité");
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
        
        if (af instanceof AFND)
        	afDeterminisation = ((AFND) af).determiniser();
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
        	AFD imgDeterminisation = afDeterminisation.imageMirror().determiniser();
        	new ShowResultAutomaton(imgDeterminisation, stageMirror, "Image miroir");
        });

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

//        af.ajouterEtat("1", TypeEtat.INIT);
//        af.ajouterEtat("2", TypeEtat.MID);
//        af.ajouterEtat("3", TypeEtat.FINAL);
//        af.setAlphabet(List.of('a', 'b'));
//        af.ajouterTransition("1", 'a', "2");
//        af.ajouterTransition("2", 'b', "3");
//        af.ajouterTransition("3", 'a', "3");
//        af.ajouterTransition("3", 'b', "3");
        
        // Test Pour Automate produit
        af.ajouterEtat("0", TypeEtat.INIT);
        af.ajouterEtat("1", TypeEtat.FINAL);
        af.setAlphabet(List.of('0', '1'));
        af.ajouterTransition("0", '1', "1");
        af.ajouterTransition("1", '1', "0");
        af.ajouterTransition("0", '0', "0");
        af.ajouterTransition("1", '0', "1");
        
        primeM.ajouterEtat("0", TypeEtat.INIT);
        primeM.ajouterEtat("1", TypeEtat.FINAL);
        primeM.setAlphabet(List.of('0', '1'));
        primeM.ajouterTransition("0", '0', "1");
        primeM.ajouterTransition("1", '1', "0");
        primeM.ajouterTransition("0", '1', "0");
        primeM.ajouterTransition("1", '0', "1");
        
        // Test Image Miroir
        afd.setAlphabet(List.of('a','b'));
        afd.ajouterEtat("0", TypeEtat.INIT);
        afd.ajouterEtat("1",TypeEtat.MID);
        afd.ajouterEtat("2", TypeEtat.FINAL);
        afd.ajouterTransition("0",'b',"0");
        afd.ajouterTransition("0",'a',"1");
        afd.ajouterTransition("1",'b',"2");
        afd.ajouterTransition("2",'a',"2");
        afd.ajouterTransition("2",'b',"2");

//        af.ajouterEtat("s1", TypeEtat.FINAL);
//        af.ajouterEtat("s2", TypeEtat.INIT);
//        af.setAlphabet(List.of('0', '1'));
//        af.ajouterTransition("s1", '0', "s2");
//        af.ajouterTransition("s2", '0', "s1");
//        af.ajouterTransition("s1", '1', "s1");
//        af.ajouterTransition("s2", '1', "s2");
//        af.printAutomate("automatePNG/test3.png");

//       af.ajouterEtat("1", TypeEtat.INIT_FINAL);
//       af.ajouterEtat("2", TypeEtat.FINAL);
//       af.ajouterEtat("3", TypeEtat.INIT);
//       af.setAlphabet(List.of('a', 'b'));
//       af.ajouterTransition("1", 'a', "2");
//       af.ajouterTransition("1", 'a', "1");
//       af.ajouterTransition("1", 'b', "2");
//       af.ajouterTransition("2", 'a', "1");
//       af.ajouterTransition("2", 'b', "2");
//       af.ajouterTransition("2", 'b', "3");
//       af.ajouterTransition("3", 'a', "2");
       
       
//        af.ajouterEtat("0", TypeEtat.INIT);
//       af.ajouterEtat("1", TypeEtat.MID);
//       af.ajouterEtat("2", TypeEtat.FINAL);
//       af.setAlphabet(List.of('a', 'b'));
//       af.ajouterTransition("0", 'a', "0");
//       af.ajouterTransition("0", 'b', "0");
//       af.ajouterTransition("0", 'a', "1");
//       af.ajouterTransition("1", 'b', "2");
//       af.ajouterTransition("2", 'b', "2");
//       af.ajouterTransition("2", 'a', "2");
       
       //af.ajouterTransition("3", AutomateFini.EPSILON, "3");
        //List <String> tous = af.generateAcceptedWords(af,4);
        //System.out.println("tous les mots acceptés :"+ tous);



//        System.out.println(af.reconnaissanceMot("00010"));
         
//        af.printAutomate("automatePNG/test2.png");
//        af.printAutomate("automatePNG/test.png");
//         af.printAutomate("automatePNG/test3.png");
        

        launch(args);
    }
}