class Car {
    String color;
}
public class Main {
    public static void main(String[] args) {
        Car car1 = new Car();
        car1.color = "Red";
        Car car2 = new Car();
        car2.color = "Blue";
        System.out.println(car1.color);
        System.out.println(car2.color);
    }
}
