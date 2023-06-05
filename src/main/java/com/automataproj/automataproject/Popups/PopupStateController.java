package com.automataproj.automataproject.Popups;
import com.automataproj.automataproject.Comps.LimitedTextField;
import com.automataproj.automataproject.Metier.TypeEtat;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PopupStateController implements Initializable {
    @FXML
    public LimitedTextField stateName;

    @FXML
    public CheckBox initialCheck;

    @FXML
    public CheckBox finalCheck;

    @FXML
    public Button cancelBtn;

    public PopupStateReturn returnObj;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        returnObj = new PopupStateReturn();
        Platform.runLater(() -> cancelBtn.requestFocus());
    }

    public void onClickCancel()
    {
        Stage stage = (Stage)cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void onClickAjouter()
    {
        Stage stage = (Stage)cancelBtn.getScene().getWindow();
        returnObj.isOK = true;
        returnObj.stateName = stateName.getText();
        if (initialCheck.isSelected() && finalCheck.isSelected())
            returnObj.type = TypeEtat.INIT_FINAL;
        else if (initialCheck.isSelected())
            returnObj.type = TypeEtat.INIT;
        else if (finalCheck.isSelected())
            returnObj.type = TypeEtat.FINAL;
        stage.close();
    }
}
