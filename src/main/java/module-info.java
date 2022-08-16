module com.example.cezarcipher {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cezarcipher to javafx.fxml;
    exports com.example.cezarcipher;
}