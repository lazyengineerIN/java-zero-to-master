Lock pen = new ReentrantLock();
Lock book = new ReentrantLock();
CountDownLatch bothHold = new CountDownLatch(2);

void grab(Lock mine, Lock wanted) {
    mine.lock();
    bothHold.countDown();
    try {
        bothHold.await();
        if (!wanted.tryLock(1, TimeUnit.SECONDS))
            IO.println("Stuck!");
    } catch (InterruptedException e) { }
}

void main() throws InterruptedException {
    var ravi = new Thread(() -> grab(pen, book));
    var meena = new Thread(() -> grab(book, pen));
    ravi.start(); meena.start();
    ravi.join(); meena.join();
}
