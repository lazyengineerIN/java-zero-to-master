public class Main {
    static void addBonus(int marks) {
        marks = marks + 10;
    }

    public static void main(String[] args) {
        int marks = 70;
        addBonus(marks);
        System.out.println(marks);
    }
}
