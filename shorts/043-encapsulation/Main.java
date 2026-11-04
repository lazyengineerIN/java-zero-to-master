class Account {
    private int balance;
    void deposit(int amt) {
        if (amt > 0) balance += amt;
    }
    int balance() { return balance; }
}
public class Main {
    public static void main(String[] args) {
        Account acc = new Account();
        acc.deposit(500);
        acc.deposit(-100);
        System.out.println(acc.balance());
    }
}
