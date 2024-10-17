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

public class PlayfairCipherLogic {

    //Alphabets
    static String alphabetSlovak = "ABCDEFHIJKLMNOPQRSTUVWXYZ";
    static String alphabetSlovakChange = "G → K";

    static String alphabetCzech = "ABCDEFGHIJKLMNOPQRSTUVXYZ";
    static String alphabetCzechChange = "W → V";

    static String alphabetEnglish = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
    static String alphabetEnglishChange = "J → I";

//    static String alphabetFull = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

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
    public static int[] matrixGetCharLocation(char target, char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (target == matrix[i][j]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1,-1};
    }

    static int[][] matrixMoveCase(int[] firstCharLoc, int[] secondCharLoc, boolean encryption) {
        //[Riadok, Stlpec]
        int[][] output = new int[2][2];

        //Stlpec, riadok, stvorec/obdlznik
        if (firstCharLoc[1] == secondCharLoc[1]) {
            System.out.println("COLUMN");

            int[] moveInColumnOutput;
            if (encryption) {
                moveInColumnOutput = moveHorVer(firstCharLoc[0], secondCharLoc[0], true);
            } else {
                moveInColumnOutput = moveHorVer(firstCharLoc[0], secondCharLoc[0], false);
            }

            output[0][0] = moveInColumnOutput[0];
            output[0][1] = firstCharLoc[1];

            output[1][0] = moveInColumnOutput[1];
            output[1][1] = secondCharLoc[1];

        } else if (firstCharLoc[0] == secondCharLoc[0]) {
            System.out.println("ROW");

            int[] moveInColumnOutput;

            if (encryption) {
                moveInColumnOutput = moveHorVer(firstCharLoc[1], secondCharLoc[1], true);
            } else {
                moveInColumnOutput = moveHorVer(firstCharLoc[1], secondCharLoc[1], false);
            }

            output[0][0] = firstCharLoc[0];
            output[0][1] = moveInColumnOutput[0];

            output[1][0] = secondCharLoc[0];
            output[1][1] = moveInColumnOutput[1];

        } else {
            System.out.println("SQUARE/RECTANGLE");

            output[0][0] = firstCharLoc[0];
            output[0][1] = secondCharLoc[1];

            output[1][0] = secondCharLoc[0];
            output[1][1] = firstCharLoc[1];
        }

        return output;
    }

    public static String encrypt(String[] input, char[][] matrix) {
        //[Riadok, Stlpec]

        StringBuilder output = new StringBuilder();

        for (String s : input) {
            int[] firstCharLoc = matrixGetCharLocation(s.charAt(0), matrix);
            int[] secondCharLoc = matrixGetCharLocation(s.charAt(1), matrix);

            int[][] newCharLoc = matrixMoveCase(firstCharLoc, secondCharLoc, true);

            output.append(matrix[newCharLoc[0][0]][newCharLoc[0][1]]);
            output.append(matrix[newCharLoc[1][0]][newCharLoc[1][1]]);
        }
        return output.toString();
    }

    public static String decrypt(String[] input, char[][] matrix) {
        StringBuilder output = new StringBuilder();

        for (String s : input) {
            int[] firstCharLoc = matrixGetCharLocation(s.charAt(0), matrix);
            int[] secondCharLoc = matrixGetCharLocation(s.charAt(1), matrix);

            int[][] newCharLoc = matrixMoveCase(firstCharLoc, secondCharLoc, false);

            output.append(matrix[newCharLoc[0][0]][newCharLoc[0][1]]);
            output.append(matrix[newCharLoc[1][0]][newCharLoc[1][1]]);
        }

        System.out.println("Decryption = " + output);
        return output.toString();
    }

    static int[] moveHorVer(int firstCharLoc, int secondCharLoc, boolean encryption) {
        System.out.println("Location before FirstChar = " + firstCharLoc + " SecondChar = " + secondCharLoc);
        if (encryption) {
            firstCharLoc += 1;
            secondCharLoc += 1;

            if (firstCharLoc == 5) firstCharLoc = 0;
            if (secondCharLoc == 5) secondCharLoc = 0;
        } else {
            firstCharLoc -= 1;
            secondCharLoc -= 1;

            if (firstCharLoc == -1) firstCharLoc = 4;
            if (secondCharLoc == -1) secondCharLoc = 4;
        }

        System.out.println("Location after FirstChar = " + firstCharLoc + " SecondChar = " + secondCharLoc);

        return new int[]{firstCharLoc, secondCharLoc};
    }
}
