void main() {
    var stock = new HashMap<String, Integer>();
    stock.put("apple", 3);
    int h = "apple".hashCode();
    int bucket = (h ^ (h >>> 16)) & 15;
    IO.println(h);
    IO.println(bucket);
    IO.println(stock.get("apple"));
}
