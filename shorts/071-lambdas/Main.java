void main() {
    var names = new ArrayList<>(
        List.of("Meena", "Ravi", "Arun"));
    names.sort((x, y) -> x.length() - y.length());
    IO.println(names);
    names.forEach(n -> IO.println("Hi " + n));
}
