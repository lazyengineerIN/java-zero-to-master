void main() throws IOException {
    Path list = Files.createTempFile("shopping", ".txt");
    Files.writeString(list, "milk\nbread\neggs");
    List<String> items = Files.readAllLines(list);
    IO.println(items);
    IO.println(items.size() + " items");
    Files.delete(list);
}
