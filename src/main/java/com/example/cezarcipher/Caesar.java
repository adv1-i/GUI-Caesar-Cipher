package com.example.cezarcipher;

import java.io.IOException;

public class Caesar {
    static final int NOT_FOUND = -1;
    static String en_alpha = "abcdefghijklmnopqrstuvwxyz";
    static String en_alphaTABBED = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String ru_alpha = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    static String ru_alphaTABBED = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    public static String Encrypt(String srcMessage, int key) throws IOException {
        GraphicalUserInterface gui = new GraphicalUserInterface();
        StringBuilder cipher = new StringBuilder();
        for (int i = 0; i < srcMessage.length(); i++) {
            int index;
            int newIndex;
            char x = srcMessage.charAt(i);
            if (x >= 'A' && x <= 'Z') {
                index = en_alphaTABBED.indexOf(x);
                newIndex = (index + key) % 26;
                char y = en_alphaTABBED.charAt(newIndex);
                cipher.append(y);
            } else if (x >= 'a' && x <= 'z') {
                index = en_alpha.indexOf(x);
                if (index == NOT_FOUND) {
                    cipher.append(x);
                    continue;
                }
                newIndex = (index + key) % 26;
                char y = en_alpha.charAt(newIndex);
                cipher.append(y);
            } else if (x >= 'А' && x <= 'Я') {
                index = ru_alphaTABBED.indexOf(x);
                newIndex = (index + key) % 33;
                char y = ru_alphaTABBED.charAt(newIndex);
                cipher.append(y);
            } else {
                index = ru_alpha.indexOf(x);
                if (index == NOT_FOUND) {
                    cipher.append(x);
                    continue;
                }
                newIndex = (index + key) % 33;
                char y = ru_alpha.charAt(newIndex);
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
                int keyVal = (charPosition - key) % 26;
                if (keyVal < 0) {
                    keyVal = en_alphaTABBED.length() + keyVal;
                }
                char replaceVal = en_alphaTABBED.charAt(keyVal);
                sb.append(replaceVal);

            } else if (actualPos >= 'a' && actualPos <= 'z') {
                int charPosition = en_alpha.indexOf(cipher.charAt(i));
                int keyVal = (charPosition - key) % 26;
                if (keyVal < 0) {
                    keyVal = en_alpha.length() + keyVal;
                }
                char replaceVal = en_alpha.charAt(keyVal);
                sb.append(replaceVal);

            } else if (actualPos >= 'А' && actualPos <= 'Я' || actualPos == 'Ё') {

                  int charPosition = ru_alphaTABBED.indexOf(cipher.charAt(i));
                  int keyVal = (charPosition - key) % 33;
                  if (keyVal < 0) {
                    keyVal = ru_alphaTABBED.length() + keyVal;
                  }
                  char replaceVal = ru_alphaTABBED.charAt(keyVal);
                  sb.append(replaceVal);

            } else if (actualPos >= 'а' && actualPos <= 'я' || actualPos == 'ё') {
                int charPosition = ru_alpha.indexOf(cipher.charAt(i));
                int keyVal = (charPosition - key) % 33;
                if (keyVal < 0) {
                        keyVal = ru_alpha.length() + keyVal;
                    }
                char replaceVal = ru_alpha.charAt(keyVal);
                sb.append(replaceVal);
                } else {
                sb.append(actualPos);
                }
            }
            return sb.toString();
        }
    }