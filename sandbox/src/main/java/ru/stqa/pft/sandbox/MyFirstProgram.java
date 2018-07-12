package ru.stqa.pft.sandbox;

public class MyFirstProgram {
	public static void main(String args[])
	{
		hello("world");
		hello("puh");
		hello("rodos");

		Square s = new Square(5);
		System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4,6);
		System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

		Point p1 = new Point(2,2);
		Point p2 = new Point(3,3);

		Point midP = p1.getMidpoint(p2);

    System.out.println("Расстояние между точками " + p1.distance(p2));

		System.out.println("Средняя точка между (" + p1.x + ", " + p1.y + ") и (" + p2.x + ", " + p2.y + ") = (" + midP.x + ", " + midP.y + ").");

		Point p3 = new Point(5,5);
		Point p4 = new Point(-8,-8);
		System.out.println("Расстояние между точками " + p3.distance(p4));
		}


	public static void hello(String somebody) {
		System.out.println("Hello, " + somebody +  "!");
	}

	}