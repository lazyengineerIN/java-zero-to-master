volatile boolean running = true;

void main() throws InterruptedException {
    var worker = new Thread(() -> {
        while (running) { }
        IO.println("Worker stopped");
    });
    worker.start();
    Thread.sleep(100);
    running = false;
    worker.join();
    IO.println("Done");
}
