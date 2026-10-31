public class Main {
    public static void main(String[] args) {
        int day = 3;
        String name = switch (day) {
            case 1 -> "Mon";
            case 2 -> "Tue";
            case 3 -> "Wed";
            default -> "?";
        };
        System.out.println(name);
        System.out.println(day > 5 ? "weekend" : "weekday");
    }
}
