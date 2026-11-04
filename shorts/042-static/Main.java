class Student {
    static int count = 0;
    Student() { count++; }
}
public class Main {
    public static void main(String[] args) {
        new Student();
        new Student();
        new Student();
        System.out.println(Student.count);
    }
}
