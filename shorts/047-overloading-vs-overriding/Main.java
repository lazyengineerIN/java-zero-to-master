class Mom {
    void cook() { System.out.println("Dal"); }
    void cook(int n) { System.out.println(n + " Dal"); }
}
class Son extends Mom {
    @Override
    void cook() { System.out.println("Pasta"); }
}
public class Main {
    public static void main(String[] args) {
        new Mom().cook(2);
        new Son().cook();
    }
}
