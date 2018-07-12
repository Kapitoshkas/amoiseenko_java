package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

  public class PointTests {

  @Test
  public void testDistanceTo() {
    Point p1 = new Point(2, 2);
    Point p2 = new Point(3, 3);
    Assert.assertEquals(p1.distance(p2), 1.4142135623730951);
  }

  @Test
  public void testDistanceMinus() {
    Point p3 = new Point(5, 5);
    Point p4 = new Point(-8, -8);
    Assert.assertEquals(p3.distance(p4), 18.384776310850235);
  }

  @Test
  public void testMidPoint() {
    Point p1 = new Point(2, 2);
    Point p2 = new Point(3, 3);

    Point midP = p1.getMidpoint(p2);

    Assert.assertEquals(2.5,midP.x);
    Assert.assertEquals(2.5,midP.y);
  }
}



