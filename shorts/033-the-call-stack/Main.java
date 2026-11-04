public class Main {
    static void pay() {
        System.out.println("pay");
    }
    static void order() {
        pay();
        System.out.println("done");
    }
    public static void main(String[] args) {
        order();
    }
}
