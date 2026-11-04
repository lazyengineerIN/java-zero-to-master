import java.io.IOException;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) {
        try {
            Files.readString(Path.of("marks.txt"));
        } catch (IOException e) {
            System.out.println("File missing");
        }
        int n = Integer.parseInt("42");
        System.out.println(n);
    }
}
