void main() {
    var scores = new TreeMap<Integer, String>();
    scores.put(72, "Ravi");
    scores.put(95, "Meena");
    scores.put(48, "Arun");
    IO.println(scores);
    IO.println(scores.lastKey());
    IO.println(scores.ceilingKey(50));
}
