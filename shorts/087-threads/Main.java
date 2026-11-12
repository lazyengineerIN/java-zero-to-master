void main() throws InterruptedException {
    int[] sums = new int[2];
    var t1 = new Thread(() -> sums[0] = 10 + 20);
    var t2 = new Thread(() -> sums[1] = 30 + 40);
    t1.start();
    t2.start();
    t1.join();
    t2.join();
    IO.println(sums[0] + sums[1]);
}
