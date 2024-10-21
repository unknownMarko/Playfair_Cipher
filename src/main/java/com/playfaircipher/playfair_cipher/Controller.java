//MIT License
//
//Copyright (c) 2024 Marko
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//The above copyright notice and this permission notice shall be included in all
//copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//SOFTWARE.

package com.playfaircipher.playfair_cipher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import static com.playfaircipher.playfair_cipher.FilterInput.formatInputTextDecrypt;

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
    private TextField field_input_filtered_encryption;

    @FXML
    private TextField field_input_filtered_decryption;

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

    @FXML
    private RadioButton radio_english_alp;

    @FXML
    private RadioButton radio_custom_alp;

    @FXML
    private Text alphabetMissing;


    ToggleGroup radioToggle = new ToggleGroup();

    //Uvodna funkcia pri zobrazeni GUIlty pleasures
    @FXML
    void initialize() {
        radio_czech_alp.setToggleGroup(radioToggle);
        radio_slovak_alp.setToggleGroup(radioToggle);
        radio_english_alp.setToggleGroup(radioToggle);
        radio_custom_alp.setToggleGroup(radioToggle);

        field_alphabet.setText(PlayfairCipherLogic.alphabetSlovak);
        fillGrid(field_alphabet.getText(), FilterInput.formatInputKey(field_key.getText()));

        alphabetMissing.setText(PlayfairCipherLogic.alphabetSlovakChange);

        btn_encrypt.setDisable(true);
        btn_decrypt.setDisable(true);
    }

    // TOOOO SLOVENSKO NAšE
    @FXML
    void handleRadioSlovak(ActionEvent event) {
        field_alphabet.setText(PlayfairCipherLogic.alphabetSlovak);
        fillGrid(field_alphabet.getText(), FilterInput.formatInputKey(field_key.getText()));
        alphabetMissing.setText(PlayfairCipherLogic.alphabetSlovakChange);
        field_output.setText("");
    }

    // CZECH RADIO OSTRAVA MENTIONED 🦅🦅🦅🦅
    @FXML
    void handleRadioCzech(ActionEvent event) {
        field_alphabet.setText(PlayfairCipherLogic.alphabetCzech);
        fillGrid(field_alphabet.getText(), FilterInput.formatInputKey(field_key.getText()));
        alphabetMissing.setText(PlayfairCipherLogic.alphabetCzechChange);
        field_output.setText("");
    }

    // rabota ne volk, walk - eto chodit 🐺
    @FXML
    void handleRadioEnglish(ActionEvent event) {
        field_alphabet.setText(PlayfairCipherLogic.alphabetEnglish);
        fillGrid(field_alphabet.getText(), FilterInput.formatInputKey(field_key.getText()));
        alphabetMissing.setText(PlayfairCipherLogic.alphabetEnglishChange);
        field_output.setText("");
    }

    @FXML
    void handleRadioCustom(ActionEvent event) {
        alphabetMissing.setText("? → ?");
        fillGrid(field_alphabet.getText(), FilterInput.formatInputKey(field_key.getText()));
        field_output.setText("");
    }
        // shalalallalalla
    void fillGrid(String alphabet, String key) {
        for (int i = 0; i < 25; i++) {
            Label currentLabel = (Label) grid.lookup("#gridLabel_" + i);
            String matrixData = PlayfairCipherLogic.parseKey(key + alphabet);
            matrix = PlayfairCipherLogic.updateMatrix(matrixData, matrix, field_alphabet.getText());
            if (currentLabel != null) {
                currentLabel.setText(String.valueOf(matrixData.charAt(i)));
            }
        }
    }
        // afanana
    @FXML
    void handleEncryptButton(ActionEvent event) {
        StringBuilder output = new StringBuilder(PlayfairCipherLogic.encrypt(field_input_filtered_encryption.getText().split(" "), matrix));
        System.out.println(output);

        for (int i = 5; i < output.length(); i += 6) {
            output.insert(i, " ");
        }

        field_output.setText(output.toString());
    }

    //Refactor needed, but unlike Ukrainian and Slovak economics, it works
    @FXML
    void handleDecryptButton(ActionEvent event) {
        System.out.println("Clicked");

        String inputFilteredText = field_input_filtered_decryption.getText();
        String[] input = new String[inputFilteredText.length()/2];

        for (int i = 0; i < inputFilteredText.length() - 1; i += 2) {
            input[i / 2] = inputFilteredText.substring(i, i + 2);
        }

        String decryptedText = PlayfairCipherLogic.decrypt(input, matrix);

        decryptedText = decryptedText.replaceAll("MEDZERA", " ");

        StringBuilder decryptedText1 = new StringBuilder(decryptedText);
        for (int i = 0; i < decryptedText1.length()-2; i++) {
            if (decryptedText1.charAt(i) == decryptedText1.charAt(i+2)) {
                if (decryptedText1.charAt(i+1) == 'X' || decryptedText1.charAt(i+1) == 'Q') {
                    decryptedText1.deleteCharAt(i+1);
                }
            }
        }
        // eeeee macarena
        for (int i = 0; i < FilterInput.numbers.length; i++) {
            decryptedText1 = new StringBuilder(decryptedText1.toString().replaceAll(FilterInput.numbers[i][1], FilterInput.numbers[i][0]));
        }

        //Toto rieši mazanie X a Q pokial encrypt input bol lichy
        if (decryptedText1.charAt(decryptedText1.length()-1) == 'Q') {
            decryptedText1.deleteCharAt(decryptedText1.length()-1);
        } else if (decryptedText1.charAt(decryptedText1.length()-1) == 'X') {
            decryptedText1.deleteCharAt(decryptedText1.length()-1);
        }

        field_output.setText(decryptedText1.toString());
    }

    // bailando bailando
    @FXML
    void handleInput(KeyEvent event) {
        String input = field_input.getText();
        if (input.isEmpty()) {
            btn_encrypt.setDisable(true);
            btn_decrypt.setDisable(true);
        } else {
            if (radio_slovak_alp.isSelected()) {
                input = input.toUpperCase().replaceAll("G","K");
            }
            if (radio_czech_alp.isSelected()) {
                input = input.toUpperCase().replaceAll("W","V");
            }
            if (radio_english_alp.isSelected()) {
                input = input.toUpperCase().replaceAll("J","I");
            }
            btn_encrypt.setDisable(false);
            btn_decrypt.setDisable(false);
        }

        field_input_filtered_encryption.setText(FilterInput.formatInputTextEncrypt(input));
        field_input_filtered_decryption.setText(formatInputTextDecrypt(input));
    }

    // amigos adios adios esteloockepwjhfcôawoubvebvliaal
    @FXML
    void handleAlphabetField(KeyEvent event) {
        String inputAlphabet = formatInputTextDecrypt(field_alphabet.getText());
        field_alphabet.setText(inputAlphabet);
        field_alphabet.positionCaret(field_alphabet.getText().length());

        if (inputAlphabet.length() == 25 && !FilterInput.hasDuplicate(inputAlphabet)) {
            //OK BRO 😎😎😎😎😎😎
            btn_encrypt.setDisable(false);
            btn_decrypt.setDisable(false);
            field_key.setDisable(false);
            field_alphabet.setText(inputAlphabet.toUpperCase());
            fillGrid(inputAlphabet, FilterInput.formatInputKey(field_key.getText()));
        } else {
            //NOT OK 😓😥😥😥😥😥😭
            field_key.setDisable(true);
            btn_encrypt.setDisable(true);
            btn_decrypt.setDisable(true);
        }

        radio_custom_alp.setSelected(true);
        alphabetMissing.setText("? → ?");
    }

    // instead of handling a key we are going to handle a weapon
    @FXML
    void handleKey(KeyEvent event) {
        if (FilterInput.hasDuplicate(FilterInput.formatInputKey(field_key.getText()))) {
            System.out.println("Key Found duplicate..");
            btn_encrypt.setDisable(true);
            btn_decrypt.setDisable(true);
        } else {
            btn_encrypt.setDisable(false);
            btn_decrypt.setDisable(false);
            fillGrid(field_alphabet.getText(), FilterInput.formatInputKey(field_key.getText()));
        }
    }
}
