import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.stream.*;

// Long 05: the morning after the festival. Java 0 -> Master #71-78 (Modern Java).
// Run: javac -Xlint:all RajuMorning.java && java RajuMorning

// Same order shape as Long 04, so every token has the same customer, drink and cups
record Order(int token, String customer, String drink, int cups, boolean cancelled) {
    @Override
    public String toString() {
        return "#" + token + " " + customer + ", " + drink + " x" + cups;
    }
}

// #78 Sealed: only these kinds of payment exist
sealed interface Payment permits Cash, Upi, Card {}

record Cash(int amount) implements Payment {}

record Upi(int amount, String ref) implements Payment {}

record Card(int amount, String last4) implements Payment {}

public class RajuMorning {

    static final String[] NAMES = {"Kavya", "Priya", "Arun", "Meena", "Ravi", "Divya", "Karthik"};
    static final String[] DRINKS = {"Masala", "Ginger", "Lemon", "Elaichi", "Coffee"};
    static final int PRICE = 15; // festival price: every cup 15 rupees

    static Order makeOrder(int token) {
        return new Order(token, NAMES[token % NAMES.length],
                DRINKS[token % DRINKS.length], token % 3 + 1, false);
    }

    public static void main(String[] args) {
        // Yesterday: 1,000 orders, tokens 1 to 1000
        List<Order> today = new ArrayList<>();
        for (int t = 1; t <= 1000; t++) {
            today.add(makeOrder(t));
        }

        // Cold open: the old way, a loop to count cups
        int oldTotal = 0;
        for (Order o : today) {
            oldTotal += o.cups();
        }
        System.out.println("Old loop total: " + oldTotal);

        // #71 Lambdas: the same sort rule, long and short
        System.out.println("== 1 Lambdas ==");
        List<Order> toMake = new ArrayList<>(List.of(
                today.get(303), today.get(300), today.get(302), today.get(301))); // 304, 301, 303, 302
        toMake.sort(new Comparator<Order>() {
            @Override
            public int compare(Order a, Order b) {
                return Integer.compare(a.cups(), b.cups());
            }
        });
        System.out.println("Anonymous class: " + tokens(toMake));
        toMake = new ArrayList<>(List.of(
                today.get(303), today.get(300), today.get(302), today.get(301)));
        toMake.sort((a, b) -> Integer.compare(a.cups(), b.cups()));
        System.out.println("Lambda: " + tokens(toMake));

        // #72 Functional interfaces: one-method sockets
        System.out.println("== 2 Functional interfaces ==");
        Predicate<Order> bigOrder = o -> o.cups() == 3;
        Function<Order, String> label = o -> o.customer() + " wants " + o.drink();
        Consumer<String> shout = s -> System.out.println(s.toUpperCase() + "!");
        Supplier<List<Order>> newTray = ArrayList::new;
        ToIntFunction<Order> cupsOf = Order::cups; // method reference
        Order kavya = today.get(741); // token 742
        System.out.println("Token 742 big? " + bigOrder.test(kavya));
        System.out.println("Token 302 big? " + bigOrder.test(today.get(301)));
        System.out.println(label.apply(kavya));
        shout.accept("next order");
        System.out.println("New tray size: " + newTray.get().size());
        System.out.println("Cups of 742: " + cupsOf.applyAsInt(kavya));

        // #73 Streams: filter -> map -> collect
        System.out.println("== 3 Streams ==");
        int totalCups = today.stream().mapToInt(Order::cups).sum();
        System.out.println("Total cups: " + totalCups);
        List<String> kNames = today.stream()
                .map(Order::customer)
                .filter(n -> n.startsWith("K"))
                .map(String::toUpperCase)
                .distinct()
                .toList();
        System.out.println("K customers: " + kNames);
        // Each order goes through the whole pipe before the next one starts (demo only: prints inside lambdas)
        List<String> firstTwo = today.stream().limit(2)
                .filter(o -> {
                    System.out.println("  filter #" + o.token());
                    return true;
                })
                .map(o -> {
                    System.out.println("  map #" + o.token());
                    return o.customer();
                })
                .toList();
        System.out.println("First two: " + firstTwo);
        // Lazy: the first big coffee order, and how many orders were looked at
        AtomicInteger looked = new AtomicInteger();
        Order firstBigCoffee = today.stream()
                .filter(o -> {
                    looked.incrementAndGet();
                    return o.drink().equals("Coffee") && o.cups() == 3;
                })
                .findFirst()
                .orElseThrow();
        System.out.println("First big coffee: " + firstBigCoffee);
        System.out.println("Orders looked at: " + looked.get() + " of " + today.size());

        // #74 Optional: maybe there, maybe not
        System.out.println("== 4 Optional ==");
        Optional<Order> kavyaFirst = today.stream()
                .filter(o -> o.customer().equals("Kavya"))
                .findFirst();
        System.out.println("Kavya first: " + kavyaFirst.map(Order::toString).orElse("none"));
        Optional<Order> zara = today.stream()
                .filter(o -> o.customer().equals("Zara"))
                .findFirst();
        System.out.println("Zara present? " + zara.isPresent());
        System.out.println("Zara first: " + zara.map(Order::toString).orElse("none"));

        // #75 var: Java works out the type
        System.out.println("== 5 var ==");
        var sales = new TreeMap<String, Integer>();
        for (var o : today) {
            sales.merge(o.drink(), o.cups(), Integer::sum);
        }
        // sales = "hello"; // compile error: still a TreeMap
        System.out.println("Sales: " + sales);

        // #76 Text blocks: the morning report
        System.out.println("== 6 Text blocks ==");
        String report = """
                RAJU CHAI STALL
                Festival day report
                Orders: %d
                Cups:   %d
                Takings: Rs %d
                """.formatted(today.size(), totalCups, totalCups * PRICE);
        System.out.print(report);

        // #77 Pattern matching: payments of every kind
        System.out.println("== 7 Pattern matching ==");
        // Each payment is for a real order: 302 Priya x3, 742 Kavya x2, 303 Arun x1
        List<Payment> payments = List.of(
                new Cash(today.get(301).cups() * PRICE),
                new Upi(kavya.cups() * PRICE, "UPI-742"),
                new Card(today.get(302).cups() * PRICE, "4821"));
        Object first = payments.get(1);
        if (first instanceof Upi u) {
            System.out.println("instanceof: UPI ref " + u.ref());
        }

        // #78 Sealed: the switch covers every kind, no default
        System.out.println("== 8 Sealed ==");
        int cashTotal = 0;
        for (Payment p : payments) {
            System.out.println(describe(p));
            if (p instanceof Cash c) {
                cashTotal += c.amount();
            }
        }
        System.out.println("Cash in drawer: Rs " + cashTotal);

        // Finale: Long 04's homework, in one line
        System.out.println("== Finale ==");
        Map<String, Integer> cupsByCustomer = today.stream()
                .collect(Collectors.groupingBy(Order::customer, TreeMap::new,
                        Collectors.summingInt(Order::cups)));
        System.out.println("Cups by customer: " + cupsByCustomer);
        System.out.println("Stream total: " + totalCups + " (loop said " + oldTotal + ")");
    }

    static String describe(Payment p) {
        return switch (p) {
            case Cash c -> "Cash Rs " + c.amount();
            case Upi u -> "UPI Rs " + u.amount() + ", ref " + u.ref();
            case Card k -> "Card Rs " + k.amount() + ", ending " + k.last4();
        };
    }

    static List<Integer> tokens(List<Order> orders) {
        return orders.stream().map(Order::token).toList();
    }
}
