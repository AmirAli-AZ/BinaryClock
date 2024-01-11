package com.amirali.binaryclock;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Binary Clock");

        final var clock = new Clock();

        final var websiteButton = new Button("Web by BenHerbst");
        websiteButton.setCursor(Cursor.HAND);
        websiteButton.setOnAction(actionEvent -> getHostServices().showDocument("https://clock.benherbst.net/"));

        final var githubRepoButton = new Button("Github by Amir Ali");
        githubRepoButton.setCursor(Cursor.HAND);
        githubRepoButton.setOnAction(actionEvent -> getHostServices().showDocument("https://github.com/AmirAli-AZ/BinaryClock"));
        githubRepoButton.getStyleClass().add("primary");

        final var buttons = new HBox(5, githubRepoButton, websiteButton);
        VBox.setMargin(buttons, new Insets(15, 0, 0,0));
        buttons.setAlignment(Pos.CENTER);

        final var root = new VBox(5, clock, buttons);
        root.setId("root");
        root.setAlignment(Pos.CENTER);

        final var scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("themes/theme.css")).toExternalForm());
        stage.setScene(scene);
        stage.setOnCloseRequest(windowEvent -> System.exit(0));
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("icons/icon.png")).toExternalForm()));
        stage.show();

        clock.startTimer();
    }
}
