class Student {
    String name;
    Student(String n) {
        name = n;
    }
}
public class Main {
    public static void main(String[] args) {
        Student priya = new Student("Priya");
        System.out.println(priya.name);
    }
}
