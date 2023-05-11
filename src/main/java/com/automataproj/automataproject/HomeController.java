package com.automataproj.automataproject;

import com.automataproj.automataproject.Metier.AutomateFini;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class HomeController {
    @FXML
    private VBox automateList;

    List<AutomateFini> listAutomates;

    public void fillVbox(List<AutomateFini> list)
    {
        for (AutomateFini automate : list)
        {
            automateList.getChildren().add(new Text(automate.getIdAutomate()));
        }
    }

    public void onNewAutomata(Event event)
    {
        try {
            automateList.getScene().setRoot(new FXMLLoader(Home.class.getResource("automataCreation.fxml")).load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}