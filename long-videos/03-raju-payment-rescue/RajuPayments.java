import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

enum Size { SMALL, MEDIUM, LARGE }

record MenuItem(String name, double price) {}

class PaymentFailedException extends Exception {
    private final double amount;

    PaymentFailedException(String message, double amount) {
        super(message);
        this.amount = amount;
    }

    double amount() {
        return amount;
    }

    private static final long serialVersionUID = 1L;
}

interface Payment {
    void pay(double amount) throws PaymentFailedException;
}

class CashPayment implements Payment {
    public void pay(double amount) {
        System.out.println("Cash received: " + amount);
    }
}

class UpiPayment implements Payment {
    private final boolean bankUp;

    UpiPayment(boolean bankUp) {
        this.bankUp = bankUp;
    }

    public void pay(double amount) throws PaymentFailedException {
        if (!bankUp) {
            throw new PaymentFailedException("Bank not responding", amount);
        }
        System.out.println("UPI paid: " + amount);
    }
}

class Drawer implements AutoCloseable {
    private final String name;

    Drawer(String name) {
        this.name = name;
        System.out.println(name + " drawer open");
    }

    void put(String thing) {
        System.out.println(thing + " goes in " + name);
    }

    @Override
    public void close() {
        System.out.println(name + " drawer closed");
    }
}

abstract class Order {
    static final double TAX = 0.05; // example rate

    private final MenuItem item;
    private final Size size;
    private final int cups;

    Order(MenuItem item, Size size, int cups) {
        this.item = item;
        this.size = size;
        this.cups = cups;
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

public class RajuPayments {
    // 1. try, catch, finally
    static int readCups(String text) {
        System.out.println("Drawer open");
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            System.out.println("Bad number: " + text);
            return 0;
        } finally {
            System.out.println("Drawer closed");
        }
    }

    // 2. Checked and unchecked
    static String loadMenu(Path file) {
        try {
            return Files.readString(file);
        } catch (IOException e) {
            return "Masala 15";
        }
    }

    // 3. throw, throws and our own exception
    static boolean checkout(Order order, Payment payment) {
        try {
            payment.pay(order.total());
            return true;
        } catch (PaymentFailedException e) {
            System.out.println(e.getMessage() + ", owed " + e.amount());
            return false;
        }
    }

    // 4. try-with-resources
    static void saveBill(Path log, String line) throws IOException {
        try (BufferedWriter out = Files.newBufferedWriter(log,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            out.write(line);
            out.newLine();
        }
    }

    // 6. How ArrayList grows
    static int nextCapacity(int capacity) {
        return capacity + (capacity >> 1);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(readCups("3"));
        System.out.println(readCups("two"));

        System.out.println(loadMenu(Path.of("menu.txt")));

        MenuItem masala = new MenuItem("Masala", 15.0);
        Order[] queue = {
            new DineInOrder(masala, Size.SMALL, 3),
            new DeliveryOrder(masala, Size.LARGE, 2, 20.0),
            new DineInOrder(masala, Size.SMALL, 1),
            new DineInOrder(masala, Size.MEDIUM, 2)
        };
        Payment upi = new UpiPayment(false);

        // 5. Why an array is not enough
        Order[] waiting = new Order[3];
        int count = 0;
        try { // demo only: fix a bug like this, don't catch it
            for (Order order : queue) {
                if (!checkout(order, upi)) {
                    waiting[count] = order;
                    count++;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Crash: " + e.getMessage());
        }

        List<Order> pending = new ArrayList<>();
        for (Order order : queue) {
            if (!checkout(order, upi)) {
                pending.add(order);
            }
        }
        System.out.println("Pending: " + pending.size());

        int capacity = 10;
        StringBuilder sizes = new StringBuilder("10");
        for (int i = 0; i < 4; i++) {
            capacity = nextCapacity(capacity);
            sizes.append(" ").append(capacity);
        }
        System.out.println(sizes);

        // Finale: the bank is back
        Path log = Path.of("bills.txt");
        Files.deleteIfExists(log);
        Payment upiBack = new UpiPayment(true);
        while (!pending.isEmpty()) {
            Order next = pending.get(0);
            if (!checkout(next, upiBack)) {
                break;
            }
            saveBill(log, next.toString());
            pending.remove(0);
        }
        System.out.println("Still pending: " + pending.size());
        System.out.println("Bills saved: " + Files.readAllLines(log).size());

        try (Drawer cash = new Drawer("cash");
             Drawer tokens = new Drawer("tokens")) {
            cash.put("Money");
            tokens.put("Token");
        }
    }
}
