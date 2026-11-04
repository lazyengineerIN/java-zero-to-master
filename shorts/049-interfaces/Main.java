interface Payable { int amount(); }
class Bill implements Payable {
    public int amount() { return 250; }
}
class Fine implements Payable {
    public int amount() { return 100; }
}
public class Main {
    public static void main(String[] args) {
        Payable due = new Bill();
        System.out.println(due.amount());
        due = new Fine();
        System.out.println(due.amount());
    }
}
