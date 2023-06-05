package presentation;

import java.awt.image.BufferedImage;

import com.automataproj.automataproject.Metier.AFD;

import com.automataproj.automataproject.Metier.AutomateFini;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShowResultAutomaton {
	
public ShowResultAutomaton(AutomateFini M, Stage stage, String title) {
	    
    	VBox root = new VBox();
    	root.setStyle("-fx-background-color: white;");
    	Scene scene = new Scene(root, 1280, 720);
    	
    	Pane p = new Pane();
    	p.setStyle("-fx-background-color: white;");
		 BufferedImage img = M.getAutomateImage().toImage();
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
		 if (M instanceof AFD)
			 reconnaissanceBtn.setOnAction(event -> {
				 if(((AFD) M).reconnaissanceMot(mot.getText()))
					 output.setText("True");
				 else
					 output.setText("False");
			 });
		 else
			 reconnaissanceBtn.setDisable(true);
         reconnaissance.getChildren().addAll(mot,reconnaissanceBtn, output);
         reconnaissance.setSpacing(20);
         reconnaissance.setPadding(new Insets(50));;
         
         root.getChildren().add(reconnaissance);
         
         stage.setTitle(title);
         stage.setScene(scene);
         stage.show();
    }

}
