String describe(Object o) {
    return switch (o) {
        case Integer i when i > 100 -> "big number " + i;
        case Integer i -> "number " + i;
        case String s -> "text of length " + s.length();
        default -> "something else";
    };
}

void main() {
    IO.println(describe(500));
    IO.println(describe(7));
    IO.println(describe("chai"));
}
