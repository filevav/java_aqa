package ru.stqa.aqa.sandbox;

import java.text.DecimalFormat;

public class Point {

    public double x, y;

    public Point (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p) {
        double value = Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
        double format = Double.parseDouble(new DecimalFormat("00.00")
                .format(value)
                .replace(",", "."));
        return format;
    }

}