import java.lang.management.*;

int square(int x) { return x * x; }

void main() {
    long sum = 0;
    for (int i = 0; i < 100_000; i++)
        sum += square(i % 10);
    IO.println(sum);
    var jit = ManagementFactory.getCompilationMXBean();
    IO.println(jit.getName());
}
