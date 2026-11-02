enum Size { SMALL, MEDIUM, LARGE }

record MenuItem(String name, double price) {}

interface Payment {
    void pay(double amount);
}

class CashPayment implements Payment {
    public void pay(double amount) {
        System.out.println("Cash received: " + amount);
    }
}

class UpiPayment implements Payment {
    public void pay(double amount) {
        System.out.println("UPI paid: " + amount);
    }
}

abstract class Order {
    static final double TAX = 0.05; // example rate
    private static int count = 0;

    private final MenuItem item;
    private final Size size;
    private int cups;

    Order(MenuItem item, Size size, int cups) {
        this.item = item;
        this.size = size;
        this.cups = cups;
        count++;
    }

    void addCups(int more) {
        if (more > 0) {
            cups += more;
        }
    }

    double cupPrice() {
        double extra = switch (size) {
            case SMALL -> 0;
            case MEDIUM -> 5;
            case LARGE -> 10;
        };
        return item.price() + extra;
    }

    double total() {
        double subtotal = cupPrice() * cups;
        return subtotal + subtotal * TAX;
    }

    abstract String type();

    static int count() {
        return count;
    }

    @Override
    public String toString() {
        return type() + ": " + cups + " x " + size + " " + item.name() + " = " + total();
    }
}

class DineInOrder extends Order {
    DineInOrder(MenuItem item, Size size, int cups) {
        super(item, size, cups);
    }

    String type() {
        return "Dine-in";
    }
}

class DeliveryOrder extends Order {
    private final double fee;

    DeliveryOrder(MenuItem item, Size size, int cups, double fee) {
        super(item, size, cups);
        this.fee = fee;
    }

    String type() {
        return "Delivery";
    }

    @Override
    double total() {
        return super.total() + fee;
    }
}

public class RajuOrders {
    // 1. Stop repeating
    static double billTotal(int cups, double price) {
        return cups * price;
    }

    static double billTotal(int cups, double price, double discount) {
        return billTotal(cups, price) - discount;
    }

    // 2. Inside a method
    static void tryDiscount(double price) {
        price = price - 5;
    }

    static double tax(double amount) {
        return amount * Order.TAX;
    }

    static void printBill(int cups, double price) {
        double amount = billTotal(cups, price);
        System.out.println("Bill: " + (amount + tax(amount)));
    }

    static int cupsInCrates(int crates) {
        if (crates == 0) {
            return 0;
        }
        return 6 + cupsInCrates(crates - 1);
    }

    // 8. When things fail
    static int cupsEach(int cups, int people) {
        return cups / people;
    }

    static void splitOrder(int cups, int people) {
        System.out.println("Each gets: " + cupsEach(cups, people));
    }

    public static void main(String[] args) {
        System.out.println(billTotal(2, 15.0));
        System.out.println(billTotal(2, 15.0, 5.0));

        double price = 15.0;
        tryDiscount(price);
        System.out.println(price);

        printBill(2, 15.0);
        System.out.println(cupsInCrates(3));

        // 3. Where data lives
        // String customer = null;
        // customer.length(); -> NullPointerException

        // 4 to 7. Objects
        MenuItem masala = new MenuItem("Masala", 15.0);
        System.out.println(masala);

        Order table = new DineInOrder(masala, Size.SMALL, 2);
        table.addCups(1);
        table.addCups(-50);
        Order home = new DeliveryOrder(masala, Size.LARGE, 2, 20.0);

        Order[] orders = {table, home};
        for (Order order : orders) {
            System.out.println(order);
        }

        Payment[] payments = {new CashPayment(), new UpiPayment()};
        for (int i = 0; i < orders.length; i++) {
            payments[i].pay(orders[i].total());
        }

        System.out.println("Orders today: " + Order.count());

        splitOrder(4, 2);
        try {
            splitOrder(4, 0);
        } catch (ArithmeticException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
