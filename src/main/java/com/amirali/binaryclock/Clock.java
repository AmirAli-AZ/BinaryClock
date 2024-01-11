package com.amirali.binaryclock;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class Clock extends GridPane {

    private final PseudoClass onMoment = PseudoClass.getPseudoClass("OnMoment");

    public Clock() {
        initGraphics();
    }

    private void initGraphics() {
        setAlignment(Pos.CENTER);
        setHgap(5);
        setVgap(5);

        final var hourLabel1 = new Label("H");
        final var hourLabel2 = new Label("H");

        GridPane.setConstraints(hourLabel1, 0, 0);
        GridPane.setHalignment(hourLabel1, HPos.CENTER);
        getChildren().add(hourLabel1);
        GridPane.setConstraints(hourLabel2, 1, 0);
        GridPane.setHalignment(hourLabel2, HPos.CENTER);
        getChildren().add(hourLabel2);

        final var minuteLabel1 = new Label("M");
        final var minuteLabel2 = new Label("M");

        GridPane.setConstraints(minuteLabel1, 2, 0);
        GridPane.setHalignment(minuteLabel1, HPos.CENTER);
        getChildren().add(minuteLabel1);
        GridPane.setConstraints(minuteLabel2, 3, 0);
        GridPane.setHalignment(minuteLabel2, HPos.CENTER);
        getChildren().add(minuteLabel2);

        final var secondLabel1 = new Label("S");
        final var secondLabel2 = new Label("S");

        GridPane.setConstraints(secondLabel1, 4, 0);
        GridPane.setHalignment(secondLabel1, HPos.CENTER);
        getChildren().add(secondLabel1);
        GridPane.setConstraints(secondLabel2, 5, 0);
        GridPane.setHalignment(secondLabel2, HPos.CENTER);
        getChildren().add(secondLabel2);

        for (int column = 0; column < 6; column++) {
            for (int row = 1; row < 5; row++) {
                var circle = new Circle(35, Color.GRAY);
                GridPane.setConstraints(circle, column, row);
                GridPane.setHalignment(circle, HPos.CENTER);

                getChildren().add(circle);
            }
        }

    }

    public void startTimer() {
        final var timerTask = new TimerTask() {
            @Override
            public void run() {
                final var currentTime = LocalDateTime.now();

                final var formattedHour = currentTime.format(DateTimeFormatter.ofPattern("HH"));
                applyBinary(0, getFirstDigit(formattedHour));
                applyBinary(1, getLastDigit(formattedHour));

                final var formattedMinute = currentTime.format(DateTimeFormatter.ofPattern("mm"));
                applyBinary(2, getFirstDigit(formattedMinute));
                applyBinary(3, getLastDigit(formattedMinute));

                final var formattedSecond = currentTime.format(DateTimeFormatter.ofPattern("ss"));
                applyBinary(4, getFirstDigit(formattedSecond));
                applyBinary(5, getLastDigit(formattedSecond));
            }
        };
        final var timer = new Timer("Timer");

        timer.schedule(timerTask, 1000, 1000);
    }

    private int getFirstDigit(String s) {
        return Integer.parseInt(s.substring(0, 1));
    }

    private int getLastDigit(String s) {
        return Integer.parseInt(s.substring(s.length() - 1));
    }

    private void applyBinary(int column, int number) {
        final var circles = getCircles(column);
        if (circles.isEmpty())
            return;
        circles.forEach(circle -> circle.pseudoClassStateChanged(onMoment, false));
        if (number == 0) return;
        if (number == 1)
            circles.getFirst().setFill(Color.DODGERBLUE);

        var i = 0;
        var input = number;
        while (input != 0) {
            if (input % 2 == 1)
                circles.get(i).pseudoClassStateChanged(onMoment, true);
            i++;
            input /= 2;
        }
    }

    private ObservableList<Circle> getCircles(int column) {
        final var circles = FXCollections.observableArrayList(getChildren().stream().filter(node -> GridPane.getColumnIndex(node) == column && node instanceof Circle).map(Circle.class::cast).toList());
        FXCollections.reverse(circles);
        return circles;
    }
}
