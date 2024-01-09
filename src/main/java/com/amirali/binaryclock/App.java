package com.amirali.binaryclock;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Binary Clock");
        final var loader = new FXMLLoader(getClass().getResource("app-view.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.setOnCloseRequest(windowEvent -> System.exit(0));
        stage.show();
    }
}
