package ru.stqa.aqa.sandbox;

import java.text.DecimalFormat;

public class Point {

    public double x, y;

    public Point (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double distance(Point p1, Point p2) {
        double value = Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
        double format = Double.parseDouble(new DecimalFormat("00.00")
                .format(value)
                .replace(",", "."));
        return format;
    }

}