void main() {
    IO.println("Aa".hashCode());
    IO.println("BB".hashCode());
    var map = new HashMap<String, Integer>();
    map.put("Aa", 1);
    map.put("BB", 2);
    IO.println(map.get("Aa") + " " + map.get("BB"));
}
