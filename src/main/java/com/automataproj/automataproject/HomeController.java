package com.automataproj.automataproject;

import com.automataproj.automataproject.Metier.AFD;
import com.automataproj.automataproject.Metier.AutomateFini;
import com.automataproj.automataproject.Popups.PopupAutomataCreation;
import com.automataproj.automataproject.Popups.PopupAutomataReturn;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import presentation.showAutomataProduct;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class    HomeController implements Initializable {
    @FXML
    public Pane previewPane;

    @FXML
    private ListView<AutomateFini> automateList;

    List<AutomateFini> listAutomates;

    private ImageView imgview;

    public void onNewAutomata(Event event)
    {
        try {
            PopupAutomataReturn returnObj =  PopupAutomataCreation.display();
            if (!returnObj.isOk)
                return;
            FXMLLoader loader = new FXMLLoader(Home.class.getResource("automataCreation.fxml"));
            automateList.getScene().setRoot(loader.load());
            loader.<AutomataCreation>getController().setAfOnCreation(returnObj);
            loader.<AutomataCreation>getController().setListAutomates(listAutomates);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listAutomates = new ArrayList<>();

        Platform.runLater(() -> {
            for (AutomateFini automate : listAutomates)
            {
//                String type;
//                if (automate instanceof AFD) type = "AFD"; else type = "AFND";
//                Text t = new Text(automate.getIdAutomate() + "      (" + type + ")");
                automateList.getItems().add(automate);
            }
        });
        imgview = new ImageView();
        imgview.setPreserveRatio(true);
        imgview.fitWidthProperty().bind(previewPane.widthProperty());
        imgview.fitHeightProperty().bind(previewPane.heightProperty());
        previewPane.getChildren().add(imgview);

        automateList.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            AutomateFini selectedAf = automateList.getItems().get(newValue.intValue());
            repaint(selectedAf);
        });
    }

    private void repaint(AutomateFini af) {
        imgview.setImage(SwingFXUtils.toFXImage(af.getAutomateImage().toImage(), null));
    }

    public void onModifyAutomata(ActionEvent event) {
        if (automateList.getSelectionModel().getSelectedItem() == null)
            return;
        try {
            FXMLLoader loader = new FXMLLoader(Home.class.getResource("automataCreation.fxml"));
            AutomateFini selected = automateList.getSelectionModel().getSelectedItem();
            ((Stage)automateList.getScene().getWindow()).setTitle("Creating Automate : " + selected.getIdAutomate() + " " + (selected instanceof AFD ? "(AFD)" : "(AFND)"));
            automateList.getScene().setRoot(loader.load());
            loader.<AutomataCreation>getController().setAf(selected);
            loader.<AutomataCreation>getController().setListAutomates(listAutomates);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onUnionAutomata(ActionEvent actionEvent) {
        Stage stageProduct = new Stage();

//        new showAutomataProduct(afDeterminisation, (AFD) primeM, stageProduct, "union");
    }

    public void onIntersectAutomata(ActionEvent actionEvent) {

    }
}