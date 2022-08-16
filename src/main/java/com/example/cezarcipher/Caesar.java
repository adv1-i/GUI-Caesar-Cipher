package com.example.cezarcipher;

public class Caesar {
    static final int NOT_FOUND = -1;
    static String en_alpha = "abcdefghijklmnopqrstuvwxyz";
    static String ru_alpha = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public static String encrypt(String srcMessage, int key) {
        StringBuilder cipher = new StringBuilder();
        for (int i = 0; i < srcMessage.length(); i++) {
            int index;
            char x = srcMessage.charAt(i);
                index = en_alpha.indexOf(x);
            if (index == NOT_FOUND) {
                cipher.append(x);
                continue;
            }
            int newIndex = (index + key) % 26;
            char y = en_alpha.charAt(newIndex);
            cipher.append(y);
        }
        return cipher.toString();
    }
    public static String rucrypt(String srcMessage , int key) {
        StringBuilder cipher = new StringBuilder();
        for(int i = 0; i<srcMessage.length();i++)
        {
            char x = srcMessage.charAt(i);
            int index = ru_alpha.indexOf(x);
            if (index == NOT_FOUND) {
                cipher.append(x);
                continue;
            }
            int newIndex = (index + key) % 33;
            char y = ru_alpha.charAt(newIndex);
            cipher.append(y);
        }
        return cipher.toString();
    }
}

/*
   class Some extends HelloController {
        int RuKey = 33;
        int EnKey = 26;
        HelloController object = new HelloController();

        public int getKeyGap() {
            if (object.combo.getSelectionModel().getSelectedItem().equals("Русский")) {
                return RuKey;
            } else return EnKey;
        }
    }*/
