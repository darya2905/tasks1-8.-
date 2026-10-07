class Point {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point other) {
        return Math.hypot(this.x - other.x, this.y - other.y);
    }
}

class Triangle {
    Point a, b, c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double perimeter() {
        return a.distance(b) + b.distance(c) + c.distance(a);
    }

    public double area() {
        return Math.abs((a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)) / 2.0);
    }

    public boolean containsPoint(Point p) {
        double s1 = new Triangle(p, a, b).area();
        double s2 = new Triangle(p, b, c).area();
        double s3 = new Triangle(p, c, a).area();
        return Math.abs(area() - (s1 + s2 + s3)) < 1e-9;
    }
}

public class Main {
    public static void main(String[] args) {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 0);
        Point p3 = new Point(0, 3);

        Triangle t = new Triangle(p1, p2, p3);

        System.out.println("Периметр: " + t.perimeter());
        System.out.println("Площадь: " + t.area());

        Point inside = new Point(1, 1);
        Point outside = new Point(5, 5);

        System.out.println("Точка (1, 1) внутри? " + t.containsPoint(inside));
        System.out.println("Точка (5, 5) внутри? " + t.containsPoint(outside));
    }
}