import java.lang.management.*;

void main() {
    var pools = ManagementFactory
            .getMemoryPoolMXBeans();
    for (var pool : pools) {
        if (pool.getType() == MemoryType.HEAP) {
            IO.println(pool.getName());
        }
    }
}
