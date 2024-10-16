package com.playfaircipher.playfair_cipher;

public class PlayfairCipherLogic {

    //Alphabets
    static String alphabetSlovak = "ABCDEFHIJKLMNOPQRSTUVWXYZ";
    static String alphabetSlovakChange = "G → K";

    static String alphabetCzech = "ABCDEFGHIJKLMNOPQRSTUVXYZ";
    static String alphabetCzechChange = "W → V";

    static String alphabetEnglish = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
    static String alphabetEnglishChange = "J → I";

    static String alphabetFull = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static char alphabetFindMissing(String alphabet) {
        for (int i = 0; i < alphabet.length(); i++) {
            if (alphabet.indexOf(alphabetFull.charAt(i)) == -1) {
                return alphabetFull.charAt(i);
            }
        }
        return '0';
    }

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

    public static String stringMatrix(String key, char[][] matrix, String alphabet) {
        StringBuilder tempString = new StringBuilder();
        tempString.append(parseKey(key+alphabet));
        tempString.delete(25, tempString.length());
        return tempString.toString();
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

    //Vrati znak na danej lokacii
    public static char matrixGetCharByLocation(char[][] matrix, int locA, int locB) {
        return matrix[locA][locB];
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

        for (int i = 0; i < input.length; i++) {
            int[] firstCharLoc = matrixGetCharLocation(input[i].charAt(0), matrix);
            int[] secondCharLoc = matrixGetCharLocation(input[i].charAt(1), matrix);

            int[][]  newCharLoc = matrixMoveCase(firstCharLoc, secondCharLoc, true);

            output.append(matrix[newCharLoc[0][0]][newCharLoc[0][1]]);
            output.append(matrix[newCharLoc[1][0]][newCharLoc[1][1]]);
        }
        return output.toString();
    }

    public static String decrypt(String[] input, char[][] matrix) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < input.length; i++) {
            int[] firstCharLoc = matrixGetCharLocation(input[i].charAt(0), matrix);
            int[] secondCharLoc = matrixGetCharLocation(input[i].charAt(1), matrix);

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
