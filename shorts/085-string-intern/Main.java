void main() {
    String pooled = "chai";
    String built = new String("chai");
    IO.println(pooled == built);
    String shared = built.intern();
    IO.println(pooled == shared);
}
