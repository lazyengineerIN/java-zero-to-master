void main() {
    var cart = new ArrayList<String>();
    cart.addAll(List.of("pen", "gum", "book"));
    try {
        for (String item : cart) {
            if (item.equals("pen"))
                cart.remove(item);
        }
    } catch (ConcurrentModificationException e) {
        var name = e.getClass().getSimpleName();
        IO.println("Crash: " + name);
    }
    cart.removeIf(item -> item.equals("pen"));
    IO.println(cart);
}
