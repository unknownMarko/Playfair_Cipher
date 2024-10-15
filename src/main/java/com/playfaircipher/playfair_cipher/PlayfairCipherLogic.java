package com.playfaircipher.playfair_cipher;

public class PlayfairCipherLogic {

    //Alphabets
    static String alphabetSlovak = "ABCDEFHIJKLMNOPQRSTUVWXYZ";
    static String alphabetCzech = "ABCDEFGHIJKLMNOPQRSTUVXYZ";


    //Kontrola zhodnosti znakov v stringu (Matici)
    public static String parseKey(String key) {
        StringBuilder result = new StringBuilder();

        key = key.toUpperCase();

        for (int i = 0; i < key.length(); i++) {
            if (result.indexOf(String.valueOf(key.charAt(i))) == -1) result.append(key.charAt(i));
        }
        return result.toString();
    }

    //Spoji key a abecedu a vrati dvojrozmerne pole vyplnene znakmi v matici
    //bool language Slovak = true, Czech = false
    public static char[][] updateMatrix(String key, char[][] matrix, String alphabet) {

        StringBuilder tempString = new StringBuilder();

        tempString.append(parseKey(key+alphabet));

        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = tempString.charAt(index);
                index++;
            }
        }
        return matrix;
    }

    //Vrati lokaciu znaku v matici. [Stlpec, Riadok]
    public static int[] matrixGetCharLocation(char[][] matrix, char target) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (target == matrix[i][j]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1,-1};
    }

    //Vrati znak na danej lokacii
    public static char matrixGetCharByLocation(char[][] matrix, int locA, int locB) {
        return matrix[locA][locB];
    }


}
