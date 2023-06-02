package com.automataproj.automataproject;

import com.automataproj.automataproject.Components.AutomataPane;
import com.automataproj.automataproject.Metier.AFD;
import com.automataproj.automataproject.Metier.AutomateFini;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private ListView<Text> automateList;

    List<AutomateFini> listAutomates;

    public void fillVbox(List<AutomateFini> list)
    {
        for (AutomateFini automate : list)
        {
            String type;
            if (automate instanceof AFD) type = "AFD"; else type = "AFND";
            Text t = new Text(automate.getIdAutomate() + "      (" + type + ")");
            automateList.getItems().add(t);
        }
    }

    public void onNewAutomata(Event event)
    {
        try {
            PopupAutomataReturn returnObj =  PopupAutomataCreation.display();
            if (!returnObj.isOk)
                return;
            FXMLLoader loader = new FXMLLoader(Home.class.getResource("automataCreation.fxml"));
            automateList.getScene().setRoot(loader.load());
            loader.<AutomataCreation>getController().setAf(returnObj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        this.fillVbox(listAutomates);
    }
}