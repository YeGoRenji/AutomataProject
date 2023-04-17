package com.automataproj.automataproject;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class HelloController {
	
    @FXML
    public TextField textToOutput;
    @FXML
    public Canvas canvas;
    @FXML
    private Label welcomeText;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText(welcomeText.getText() + textToOutput.getText());
    }

}