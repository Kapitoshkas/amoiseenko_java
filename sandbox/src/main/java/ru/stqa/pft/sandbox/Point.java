package ru.stqa.pft.sandbox;

public class Point {

  public double x;
  public double y;



  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public Point getMidpoint(Point p2)
  {
    double midx;
    double midy;


    midx = (x + p2.x)/2;
    midy = (y + p2.y)/2;

   Point midPoint = new Point(midx, midy);

    return midPoint;
  }

  public double distance(Point p2){

    double distance = Math.sqrt ((x - p2.x)*(x - p2.x) + (y - p2.y)*(y - p2.y));
    return distance;

  }

}
