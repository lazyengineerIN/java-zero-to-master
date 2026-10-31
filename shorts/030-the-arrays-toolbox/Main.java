import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] scores = {45, 12, 99, 7};
        Arrays.sort(scores);
        System.out.println(Arrays.toString(scores));
        System.out.println("Top: " + scores[3]);
    }
}
