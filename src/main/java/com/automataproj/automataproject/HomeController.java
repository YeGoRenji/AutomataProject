package com.automataproj.automataproject;

import com.automataproj.automataproject.Components.AutomataPane;
import com.automataproj.automataproject.Metier.AFD;
import com.automataproj.automataproject.Metier.AutomateFini;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private ListView<AutomateFini> automateList;

    List<AutomateFini> listAutomates;

//    public void fillVbox(List<AutomateFini> list)
//    {
//        for (AutomateFini automate : list)
//        {
//            String type;
//            if (automate instanceof AFD) type = "AFD"; else type = "AFND";
//            Text t = new Text(automate.getIdAutomate() + "      (" + type + ")");
//            automateList.getItems().add(automate);
//        }
//    }

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
//        this.fillVbox(listAutomates);
    }

    public void onModifyAutomata(ActionEvent event) {
        if (automateList.getSelectionModel().getSelectedItem() == null)
            return;
        try {
            FXMLLoader loader = new FXMLLoader(Home.class.getResource("automataCreation.fxml"));
            AutomateFini selected = automateList.getSelectionModel().getSelectedItem();
            ((Stage)automateList.getScene().getWindow()).setTitle("Creating Automate : " + selected.getIdAutomate() + " " + (selected instanceof AFD ? "(AFND)" : "(AFD)"));
            automateList.getScene().setRoot(loader.load());
            loader.<AutomataCreation>getController().setAf(selected);
            loader.<AutomataCreation>getController().setListAutomates(listAutomates);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}