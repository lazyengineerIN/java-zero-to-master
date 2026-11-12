void main() throws InterruptedException {
    var votes = new ConcurrentHashMap<String, Integer>();
    Runnable vote = () -> {
        for (int i = 0; i < 1000; i++)
            votes.merge("chai", 1, Integer::sum);
    };
    var t1 = new Thread(vote);
    var t2 = new Thread(vote);
    t1.start(); t2.start();
    t1.join(); t2.join();
    IO.println(votes);
}
