import java.util.Arrays;
import java.util.Scanner;

public class ChaiShop {
    public static void main(String[] args) {
        System.out.println("Welcome to Raju's Chai!");

        // 3. Storing things
        int cups = 0;
        cups = 1;
        double price = 12.5;
        boolean open = true;
        char code = 'M';
        long lifetimeSales = 3_000_000_000L;
        int tooBig = Integer.MAX_VALUE + 1;
        System.out.println(tooBig);
        System.out.println((int) code);
        int rupees = (int) price;
        System.out.println(rupees);
        int boxes = 17 / 5;
        int left = 17 % 5;
        System.out.println(boxes + " boxes, " + left + " left");
        System.out.println(5 / 2 + " vs " + 5 / 2.0);
        int sold = cups++;
        int now = ++cups;

        // 4. Words and customers
        String name = "Asha";
        System.out.println(name.length() + " " + name.charAt(0));
        String greet = "Hi " + name;
        greet = greet + "!";
        String coupon = "CHAI10";
        String typed = new String("CHAI10");
        System.out.println(typed == coupon);
        System.out.println(typed.equals(coupon));
        Scanner in = new Scanner(System.in);
        int order = in.nextInt();

        // 5. Decisions
        if (order >= 10) {
            System.out.println("20% off");
        } else if (order >= 5) {
            System.out.println("10% off");
        } else {
            System.out.println("Full price");
        }
        int menu = 2;
        String chai = switch (menu) {
            case 1 -> "Masala";
            case 2 -> "Ginger";
            case 3 -> "Lemon";
            default -> "?";
        };
        System.out.println(chai);
        boolean raining = true;
        System.out.println(raining ? "Busy" : "Quiet");

        // 6. Loops
        int queue = 3;
        while (queue > 0) {
            System.out.println("Serving " + queue);
            queue--;
        }
        for (int item = 1; item <= 3; item++) {
            System.out.println("Item " + item);
        }
        int choice = 0;
        do {
            System.out.println("1 Bill  0 Close");
        } while (choice != 0);
        for (int chaiNo = 1; chaiNo <= 4; chaiNo++) {
            if (chaiNo == 2) continue;
            if (chaiNo == 4) break;
            System.out.println("Chai " + chaiNo);
        }
        for (int size = 1; size <= 3; size++) {
            for (int kind = 1; kind <= 3; kind++) {
                System.out.print(size * 10 + kind * 5 + " ");
            }
            System.out.println();
        }

        // 7. Arrays
        int[] prices = {10, 15, 20};
        System.out.println(prices[0] + " " + prices.length);
        // prices[3] -> ArrayIndexOutOfBoundsException
        String[][] benches = new String[2][3];
        benches[1][2] = name;
        String[] customers = {"Asha", "Ravi", "Sam"};
        for (String customer : customers) {
            System.out.println("Thanks, " + customer);
        }
        int[] sales = {40, 75, 20};
        Arrays.sort(sales);
        System.out.println(Arrays.toString(sales));
    }
}
