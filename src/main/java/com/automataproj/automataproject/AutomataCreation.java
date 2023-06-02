package com.automataproj.automataproject;

import com.automataproj.automataproject.Metier.AFD;
import com.automataproj.automataproject.Metier.AFND;
import com.automataproj.automataproject.Metier.AutomateFini;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AutomataCreation implements Initializable {
    @FXML
    private Pane mainPane;

    private AutomateFini af;

    private ImageView imgview;

    @FXML
    public void onClickState(ActionEvent event) throws IOException {
        PopupStateReturn stateReturn = PopupStateCreation.display();
        if (stateReturn != null && stateReturn.isOK && !stateReturn.stateName.isEmpty()) {
            String errorMsg = af.ajouterEtat(stateReturn.stateName, stateReturn.type);
            if (errorMsg != null)
                new Alert(Alert.AlertType.ERROR, errorMsg).show();
            else
                repaint();
        } else
            System.out.println("it is null !");
    }

    @FXML
    public void onClickTransition(ActionEvent event) throws IOException {
        PopupTransitionReturn stateReturn = PopupTransitionCreation.display(af);
        if (stateReturn != null && stateReturn.isOK) {
            String errorMsg = af.ajouterTransition(stateReturn.startState, stateReturn.symbol, stateReturn.destState);
            if (errorMsg != null)
                new Alert(Alert.AlertType.ERROR, errorMsg).show();
            else
                repaint();
        } else
            System.out.println("it is null !");
    }

    private void repaint() {
        imgview.setImage(SwingFXUtils.toFXImage(af.getAutomateImage().toImage(), null));
    }

    public void setAf(PopupAutomataReturn returnObj) {
        af = returnObj.isAFND ? new AFND() : new AFD();
        af.setAlphabet(returnObj.alphabets);
        af.setIdAutomate(returnObj.automataName);

        Platform.runLater(() -> ((Stage) mainPane.getScene().getWindow()).setTitle("Creating Automate : " + returnObj.automataName + " " + (returnObj.isAFND ? "(AFND)" : "(AFD)")));
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
//        af.setIdAutomate("Automate 1");
//        af.ajouterEtat("1", TypeEtat.INIT_FINAL);
//        af.ajouterEtat("2", TypeEtat.FINAL);
//        af.ajouterEtat("3", TypeEtat.MID);
//        af.ajouterEtat("4", TypeEtat.MID);
//        af.ajouterEtat("5", TypeEtat.MID);
//        af.ajouterEtat("7", TypeEtat.MID);
//        af.ajouterEtat("6", TypeEtat.MID);
//        af.setAlphabet(List.of('0','1'));
//        af.ajouterTransition("1", 'a', "2");
//        af.ajouterTransition("1", 'b', "5");
//        af.ajouterTransition("2", 'a', "2");
//        af.ajouterTransition("2", 'b', "4");
//        af.ajouterTransition("3", 'a', "3");
//        af.ajouterTransition("3", 'b', "2");
//        af.ajouterTransition("4", 'a', "5");
//        af.ajouterTransition("4", 'b', "3");
//        af.ajouterTransition("5", 'a', "4");
//        af.ajouterTransition("5", 'b', "6");
//        af.ajouterTransition("6", 'a', "6");
//        af.ajouterTransition("6", 'b', "1");
//        af.ajouterTransition("7", 'a', "5");

//        BufferedImage img = af.getAutomateImage().toImage();
        imgview = new ImageView();
        imgview.setPreserveRatio(true);
        imgview.fitWidthProperty().bind(mainPane.widthProperty());
        imgview.fitHeightProperty().bind(mainPane.heightProperty());
        mainPane.getChildren().add(imgview);
    }
}
