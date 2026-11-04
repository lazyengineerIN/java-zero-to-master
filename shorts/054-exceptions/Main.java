public class Main {
    static int share(int bill, int people) {
        return bill / people;
    }

    public static void main(String[] args) {
        System.out.println(share(300, 3));
        System.out.println(share(300, 0));
    }
}
