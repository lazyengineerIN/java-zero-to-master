class Vehicle {
    void start() { System.out.println("Go"); }
}
class Bike extends Vehicle {
    void wheelie() { System.out.println("Wheelie!"); }
}
public class Main {
    public static void main(String[] args) {
        Bike bike = new Bike();
        bike.start();
        bike.wheelie();
    }
}
