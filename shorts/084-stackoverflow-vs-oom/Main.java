int dig(int n) { return dig(n + 1); }

void main() {
    try {
        dig(1);
    } catch (StackOverflowError e) {
        IO.println("Stack full");
    }
    try {
        var huge = new long[Integer.MAX_VALUE];
    } catch (OutOfMemoryError e) {
        IO.println("Heap full");
    }
}
