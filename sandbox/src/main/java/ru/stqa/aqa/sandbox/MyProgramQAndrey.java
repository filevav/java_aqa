package ru.stqa.aqa.sandbox;

public class MyProgramQAndrey {

	public static void main(String[] args) {
		hello("world");
		hello("user");
		hello("Alexei");

		Square s = new Square(5);
		System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

		Rectangle r = new Rectangle(4, 6);
		System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

		Point p = new Point(-6,-4,1,7);
		System.out.println("Расстояние между точками p1 " + "(" + p.x1 + "; " + p.y1 + ")" + " и p2 (" + p.x2 + "; " + p.y2 + ")" +  " = " + p.distance());

	}

	public static void hello (String somebody) {

		System.out.println("Hello, " + somebody);
	}

}