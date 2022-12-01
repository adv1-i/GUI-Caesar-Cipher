package com.example.cezarcipher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import static com.example.cezarcipher.Caesar.*;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class GraphicalUserInterface implements Initializable {
    @FXML
    public TextArea sourceField;
    @FXML
    public TextArea codedField;
    @FXML
    public Label sourceLBL;
    @FXML
    public Label codedLBL;
    @FXML
    public Button toCodeBTN;
    @FXML
    public Label keyLBL;
    @FXML
    public Spinner<Integer> spinnerCount;
    @FXML
    public Pane stage;
    @FXML
    public Button toClearSrc;
    @FXML
    public Button toClearCoded;
    @FXML
    public Button toDecodeBTN;
    @FXML
    public Button loadEncryptedFile;
    @FXML
    public Button loadDecryptedFile;
    @FXML
    public Button loadCipherTextBTN;
    @FXML
    public Button loadAnalysisTextBTN;
    @FXML
    public TextArea cipherTextArea;
    @FXML
    public TextArea analysisTextArea;
    @FXML
    public  Button analysisBTN;
    @FXML
    public TextField outPutKey;
    int key;
    int currentValue;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000);
        valueFactory.setValue(1);
        spinnerCount.setValueFactory(valueFactory);
        currentValue = spinnerCount.getValue();
    }

    public void CodeMessage(MouseEvent actionEvent) {
            String s = sourceField.getText();
            key = Integer.parseInt(String.valueOf(spinnerCount.getValue()));
            try {
                codedField.setText(encrypt(s, key));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    public void decode(ActionEvent actionEvent) {
        String dec = codedField.getText();
        key = Integer.parseInt(String.valueOf(spinnerCount.getValue()));
        try {
            sourceField.setText(decoding(dec, key));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void frequenceAnalysis(ActionEvent actionEvent) throws IOException {
        Caesar.setCiphertext(cipherTextArea.getText());
        Caesar.setAnalysisText(analysisTextArea.getText());
        Caesar.analysis();

        Map<Character,Integer> graduationAnalysisMap = Caesar.getGradAnalysisMap();
        Map<Character,Integer> graduationCipherMap = Caesar.getGradCipherMap();

        Map.Entry<Character, Integer> mostAnalysisLetter = graduationAnalysisMap.entrySet().iterator().next();
        Map.Entry<Character, Integer> mostCipherLetter = graduationCipherMap.entrySet().iterator().next();

        System.out.println("The most meeting letter in analysis text\n");
        System.out.println("letter - " + mostAnalysisLetter.getKey() + "   :   " + "quantity = " +
                    mostAnalysisLetter.getValue() + "\n");
        System.out.println("The most meeting letter in cipher text\n");
        System.out.println("letter - " + mostCipherLetter.getKey() + "   :   " + "quantity = " +
                    mostCipherLetter.getValue() +"\n");

        int estimatedCalcKey = 0;
            estimatedCalcKey = Math.abs((Character.toLowerCase(mostCipherLetter.getKey()))-
                    (Character.toLowerCase(mostAnalysisLetter.getKey())));
        outPutKey.setText(String.valueOf(estimatedCalcKey));
    }

    @FXML public void displayTextInDecryptedField(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(sourceField.getScene().getWindow());
        if(file != null){
            sourceField.setText(readFile(file));
        }
    }

    @FXML public void displayTextInEncryptedField(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(codedField.getScene().getWindow());
        if(file != null){
            codedField.setText(readFile(file));
        }
    }
    @FXML public void displayCipherTextOnBtnClick(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(cipherTextArea.getScene().getWindow());
        if (file != null) {
            cipherTextArea.setText(readFile(file));
        }
    }
    //        HashMap<String, Integer> encodedText = new HashMap<String, Integer>();
//        ArrayList<String> encodedList = new ArrayList<String>();
//        char[] arrline = new char[cipherTextArea.getText().length()];
//        int count = 1;
//
//        arrline = cipherTextArea.getText().replace(" ", "").toLowerCase().toCharArray();
//        for (int i = 0; i < arrline.length; i++) {
//            encodedList.add(String.valueOf(arrline[i]));
//        }
//        for (int i = 0; i < encodedList.size(); i++) {
//            for (int j = i + 1; j < encodedList.size(); j++) {
//                if (encodedList.get(i).equals(encodedList.get(j))) {
//                    count++;
//                    encodedList.remove(j);
//                    j--;
//                }
//            }
//            encodedText.put(encodedList.get(i), count);
//            count = 1;
//        }
//        System.out.println("\nКоличество вхождений каждой буквы в заданой строке:");
//        for (Map.Entry<String, Integer> mapE : encodedText.entrySet()) {
//            System.out.println("буква \"" + mapE.getKey() + "\" - " + mapE.getValue());
//        }
    @FXML public void displayAnalysisTextOnBtnClick(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(analysisTextArea.getScene().getWindow());
        if (file != null) {
            analysisTextArea.setText(readFile(file));
        }
    }
    //
//        HashMap<String, Integer> analysisText = new HashMap<String, Integer>();
//        ArrayList<String> analysisList = new ArrayList<String>();
//        char[] arrlineAnalysis = new char[analysisTextArea.getText().length()];
//        int countAnalysis = 1;
//        arrlineAnalysis = analysisTextArea.getText().replace(" ", "").toLowerCase().toCharArray();
//                for (int i = 0; i < arrlineAnalysis.length; i++) {
//            analysisList.add(String.valueOf(arrlineAnalysis[i]));
//        }
//                for (int i = 0; i < analysisList.size(); i++) {
//                    char ch = analysisTextArea.getText().charAt(i);
//                        for (int j = i + 1; j < analysisList.size(); j++) {
//                            if (analysisList.get(i).equals(analysisList.get(j))) {
//                                    countAnalysis++;
//                                    analysisList.remove(j);
//                                    j--;
//                            }
//                        }
//                        analysisText.put(analysisList.get(i), countAnalysis);
//                        countAnalysis = 1;
//                }
//                System.out.println("\nКоличество вхождений каждой буквы в заданой строке:");
//                for (Map.Entry<String, Integer> mapE : analysisText.entrySet()) {
//            System.out.println("буква \"" + mapE.getKey() + "\" - " + mapE.getValue());
//    }

    public void codedClear(MouseEvent mouseEvent) {
        toClearCoded.setOnAction(e -> {
            codedField.setText("");
        });
    }

    public void srcClear(MouseEvent mouseEvent) {
        toClearSrc.setOnAction(e -> {
            sourceField.setText("");
        });
    }

    private String readFile(File file){
        String filePath = file.getPath();
        String content = null;
        try {
            content = Files.lines(Paths.get(filePath))
                    .collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  content;
    }
}