class Person {
    Person() { System.out.println("Person part"); }
}
class Teacher extends Person {
    Teacher() {
        super();
        System.out.println("Teacher part");
    }
}
public class Main {
    public static void main(String[] args) {
        new Teacher();
    }
}
