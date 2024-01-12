package com.amirali.binaryclock;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Binary Clock");

        final var clock = new Clock();

        final var githubRepoButton = createButton("Github by Amir Ali");
        githubRepoButton.setOnAction(actionEvent -> getHostServices().showDocument("https://github.com/AmirAli-AZ/BinaryClock"));
        githubRepoButton.getStyleClass().add("primary");

        VBox.setMargin(githubRepoButton, new Insets(15, 0, 0, 0));

        final var root = new VBox(5, clock, githubRepoButton);
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

    private Button createButton(String text) {
        final var button = new Button(text);
        button.setCursor(Cursor.HAND);
        button.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            var scaleTransition = new ScaleTransition(Duration.millis(200), button);
            scaleTransition.setToX(0.88);
            scaleTransition.setToY(0.88);
            scaleTransition.play();
        });
        button.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> {
            var scaleTransition = new ScaleTransition(Duration.millis(200), button);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
        });
        return button;
    }
}
