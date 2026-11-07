import java.util.*;

// Long 04: Raju's festival rush. Java 0 -> Master #61-70.
// Run: javac -Xlint:all RajuFestival.java && java RajuFestival

record Order(int token, String customer, String drink, int cups, boolean cancelled)
        implements Comparable<Order> {

    // Natural order: by token number (#68)
    @Override
    public int compareTo(Order other) {
        return Integer.compare(token, other.token);
    }

    @Override
    public String toString() {
        return "#" + token + " " + customer + ", " + drink + " x" + cups;
    }
}

// A tray that holds one kind of thing (#69)
class Tray<T> {
    private final List<T> items = new ArrayList<>();

    void put(T item) {
        items.add(item);
    }

    T take() {
        return items.removeFirst();
    }
}

public class RajuFestival {

    static final String[] NAMES = {"Kavya", "Priya", "Arun", "Meena", "Ravi", "Divya", "Karthik"};
    static final String[] DRINKS = {"Masala", "Ginger", "Lemon", "Elaichi", "Coffee"};

    static Order makeOrder(int token) {
        return new Order(token, NAMES[token % NAMES.length],
                DRINKS[token % DRINKS.length], token % 3 + 1, false);
    }

    public static void main(String[] args) {
        // The festival day: 1,000 orders, tokens 1 to 1000
        List<Order> today = new ArrayList<>();
        for (int t = 1; t <= 1000; t++) {
            today.add(makeOrder(t));
        }

        // #61 LinkedList: the counter queue
        System.out.println("== 1 LinkedList ==");
        LinkedList<Order> line = new LinkedList<>();
        line.addLast(today.get(0)); // token 1
        line.addLast(today.get(1)); // token 2
        line.addLast(today.get(2)); // token 3
        System.out.println("Serving " + line.removeFirst());
        System.out.println("Next up " + line.getFirst());
        System.out.println("In line: " + line.size());

        // #62 ArrayList vs LinkedList: hops to reach the middle
        System.out.println("== 2 ArrayList vs LinkedList ==");
        int middle = 499;
        System.out.println("ArrayList jumps to index " + middle + ": 1 step");
        System.out.println("LinkedList walks to index " + middle + ": " + middle + " hops");

        // #63 HashMap: find token 742 at once
        System.out.println("== 3 HashMap ==");
        Map<Integer, Order> byToken = new HashMap<>();
        for (Order o : today) {
            byToken.put(o.token(), o);
        }
        System.out.println("Found " + byToken.get(742));
        System.out.println("742 goes to bucket " + (742 % 16) + " of 16");

        // #64 Collisions: same bucket, different keys
        System.out.println("== 4 Collisions ==");
        for (int token : new int[] {6, 22, 742}) {
            System.out.println(token + " -> bucket " + (token % 16) + " of 16, "
                    + (token % 64) + " of 64");
        }

        // #65 HashSet: one free biscuit per customer
        System.out.println("== 5 HashSet ==");
        Set<String> gotBiscuit = new HashSet<>();
        for (int t : new int[] {1, 2, 8}) {
            String name = byToken.get(t).customer();
            System.out.println("Token " + t + ", " + name + ": " + gotBiscuit.add(name));
        }
        System.out.println("Biscuits given: " + gotBiscuit.size());

        // #66 TreeMap: cups sold per drink, sorted
        System.out.println("== 6 TreeMap ==");
        Map<String, Integer> hashSales = new HashMap<>();
        Map<String, Integer> treeSales = new TreeMap<>();
        for (Order o : today) {
            hashSales.put(o.drink(), hashSales.getOrDefault(o.drink(), 0) + o.cups());
            treeSales.put(o.drink(), treeSales.getOrDefault(o.drink(), 0) + o.cups());
        }
        System.out.println("HashMap: " + hashSales);
        System.out.println("TreeMap: " + treeSales);

        // #67 Removing while looping
        System.out.println("== 7 Iterator ==");
        List<Order> pending = new ArrayList<>();
        for (int t = 201; t <= 205; t++) {
            Order o = byToken.get(t);
            pending.add(t == 202 || t == 205 ? cancel(o) : o); // 202 and 205 cancelled
        }
        List<Order> copy = new ArrayList<>(pending); // demo only, so the real list stays whole
        try {
            for (Order o : copy) {
                if (o.cancelled()) {
                    copy.remove(o);
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Crash: " + e.getClass().getSimpleName());
        }
        pending.removeIf(o -> o.cancelled());
        System.out.println("Still pending: " + pending.size());

        // #68 Comparable vs Comparator
        System.out.println("== 8 Sorting ==");
        List<Order> toMake = new ArrayList<>(List.of(
                byToken.get(304), byToken.get(301), byToken.get(303), byToken.get(302)));
        Collections.sort(toMake);
        System.out.println("By token: " + tokens(toMake));
        toMake.sort(Comparator.comparingInt(Order::cups));
        System.out.println("Fewest cups first: " + tokens(toMake));
        for (Order o : toMake) {
            System.out.println("  " + o);
        }

        // #69 Generics
        System.out.println("== 9 Generics ==");
        Tray<Order> orderTray = new Tray<>();
        orderTray.put(byToken.get(742));
        // orderTray.put("Samosa"); // compile error: String cannot be converted to Order
        Order first = orderTray.take(); // no cast needed
        System.out.println("From the tray: " + first);

        // #70 Big-O: steps to find token 742
        System.out.println("== 10 Big-O ==");
        int checks = 0;
        for (Order o : today) {
            checks++;
            if (o.token() == 742) {
                break;
            }
        }
        System.out.println("List checks: " + checks);
        TreeMap<Integer, Order> sortedTokens = new TreeMap<>(byToken);
        System.out.println("TreeMap levels: about " + treeSteps(1000));
        System.out.println("HashMap: 1 bucket, " + sortedTokens.get(742).customer());
    }

    static Order cancel(Order o) {
        return new Order(o.token(), o.customer(), o.drink(), o.cups(), true);
    }

    static List<Integer> tokens(List<Order> orders) {
        List<Integer> out = new ArrayList<>();
        for (Order o : orders) {
            out.add(o.token());
        }
        return out;
    }

    // How many halvings to narrow n items down to 1 (balanced tree height)
    static int treeSteps(int n) {
        int steps = 0;
        while (n > 1) {
            n = (n + 1) / 2;
            steps++;
        }
        return steps;
    }
}
