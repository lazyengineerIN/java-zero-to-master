enum Light { RED, YELLOW, GREEN }

public class Main {
    public static void main(String[] args) {
        Light now = Light.RED;
        switch (now) {
            case RED -> System.out.println("Stop");
            case YELLOW -> System.out.println("Wait");
            case GREEN -> System.out.println("Go");
        }
    }
}
