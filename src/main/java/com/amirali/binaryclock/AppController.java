package com.amirali.binaryclock;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class AppController implements Initializable {

    @FXML
    private HBox root;

    @FXML
    private VBox h1, h2, m1, m2, s1, s2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final var timer = new Timer("Timer");
        final var hourTimerTask = new TimerTask() {
            @Override
            public void run() {
                final var currentTime = LocalDateTime.now();
                final var formattedHour = currentTime.format(DateTimeFormatter.ofPattern("HH"));
                applyBinary(h1, getFirstDigit(formattedHour));
                applyBinary(h2, getLastDigit(formattedHour));
            }
        };
        final var minuteTimerTask = new TimerTask() {
            @Override
            public void run() {
                final var currentTime = LocalDateTime.now();
                final var formattedMinute = currentTime.format(DateTimeFormatter.ofPattern("mm"));
                applyBinary(m1, getFirstDigit(formattedMinute));
                applyBinary(m2, getLastDigit(formattedMinute));
            }
        };
        final var secondTimerTask = new TimerTask(){
            @Override
            public void run() {
                final var currentTime = LocalDateTime.now();
                final var formattedSecond = currentTime.format(DateTimeFormatter.ofPattern("ss"));
                applyBinary(s1, getFirstDigit(formattedSecond));
                applyBinary(s2, getLastDigit(formattedSecond));
            }
        };
        timer.schedule(hourTimerTask, 1000, 1000*3600);
        timer.schedule(minuteTimerTask, 1000, 1000*60);
        timer.schedule(secondTimerTask, 1000, 1000);
    }

    private int getFirstDigit(String s) {
        return Integer.parseInt(s.substring(0, 1));
    }

    private int getLastDigit(String s) {
        return Integer.parseInt(s.substring(s.length()-1));
    }

    private void applyBinary(VBox vBox, int number) {
        final var circles = getCircles(vBox);
        if (circles.isEmpty())
            return;
        circles.forEach(circle -> circle.setFill(Color.GRAY));
        if (number == 0) return;
        if (number == 1)
            circles.getFirst().setFill(Color.DODGERBLUE);

        var index = 0;
        var input = number;
        while (input != 0) {
            if (input % 2 == 1)
                circles.get(index).setFill(Color.DODGERBLUE);
            index++;
            input /= 2;
        }
    }

    private ObservableList<Circle> getCircles(Pane pane) {
        final var circles = FXCollections.observableArrayList(pane.getChildren().stream().filter(Circle.class::isInstance).map(Circle.class::cast).toList());
        FXCollections.reverse(circles);
        return circles;
    }

}
