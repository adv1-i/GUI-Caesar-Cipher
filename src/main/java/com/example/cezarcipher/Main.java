package com.example.cezarcipher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 280);
        stage.setTitle("CAESAR CIPHER APP");

        stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3470/3470475.png"));
        /*try {
            stage.getIcons().add(new Image(Main.class.getResource("/icons/codingIcon.png").toExternalForm()));
        } catch (Exception e) {
            System.out.println("Icon not found");
        }*/

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println(new File("codingIcon.png").getAbsolutePath());
        launch();
    }
}