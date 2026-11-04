public class Main {
    public static void main(String[] args) {
        try {
            int age = Integer.parseInt("twenty");
            System.out.println(age);
        } catch (NumberFormatException e) {
            System.out.println("Not a number");
        } finally {
            System.out.println("Form closed");
        }
    }
}
