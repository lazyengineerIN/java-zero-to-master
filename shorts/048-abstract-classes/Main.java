abstract class Shape {
    abstract double area();
}
class Square extends Shape {
    double side = 3;
    double area() { return side * side; }
}
public class Main {
    public static void main(String[] args) {
        Shape shape = new Square();
        System.out.println(shape.area());
    }
}
