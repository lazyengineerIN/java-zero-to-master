void main() {
    var array = new ArrayList<Integer>();
    var linked = new LinkedList<Integer>();
    for (int i = 0; i < 1000; i++) {
        array.add(i);
        linked.add(i);
    }
    IO.println(array.get(250));
    IO.println(linked.get(250));
}
