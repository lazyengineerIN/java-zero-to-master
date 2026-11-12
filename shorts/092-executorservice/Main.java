void main() throws Exception {
    try (var pool = Executors.newFixedThreadPool(2)) {
        Future<Integer> tea = pool.submit(() -> 2 * 10);
        Future<Integer> snack = pool.submit(() -> 3 * 15);
        IO.println(tea.get() + snack.get());
    }
}
