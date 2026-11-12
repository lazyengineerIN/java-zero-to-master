void main() {
    var names = List.of("Ravi", "Meena");
    Optional<String> found = names.stream()
        .filter(n -> n.startsWith("Z"))
        .findFirst();
    IO.println(found.isPresent());
    IO.println(found.orElse("nobody"));
}
