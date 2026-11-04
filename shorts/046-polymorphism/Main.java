class Animal { void speak() {} }
class Dog extends Animal {
    void speak() { System.out.println("Woof"); }
}
class Cat extends Animal {
    void speak() { System.out.println("Meow"); }
}
public class Main {
    public static void main(String[] args) {
        Animal pet = new Dog();
        pet.speak();
        pet = new Cat();
        pet.speak();
    }
}
