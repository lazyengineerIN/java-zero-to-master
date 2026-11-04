class NoMoneyException extends Exception {}
public class Main {
    static void pay(int amt) throws NoMoneyException {
        if (amt > 1000) throw new NoMoneyException();
        System.out.println("Paid " + amt);
    }
    public static void main(String[] args) {
        try {
            pay(500);
            pay(5000);
        } catch (NoMoneyException e) {
            System.out.println("Not enough money");
        }
    }
}
