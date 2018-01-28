package ru.stqa.aqa.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance() {
        Point p = new Point(-6,-4,1,7);
        Assert.assertEquals(p.distance(), (13.04));
    }
}
