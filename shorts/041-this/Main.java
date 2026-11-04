class Player {
    String name;
    Player(String name) {
        this.name = name;
    }
}
public class Main {
    public static void main(String[] args) {
        Player captain = new Player("Virat");
        System.out.println(captain.name);
    }
}
