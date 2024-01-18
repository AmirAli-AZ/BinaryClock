package com.amirali.binaryclock;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class Clock extends HBox {

    private Digit h1, h2, m1, m2, s1, s2;

    public Clock() {
        initGraphics();
    }

    private void initGraphics() {
        setAlignment(Pos.CENTER);
        setSpacing(5);

        h1 = new Digit("H");
        h2 = new Digit("H");
        m1 = new Digit("M");
        m2 = new Digit("M");
        s1 = new Digit("S");
        s2 = new Digit("S");

        getChildren().addAll(h1, h2, m1, m2, s1, s2);
    }

    public void startTimer() {
        final var timerTask = new TimerTask() {
            @Override
            public void run() {
                final var currentTime = LocalDateTime.now();

                final var formattedHour = currentTime.format(DateTimeFormatter.ofPattern("HH"));
                h1.showDigit(getFirstDigit(formattedHour));
                h2.showDigit(getLastDigit(formattedHour));

                final var formattedMinute = currentTime.format(DateTimeFormatter.ofPattern("mm"));
                m1.showDigit(getFirstDigit(formattedMinute));
                m2.showDigit(getLastDigit(formattedMinute));

                final var formattedSecond = currentTime.format(DateTimeFormatter.ofPattern("ss"));
                s1.showDigit(getFirstDigit(formattedSecond));
                s2.showDigit(getLastDigit(formattedSecond));
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
}
