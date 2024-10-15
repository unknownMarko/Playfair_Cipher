package com.playfaircipher.playfair_cipher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class Controller {

    char[][] matrix = new char[5][5];

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
    private Label gridLabel_2;

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
    private RadioButton radio_czech_alp;

    @FXML
    private RadioButton radio_slovak_alp;


    ToggleGroup radioToggle = new ToggleGroup();

    //Uvodna funkcia pri zobrazeni GUI
    @FXML
    void initialize() {
        radio_czech_alp.setToggleGroup(radioToggle);
        radio_slovak_alp.setToggleGroup(radioToggle);
        field_alphabet.setText(PlayfairCipherLogic.alphabetSlovak);
        fillGrid(field_alphabet.getText());
    }

    //Spravuje zmenu jazyka (abecedy)
    @FXML
    void handleRadioSlovak() {
        field_alphabet.setText(PlayfairCipherLogic.alphabetSlovak);
        fillGrid(field_alphabet.getText());
    }

    //Spravuje zmenu jazyka (abecedy)
    @FXML
    void handleRadioCzech() {
        field_alphabet.setText(PlayfairCipherLogic.alphabetCzech);
        fillGrid(field_alphabet.getText());
    }

    void fillGrid(String alphabet) {
        for (int i = 0; i < 25; i++) {
            Label currentLabel = (Label) grid.lookup("#gridLabel_" + i);
            //TODO TEST THIS
            String matrixData = PlayfairCipherLogic.parseKey(field_key.getText() + alphabet);
            //TODO TEST THIS
            matrix = PlayfairCipherLogic.updateMatrix(matrixData, matrix, alphabet);
            if (currentLabel != null) {
                currentLabel.setText(String.valueOf(matrixData.charAt(i)));
            }
        }
    }

    @FXML
    void handleDecryptButton(KeyEvent event) {

    }

    @FXML
    void handleEncryptButton(ActionEvent event) {

    }

    @FXML
    void handleInput(KeyEvent event) {
        field_input_filtered.setText(FilterInput.formatInput(field_input.getText()));
    }

    @FXML
    void handleKey(KeyEvent event) {
        fillGrid(field_alphabet.getText());
    }



}
