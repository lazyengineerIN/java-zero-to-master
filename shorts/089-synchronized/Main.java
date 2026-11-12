int count = 0;

synchronized void add() { count++; }

void main() throws InterruptedException {
    Runnable work = () -> {
        for (int i = 0; i < 1_000_000; i++) add();
    };
    var t1 = new Thread(work);
    var t2 = new Thread(work);
    t1.start(); t2.start();
    t1.join(); t2.join();
    IO.println(count);
}
