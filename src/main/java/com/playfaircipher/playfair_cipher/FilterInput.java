package com.playfaircipher.playfair_cipher;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class FilterInput {

    static String[][] numbers = {
            {"0", "XJEDNAX"},
            {"1", "XDVAX"},
            {"2", "XTRIX"},
            {"3", "XSTYRIX"},
            {"4", "XPATX"},
            {"5", "XSESTX"},
            {"6", "XSEDEMX"},
            {"7", "XOSEMX"},
            {"8", "XDEVATX"},
            {"9", "XDESATX"},
    };

    public static String normalizer(String inputString) {

        inputString = inputString.toUpperCase();
        inputString = Normalizer.normalize(inputString, Normalizer.Form.NFD);
        inputString = Pattern.compile("\\p{InCombiningDiacriticalMarks}+").matcher(inputString).replaceAll("");

        return inputString;
    }

    public static boolean hasDuplicate(String inputString) {
        char[] chars = inputString.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] == chars[j]) {
                    return true;
                }
            }
        }
        return false;
    }

        public static String formatInputTextDecrypt(String inputString) {
        StringBuilder output = new StringBuilder();

        inputString = normalizer(inputString);

        for (int i = 0; i < inputString.length(); i++) {
            char character = inputString.charAt(i);

            if (character >= 'A' && character <= 'Z') {
                output.append(character);
            }
        }

        return output.toString();
    }

    //Whole logic of formatting input. Using all functions in this file
    public static String formatInputTextEncrypt(String inputString) {
        StringBuilder output = new StringBuilder();

        inputString = normalizer(inputString);

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

        return spacing(output.toString(), 2);
    }

    //Check correctness of Key input
    //Allow only letters + change lowerCase to upperCase
    public static String formatInputKey(String key) {
        key = key.toUpperCase();
        key = normalizer(key);
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) >= 'A' && key.charAt(i) <= 'Z') {
                output.append(key.charAt(i));
            }
        }

        return output.toString();
    }

    //Don't ask me about name of this function. I just really didn't know what to call it.. (:
    //Checking if we have two chars after each other and adds another char.
    //XX = XQX, Else add X
    public static String almostFinalTouch(String inputString) {
        StringBuilder output = new StringBuilder();
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
    public static String spacing(String inputString, int spacing) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < inputString.length(); i++) {
            if (i % spacing == 0 && i > 0) output.append(" ");
            output.append(inputString.charAt(i));
        }
        return output.toString();
    }
}
