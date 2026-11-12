class Box<T> {
    private T item;
    void put(T item) { this.item = item; }
    T get() { return item; }
}

void main() {
    Box<String> box = new Box<>();
    box.put("Laptop");
    // box.put(42); // compile error
    String thing = box.get();
    IO.println(thing.toUpperCase());
}
