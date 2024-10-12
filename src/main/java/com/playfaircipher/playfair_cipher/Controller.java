package com.playfaircipher.playfair_cipher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    private Button btn_decrypt;

    @FXML
    private Button btn_encrypt;

    @FXML
    private Label error_msg_alphabet;

    @FXML
    private Label error_msg_input;

    @FXML
    private Label error_msg_key_a;

    @FXML
    private Label error_msg_key_b;

    @FXML
    private TextField field_alphabet;

    @FXML
    private TextField field_input;

    @FXML
    private TextField field_input1;

    @FXML
    private TextField field_key_a;

    @FXML
    private TextField field_output;

    @FXML
    private Text text_input12;

    @FXML
    void handleCheckTextFieldKeyA(KeyEvent event) {

    }

    @FXML
    void handleCheckTextInput(KeyEvent event) {

    }

    @FXML
    void handleDecryptButton(ActionEvent event) {

    }

    @FXML
    void handleEncryptButton(ActionEvent event) {

    }

    @FXML
    void handleUpdateFieldAlphabet(KeyEvent event) {

    }

}
