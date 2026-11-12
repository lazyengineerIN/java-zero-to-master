void main() {
    int n = 1000;
    int steps = 0;
    for (int i = 0; i < n; i++) steps++;
    IO.println("O(n):  " + steps);
    steps = 0;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++) steps++;
    IO.println("O(n^2): " + steps);
}
