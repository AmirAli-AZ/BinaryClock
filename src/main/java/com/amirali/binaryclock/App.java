package com.amirali.binaryclock;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Binary Clock");
        final var loader = new FXMLLoader(getClass().getResource("clock-view.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.setOnCloseRequest(windowEvent -> System.exit(0));
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("icons/icon.png")).toExternalForm()));
        stage.show();
    }
}
