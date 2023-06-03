package com.automataproj.automataproject;

import com.automataproj.automataproject.Metier.AFD;
import com.automataproj.automataproject.Metier.AFND;
import com.automataproj.automataproject.Metier.AutomateFini;
import guru.nidi.graphviz.engine.Graphviz;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import presentation.ShowResultAutomaton;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AutomataCreation implements Initializable {
    @FXML
    private Pane mainPane;

    private AutomateFini af;

    private ImageView imgview;

    @FXML
    private Button complementId;

    @FXML
    private Button mirrorId;

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

    @FXML
    public void onClickComplement(ActionEvent event) throws IOException {
        if (af.getEtats().size() == 0)
        {
            new Alert(Alert.AlertType.ERROR, "Automate vide !").show();
            return;
        }
        Stage stageComplement = new Stage();
        new ShowResultAutomaton(((AFD) af).ComplementAFD(), stageComplement, "Complémentaire");
    }

    @FXML
    public void onClickMirror(ActionEvent event) throws IOException {
        if (af.getEtats().size() == 0)
        {
            new Alert(Alert.AlertType.ERROR, "Automate vide !").show();
            return;
        }
        Stage stageMirror = new Stage();
        AFD imgDeterminisation = ((AFD) af).imageMirror().determiniser();
        new ShowResultAutomaton(imgDeterminisation, stageMirror, "Image miroir");
    }

    private void repaint() {
        imgview.setImage(SwingFXUtils.toFXImage(af.getAutomateImage().toImage(), null));
    }

    public void setAf(PopupAutomataReturn returnObj) {
        af = returnObj.isAFND ? new AFND() : new AFD();
        af.setAlphabet(returnObj.alphabets);
        af.setIdAutomate(returnObj.automataName);
        Platform.runLater(() -> ((Stage) mainPane.getScene().getWindow()).setTitle("Creating Automate : " + returnObj.automataName + " " + (returnObj.isAFND ? "(AFND)" : "(AFD)")));
        if (returnObj.isAFND)
        {
            complementId.setDisable(true);
            mirrorId.setDisable(true);
        }
        repaint();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        imgview = new ImageView();
        imgview.setPreserveRatio(true);
        imgview.fitWidthProperty().bind(mainPane.widthProperty());
        imgview.fitHeightProperty().bind(mainPane.heightProperty());
        mainPane.getChildren().add(imgview);
    }

}
