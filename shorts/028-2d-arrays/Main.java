public class Main {
    public static void main(String[] args) {
        String[][] seats = new String[3][4];
        seats[1][2] = "You";
        System.out.println(seats[1][2]);
        System.out.println(seats.length + " rows");
        System.out.println(seats[0].length + " seats");
    }
}
