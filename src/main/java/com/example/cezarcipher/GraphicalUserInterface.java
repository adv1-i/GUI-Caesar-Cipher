package com.example.cezarcipher;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import static com.example.cezarcipher.Caesar.encrypt;
import static com.example.cezarcipher.Caesar.rucrypt;

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

    int key;
    int currentValue;

    public void Select(ActionEvent actionEvent) {
        String s = combo.getSelectionModel().getSelectedItem();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("Русский", "Английский");
        combo.setItems(list);
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 33);
        valueFactory.setValue(1);
        spinnerCount.setValueFactory(valueFactory);
        currentValue = spinnerCount.getValue();
    }

    public void onClick(ActionEvent actionEvent) {
        sourceField.setText("");
        codedField.setText("");
        spinnerCount.getValueFactory().setValue(1);
    }

    public void CodeMessage(MouseEvent actionEvent) {
        if (combo.getSelectionModel().getSelectedItem().equals("Английский")) {
            String s = sourceField.getText().toLowerCase();
            key = Integer.parseInt(String.valueOf(spinnerCount.getValue()));
            codedField.setText(encrypt(s, key));
        } else {
            String ru_string = sourceField.getText().toLowerCase();
            key = Integer.parseInt(String.valueOf(spinnerCount.getValue()));
            codedField.setText(rucrypt(ru_string, key));
        }
    }
}