class Phone {
    String model = "Pixel";
    @Override
    public String toString() {
        return "Phone: " + model;
    }
}
public class Main {
    public static void main(String[] args) {
        Phone myPhone = new Phone();
        System.out.println(myPhone instanceof Object);
        System.out.println(myPhone);
    }
}
