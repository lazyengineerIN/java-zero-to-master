public class Main {
    public static void main(String[] args) {
        for (int song = 1; song <= 5; song++) {
            if (song == 2) continue;
            System.out.println("Song " + song);
            if (song == 3) break;
        }
    }
}
