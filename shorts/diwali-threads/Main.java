void main() throws InterruptedException {
    Thread r1 = rocket("R1", 900);
    Thread r2 = rocket("R2", 300);
    Thread r3 = rocket("R3", 600);
    r1.start(); r2.start(); r3.start();
    r1.join(); r2.join(); r3.join();
    IO.println("Happy Diwali!");
}

// Each rocket: wait out the fuse, then boom
Thread rocket(String name, int fuseMs) {
    return new Thread(() -> {
        try {
            Thread.sleep(fuseMs);
        } catch (InterruptedException e) {
            return;
        }
        IO.println(name + " BOOM");
    });
}
