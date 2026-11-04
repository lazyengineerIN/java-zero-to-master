class Tap implements AutoCloseable {
    public void close() {
        System.out.println("Tap closed");
    }
}
public class Main {
    public static void main(String[] args) {
        try (Tap tap = new Tap()) {
            System.out.println("Water flowing");
        }
        System.out.println("Done");
    }
}
