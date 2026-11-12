void main() {
    var done = new AtomicInteger();
    try (var pool = Executors.newVirtualThreadPerTaskExecutor()) {
        for (int i = 0; i < 10_000; i++) {
            pool.submit(() -> {
                Thread.sleep(1000);
                return done.incrementAndGet();
            });
        }
    }
    IO.println(done.get());
}
