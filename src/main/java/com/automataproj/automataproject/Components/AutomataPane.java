package com.automataproj.automataproject.Components;

import com.automataproj.automataproject.Metier.AutomateFini;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.spreadsheet.Grid;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
//import org.kordamp.bootstrapfx.scene.layout.Panel;

public class AutomataPane extends AnchorPane {

    @FXML
    GridPane gridPane;

    public AutomataPane(AutomateFini af)
    {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AutomataPane.fxml"));
//        fxmlLoader.setRoot(this);
//        fxmlLoader.setController(AutomataPane.this);

        try {
            fxmlLoader.load();
        }
        catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
    }

}
