package com.amirali.binaryclock;

import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Digit extends VBox {

    private static final PseudoClass onMoment = PseudoClass.getPseudoClass("OnMoment");

    private final List<Circle> circles;

    private int lastDigit;

    public Digit(String label) {
        setAlignment(Pos.CENTER);
        setSpacing(5);
        getChildren().add(new Label(label));

        circles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            var circle = new Circle(35);
            circles.add(circle);
            getChildren().add(circle);
        }
        Collections.reverse(circles);
    }

    public void showDigit(int digit) {
        if (digit > 9)
            return;
        if (lastDigit == digit)
            return;
        lastDigit = digit;
        circles.forEach(circle -> circle.pseudoClassStateChanged(onMoment, false));
        if (digit == 0)
            return;

        var i = 0;
        var input = digit;
        while (input != 0) {
            if (input % 2 == 1)
                circles.get(i).pseudoClassStateChanged(onMoment, true);
            i++;
            input /= 2;
        }
    }
}
