List<byte[]> cache = new ArrayList<>();

void remember() {
    cache.add(new byte[1_000_000]);
}

void main() {
    for (int i = 0; i < 50; i++) remember();
    IO.println(cache.size() + " MB kept");
    cache.clear();
    IO.println(cache.size() + " MB kept");
}
