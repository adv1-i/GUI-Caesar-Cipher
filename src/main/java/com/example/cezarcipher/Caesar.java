package com.example.cezarcipher;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Caesar {
    static final int alphabetEnglishPower = 26;
    static final int alphabetRussianPower = 33;
    static final int NOT_FOUND = -1;
    static String en_alpha = "abcdefghijklmnopqrstuvwxyz";
    static String en_alphaTABBED = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String ru_alpha = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    static String ru_alphaTABBED = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    public static String analysisText = "";
    public static String ciphertext = "";
    public static Map<Character, Integer> graduationAnalysisMap;
    public static Map<Character, Integer> graduationCipherMap;

    public static class LetterCount {
        private final char letter;

        private int count;

        public LetterCount(char letter) {
            this.letter = letter;
            this.count = 1;
        }

        public void incrementCount() {
            this.count++;
        }

        public char getLetter() {
            return letter;
        }

        public int getCount() {
            return count;
        }
    }

    public List<LetterCount> calculateLetterCounts(String sentence) {
        List<LetterCount> letterCounts = new ArrayList<>();
        char[] letters = sentence.toCharArray();
        for (char c : letters) {
            if (c != ' ') {
                addCount(letterCounts, c);
            }
        }

        return letterCounts;
    }

    private void addCount(List<LetterCount> letterCounts, char c) {
        for (LetterCount letterCount: letterCounts) {
            if (letterCount.getLetter() == c) {
                letterCount.incrementCount();
                return;
            }
        }

        letterCounts.add(new LetterCount(c));
    }

    public static String encrypt(String srcMessage, int key) throws IOException {
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

    public static boolean isLetterIncluded(char selectedChar) {
        return (((int) selectedChar >= 'a') & ((int) selectedChar <= 'z')) ||
                (((int) selectedChar >= 'A') & ((int) selectedChar <= 'Z'));
    }

    public static Map<Character, Integer> getGradAnalysisMap() {
        return graduationAnalysisMap;
    }

    public static Map<Character, Integer> getGradCipherMap() {
        return graduationCipherMap;
    }

    public static void setAnalysisText(String analysisText) {
        Caesar.analysisText = analysisText;
    }

    public static void setCiphertext(String ciphertext) {
        Caesar.ciphertext = ciphertext;
    }

    private static Map<Character, Integer> sortByValue(Map<Character, Integer> originalMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<Character, Integer>> list =
                new LinkedList<Map.Entry<Character, Integer>>(originalMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> o1,
                               Map.Entry<Character, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<Character, Integer> sortedMap = new LinkedHashMap<Character, Integer>();
        for (Map.Entry<Character, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        /*
        //classic iterator example
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }*/
        return sortedMap;
    }

    public static void analysis() {
        //System.out.println(analysisText + "/" + analysisText + "\n");
        //System.out.println(ciphertext + "/"  + ciphertext + "\n");
        setCiphertext(ciphertext.toUpperCase());
        setAnalysisText(analysisText.toUpperCase());
        Map<Character, Integer> freqAnalysisText = new HashMap<>();
        Map<Character, Integer> freqCipherText = new HashMap<>();
        for (char analysisLetter : analysisText.toCharArray())
            if (isLetterIncluded(analysisLetter)) freqAnalysisText.put(analysisLetter,
                    freqAnalysisText.getOrDefault(analysisLetter, 0) + 1);

        graduationAnalysisMap = sortByValue(freqAnalysisText);

        for (char cipherLetter : ciphertext.toCharArray())
            if (isLetterIncluded(cipherLetter)) freqCipherText.put(cipherLetter,
                    freqCipherText.getOrDefault(cipherLetter, 0) + 1);

        graduationCipherMap = sortByValue(freqCipherText);

        System.out.println("Cipher Letters:\n");
        for (Map.Entry<Character, Integer> entry : graduationCipherMap.entrySet()) {
            //double percentage = 100.0 * entry.getValue() / getCipherTextArea().length();
            System.out.println("letter - " + entry.getKey() + "   :   " + "quantity = " + entry.getValue() +"\n");
        }
        System.out.println("Analysis Letters:\n");
        for (Map.Entry<Character, Integer> entry : graduationAnalysisMap.entrySet()) {
            //double percentage = 100.0 * entry.getValue() / getAnalysisTextArea().length();
            System.out.println("letter - " + entry.getKey() + "   :   " + "quantity = " + entry.getValue() +"\n");
        }
    }
}