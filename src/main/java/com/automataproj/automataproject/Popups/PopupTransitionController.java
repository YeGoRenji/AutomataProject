package com.automataproj.automataproject.Popups;

import com.automataproj.automataproject.Metier.AutomateFini;
import com.automataproj.automataproject.Metier.Etat;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class PopupTransitionController implements Initializable {

    @FXML
    private ComboBox<Etat> startStateCb;

    @FXML
    private ComboBox<Etat> destStateCb;

    @FXML
    private ComboBox<Character> symbolCb;
    @FXML
    public Button cancelBtn;

    public PopupTransitionReturn returnObj;

    private AutomateFini af;

    public void setAf(AutomateFini af) {
        this.af = af;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        returnObj = new PopupTransitionReturn();
        Platform.runLater(() -> {
            cancelBtn.requestFocus();
            symbolCb.setItems(FXCollections.observableList(af.getAlphabet()));
            startStateCb.setItems(FXCollections.observableList(af.getEtats()));
            destStateCb.setItems(FXCollections.observableList(af.getEtats()));
            StringConverter<Etat> sc = new StringConverter<>() {
                @Override
                public String toString(Etat object) {
                    return object.getIdEtat();
                }

                @Override
                public Etat fromString(String string) {
                    return af.findEtat(string);
                }
            };
            startStateCb.setConverter(sc);
            destStateCb.setConverter(sc);
        });
    }

    public void onClickCancel()
    {
        Stage stage = (Stage)cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void onClickAjouter()
    {
        Stage stage = (Stage)cancelBtn.getScene().getWindow();
        if (startStateCb.getValue() != null && destStateCb.getValue() != null && symbolCb.getValue() != null)
        {
            returnObj.isOK = true;
            returnObj.startState = startStateCb.getValue().getIdEtat();
            returnObj.destState = destStateCb.getValue().getIdEtat();
            returnObj.symbol = symbolCb.getValue();
        }
        stage.close();
    }
}
