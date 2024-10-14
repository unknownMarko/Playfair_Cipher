package com.playfaircipher.playfair_cipher;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Controller {

    @FXML
    private Button btn_decrypt;

    @FXML
    private Button btn_encrypt;

    @FXML
    private TextField field_alphabet;

    @FXML
    private TextField field_input;

    @FXML
    private TextField field_input_filtered;

    @FXML
    private TextField field_key;

    @FXML
    private TextField field_output;

    @FXML
    private GridPane grid;

    @FXML
    private Label gridLabel_0;

    @FXML
    private Label gridLabel_1;

    @FXML
    private Label gridLabel_2;

    @FXML
    private Label gridLabel_3;

    @FXML
    private Label gridLabel_4;

    @FXML
    private Label gridLabel_5;

    @FXML
    private Label gridLabel_6;

    @FXML
    private Label gridLabel_7;

    @FXML
    private Label gridLabel_8;

    @FXML
    private Label gridLabel_9;

    @FXML
    private Label gridLabel_10;

    @FXML
    private Label gridLabel_11;

    @FXML
    private Label gridLabel_12;

    @FXML
    private Label gridLabel_13;

    @FXML
    private Label gridLabel_14;

    @FXML
    private Label gridLabel_15;

    @FXML
    private Label gridLabel_16;

    @FXML
    private Label gridLabel_17;

    @FXML
    private Label gridLabel_18;

    @FXML
    private Label gridLabel_19;

    @FXML
    private Label gridLabel_20;

    @FXML
    private Label gridLabel_21;

    @FXML
    private Label gridLabel_22;

    @FXML
    private Label gridLabel_23;

    @FXML
    private Label gridLabel_24;

    void fillGrid() {
        for (int i = 0; i < 25; i++) {
            Label currentLabel = (Label) grid.lookup("#gridLabel_" + i);
            String matrixData = PlayfairCipherLogic.parseKey(field_key.getText()+PlayfairCipherLogic.alphabetCzech);
            if (currentLabel != null) {
                currentLabel.setText(String.valueOf(matrixData.charAt(i)));
            }
        }    
    }

    @FXML
    void handleInput() {
        fillGrid();
    }

    @FXML
    void handleKey() {
        fillGrid();
    }

}
