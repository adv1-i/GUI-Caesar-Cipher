package com.example.cezarcipher;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

//import static com.example.cezarcipher.Caesar.engEncrypt;
import static com.example.cezarcipher.Caesar.Encrypt;
import static com.example.cezarcipher.Caesar.decoding;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GraphicalUserInterface implements Initializable {
    @FXML
    public TextField sourceField;
    @FXML
    public TextField codedField;
    @FXML
    public Label sourceLBL;
    @FXML
    public Label codedLBL;
    @FXML
    public Button toCodeBTN;
    @FXML
    public Label keyLBL;
    @FXML
    public ComboBox<String> combo;
    @FXML
    public Button clrBTN;
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

    int key;
    int currentValue;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(-1000, 1000);
        valueFactory.setValue(1);
        spinnerCount.setValueFactory(valueFactory);
        currentValue = spinnerCount.getValue();
    }

    public void CodeMessage(MouseEvent actionEvent) {
            String s = sourceField.getText();
            String ru_string = sourceField.getText();
            key = Integer.parseInt(String.valueOf(spinnerCount.getValue()));
            try {
                codedField.setText(Encrypt(s, key));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //String ru_string = sourceField.getText();
            //key = Integer.parseInt(String.valueOf(spinnerCount.getValue()));
            //codedField.setText(ruEncrypt(ru_string, key));
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

    public void clicked(MouseEvent mouseEvent) {
        if (toClearCoded.isPressed())
            sourceField.setText("");
            codedField.setText("");
    }

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
}