package com.example.cezarcipher;

import java.io.IOException;

public class Caesar {
    static final int alphabetEnglishPower = 26;
    static final int alphabetRussianPower = 33;
    static final int NOT_FOUND = -1;
    static String en_alpha = "abcdefghijklmnopqrstuvwxyz";
    static String en_alphaTABBED = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String ru_alpha = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    static String ru_alphaTABBED = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    public static String Encrypt(String srcMessage, int key) throws IOException {
        GraphicalUserInterface gui = new GraphicalUserInterface();
        StringBuilder cipher = new StringBuilder();
        for (int i = 0; i < srcMessage.length(); i++) {
            int currPos;
            int newPos;
            char x = srcMessage.charAt(i);
            if (x >= 'A' && x <= 'Z') {
                currPos = en_alphaTABBED.indexOf(x);
                newPos = (currPos + key) % alphabetEnglishPower;
                char y = en_alphaTABBED.charAt(newPos);
                cipher.append(y);
            } else if (x >= 'a' && x <= 'z') {
                currPos = en_alpha.indexOf(x);
                if (currPos == NOT_FOUND) {
                    cipher.append(x);
                    continue;
                }
                newPos = (currPos + key) % alphabetEnglishPower;
                char y = en_alpha.charAt(newPos);
                cipher.append(y);
            } else if (x >= 'А' && x <= 'Я') {
                currPos = ru_alphaTABBED.indexOf(x);
                newPos = (currPos + key) % alphabetRussianPower;
                char y = ru_alphaTABBED.charAt(newPos);
                cipher.append(y);
            } else {
                currPos = ru_alpha.indexOf(x);
                if (currPos == NOT_FOUND) {
                    cipher.append(x);
                    continue;
                }
                newPos = (currPos + key) % alphabetRussianPower;
                char y = ru_alpha.charAt(newPos);
                cipher.append(y);
            }
        }
        return cipher.toString();
    }
    public static String decoding(String cipher, int key) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cipher.length(); i++) {
            char actualPos = cipher.charAt(i);
            if (actualPos >= 'A' && actualPos <= 'Z') {
                int charPosition = en_alphaTABBED.indexOf(cipher.charAt(i));
                int calcKey = (charPosition - key) % alphabetEnglishPower;
                if (calcKey < 0) {
                    calcKey = en_alphaTABBED.length() + calcKey;
                }
                char decodedLetter = en_alphaTABBED.charAt(calcKey);
                sb.append(decodedLetter);

            } else if (actualPos >= 'a' && actualPos <= 'z') {
                int charPosition = en_alpha.indexOf(cipher.charAt(i));
                int calcKey = (charPosition - key) % alphabetEnglishPower;
                if (calcKey < 0) {
                    calcKey = en_alpha.length() + calcKey;
                }
                char decodedLetter = en_alpha.charAt(calcKey);
                sb.append(decodedLetter);

            } else if (actualPos >= 'А' && actualPos <= 'Я' || actualPos == 'Ё') {

                  int charPosition = ru_alphaTABBED.indexOf(cipher.charAt(i));
                  int calcKey = (charPosition - key) % alphabetRussianPower;
                  if (calcKey < 0) {
                    calcKey = ru_alphaTABBED.length() + calcKey;
                  }
                  char decodedLetter = ru_alphaTABBED.charAt(calcKey);
                  sb.append(decodedLetter);

            } else if (actualPos >= 'а' && actualPos <= 'я' || actualPos == 'ё') {
                int charPosition = ru_alpha.indexOf(cipher.charAt(i));
                int calcKey = (charPosition - key) % alphabetRussianPower;
                if (calcKey < 0) {
                        calcKey = ru_alpha.length() + calcKey;
                    }
                char decodedLetter = ru_alpha.charAt(calcKey);
                sb.append(decodedLetter);
                } else {
                sb.append(actualPos);
                }
            }
            return sb.toString();
        }
    }