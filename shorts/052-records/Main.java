record Ticket(String name, int seat) {}

public class Main {
    public static void main(String[] args) {
        Ticket ticket = new Ticket("Anu", 42);
        System.out.println(ticket.name());
        System.out.println(ticket);
    }
}
