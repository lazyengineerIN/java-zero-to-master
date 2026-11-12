void main() {
    var names = List.of("arun", "ravi", "anu", "meena");
    var result = names.stream()
        .filter(n -> n.startsWith("a"))
        .map(String::toUpperCase)
        .toList();
    IO.println(result);
}
