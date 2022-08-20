package com.example.cezarcipher;

public class Caesar {
    static final int NOT_FOUND = -1;
    static String en_alpha = "abcdefghijklmnopqrstuvwxyz";
    static String en_alphaTABBED = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String ru_alpha = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    static String ru_alphaTABBED = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    public static String encrypt(String srcMessage, int key) {
        GraphicalUserInterface gui = new GraphicalUserInterface();
        StringBuilder cipher = new StringBuilder();
        for (int i = 0; i < srcMessage.length(); i++) {
            int index;
            char x = srcMessage.charAt(i);
            if(x>='A' && x<='Z') {
                index = en_alphaTABBED.indexOf(x);
                int newIndex = (index + key) % 26;
                char y = en_alphaTABBED.charAt(newIndex);
                cipher.append(y);
            } else {
                index = en_alpha.indexOf(x);
                if (index == NOT_FOUND) {
                    cipher.append(x);
                    continue;
                }
                int newIndex = (index + key) % 26;
                char y = en_alpha.charAt(newIndex);
                cipher.append(y);
            }
        }
        return cipher.toString();
    }

    public static String rucrypt(String srcMessage , int key) {
        StringBuilder cipher = new StringBuilder();
        for(int i = 0; i<srcMessage.length();i++)
        {
            int index;
            char x = srcMessage.charAt(i);
            if(x>='А' && x<='Я') {
                index = ru_alphaTABBED.indexOf(x);
                int newIndex = (index + key) % 33;
                char y = ru_alphaTABBED.charAt(newIndex);
                cipher.append(y);
            } else {
                index = ru_alpha.indexOf(x);
                if (index == NOT_FOUND) {
                    cipher.append(x);
                    continue;
                }
                int newIndex = (index + key) % 33;
                char y = ru_alpha.charAt(newIndex);
                cipher.append(y);
            }
        }
        return cipher.toString();
    }
}
