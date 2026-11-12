void main() {
    var bill = CompletableFuture
        .supplyAsync(() -> 120)
        .thenApply(price -> price + 30)
        .thenApply(total -> "Pay " + total);
    IO.println(bill.join());
}
