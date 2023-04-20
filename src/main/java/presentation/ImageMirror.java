package presentation;

import java.awt.image.BufferedImage;

import com.automataproj.automataproject.Metier.AFD;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ImageMirror {

	public ImageMirror (AFD M, Stage stage) {
		VBox root = new VBox();
    	root.setStyle("-fx-background-color: white;");
    	Scene scene = new Scene(root, 1280, 720);
    	
    	Pane p = new Pane();
    	p.setStyle("-fx-background-color: white;");
    	 BufferedImage img = M.imageMirror().getAutomateImage().toImage();
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
        	 Alert a = new Alert(AlertType.WARNING,"J'ai besoin de la fonction de la déterminisation !");
     		 a.show();
//        	 if(M.imageMirror.reconnaissanceMot(mot.getText()))
//        		 output.setText("True");
//        	 else
//        		 output.setText("False");
         });
         reconnaissance.getChildren().addAll(mot,reconnaissanceBtn, output);
         reconnaissance.setSpacing(20);
         reconnaissance.setPadding(new Insets(50));;
         
         root.getChildren().add(reconnaissance);
         
         stage.setTitle("Image Miroir");
         stage.setScene(scene);
         stage.show();
	}
}
