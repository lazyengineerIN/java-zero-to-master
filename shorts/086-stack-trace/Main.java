String shout(String name) {
    return name.toUpperCase();
}

void main() {
    try {
        shout(null);
    } catch (NullPointerException e) {
        IO.println(e.getStackTrace()[0]);
        IO.println(e.getStackTrace()[1]);
    }
}
