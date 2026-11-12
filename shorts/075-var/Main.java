void main() {
    var price = 250;
    var item = "Chai";
    var orders = new ArrayList<String>();
    orders.add(item);
    // price = "free";  // compile error: still an int
    IO.println(item + " " + price + " " + orders);
}
