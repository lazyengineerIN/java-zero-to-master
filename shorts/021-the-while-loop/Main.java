public class Main {
    public static void main(String[] args) {
        int battery = 97;
        while (battery < 100) {
            battery++;
            System.out.println(battery + "%");
        }
        System.out.println("Charged!");
    }
}
