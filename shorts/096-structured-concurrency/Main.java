void main() throws InterruptedException {
    try (var scope = StructuredTaskScope.open()) {
        var tea = scope.fork(() -> "tea");
        var bun = scope.fork(() -> "bun");
        scope.join();
        IO.println(tea.get() + " + " + bun.get());
    }
}
