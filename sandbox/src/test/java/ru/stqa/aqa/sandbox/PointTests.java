package ru.stqa.aqa.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance() {
        Point p1 = new Point(-6,-4);
        Point p2 = new Point(1,7);
        Assert.assertEquals(Point.distance(p1, p2), (13.04));
    }
}
