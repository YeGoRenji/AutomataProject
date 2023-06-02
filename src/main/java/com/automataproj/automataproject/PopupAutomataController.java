package com.automataproj.automataproject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PopupAutomataController implements Initializable {
    @FXML
    public TextField alphabets;

    @FXML
    public TextField automataName;

    @FXML
    RadioButton afdButton;

    @FXML
    RadioButton afndButton;

    @FXML
    public Button cancelBtn;

    public PopupAutomataReturn returnObj;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        returnObj = new PopupAutomataReturn();
        Platform.runLater(() -> cancelBtn.requestFocus());
    }

    public void onClickCancel()
    {
        Stage stage = (Stage)cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void onClickCreer() {
        String alphabetsText = alphabets.getText().strip();
        String automateNameText = automataName.getText().strip();
        if (automateNameText.length() == 0 || alphabetsText.length() == 0)
        {
            new Alert(Alert.AlertType.ERROR, "L'un des champs est vide !").show();
            return;
        }
        // Alphabets Parsing
        alphabetsText =  alphabetsText.replaceAll("\\s+", " ");
        List<String> arrAlpha = List.of(alphabetsText.split(" "));
        returnObj.alphabets = new ArrayList<>();
        for (String alphaStr : arrAlpha)
            returnObj.alphabets.add(alphaStr.charAt(0));
        //
        returnObj.automataName = automateNameText;
        returnObj.isAFND = afndButton.isSelected();
        returnObj.isOk = true;
        System.out.println(returnObj);
        Stage stage = (Stage)cancelBtn.getScene().getWindow();
        stage.close();
    }
}
