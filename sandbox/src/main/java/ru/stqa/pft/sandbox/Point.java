package ru.stqa.pft.sandbox;

public class Point {

  public double a;

  public double b;

  public Point(double p1, double p2) {
    this.a = p1;
    this.b = p2;
  }

  public double sqrt() {return this.a * this.b;}
}
