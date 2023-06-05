package com.automataproj.automataproject.Comps;

import com.automataproj.automataproject.Metier.AutomateFini;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
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
