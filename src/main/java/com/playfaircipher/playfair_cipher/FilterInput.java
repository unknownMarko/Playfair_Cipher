package com.playfaircipher.playfair_cipher;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class FilterInput {

    static String[][] numbers = {
            {"0", "NULA"},
            {"1", "JEDNA"},
            {"2", "DVA"},
            {"3", "TRI"},
            {"4", "STYRI"},
            {"5", "PAT"},
            {"6", "SEST"},
            {"7", "SEDEM"},
            {"8", "OSEM"},
            {"9", "DEVAT"},
    };

    //Whole logic of formatting input. Using all functions in this file
    public static String formatInput(String inputString) {
        StringBuilder output = new StringBuilder();

        inputString = inputString.toUpperCase();
        inputString = Normalizer.normalize(inputString, Normalizer.Form.NFD);
        inputString = Pattern.compile("\\p{InCombiningDiacriticalMarks}+").matcher(inputString).replaceAll("");

        for (int i = 0; i < inputString.length(); i++) {
            char character = inputString.charAt(i);

            for (String[] number : numbers) {
                if (String.valueOf(character).equals(number[0])) {
                    output.append(number[1]);
                    break;
                }
            }
            if (character >= 'A' && character <= 'Z') {
                output.append(character);
            } else if (character == ' ') {
                output.append("MEDZERA");
            }
        }

        output = new StringBuilder(almostFinalTouch(output.toString()));

        if (output.length() % 2 == 1) {
            if (output.charAt(output.length()-1) == 'X') {
                output.append("Q");
            } else {
                output.append("X");
            }
        }

        return spacing(output.toString());
    }

    //Don't ask me about name of this function. I just really didn't know what to call it.. (:
    //Checking if we have two chars after each other and adds another char.
    //XX = XQX, Else add X
    public static String almostFinalTouch(String inputString) {
        StringBuilder output = new StringBuilder();
        //TODO: Maybe refactor?
        for (int i = 0; i < inputString.length(); i++) {
            output.append(inputString.charAt(i));
            if (i < inputString.length() - 1 && inputString.charAt(i) == inputString.charAt(i + 1)) {
                if (inputString.charAt(i) == 'X') {
                    output.append('Q');
                } else {
                    output.append('X');
                }
            }
        }
        return output.toString();
    }


    //Prida medzeri kazde dva chars
    public static String spacing(String inputString) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < inputString.length(); i++) {
            if (i % 2 == 0 && i > 0) output.append(" ");
            output.append(inputString.charAt(i));
        }
        return output.toString();
    }
}
