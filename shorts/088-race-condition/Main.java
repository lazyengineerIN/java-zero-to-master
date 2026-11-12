int count = 0;

void main() throws InterruptedException {
    Runnable add = () -> {
        for (int i = 0; i < 1_000_000; i++) count++;
    };
    var t1 = new Thread(add);
    var t2 = new Thread(add);
    t1.start(); t2.start();
    t1.join(); t2.join();
    IO.println(count == 2_000_000);
}
