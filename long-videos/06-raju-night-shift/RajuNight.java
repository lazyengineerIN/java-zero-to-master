import java.lang.management.*;
import java.lang.ref.WeakReference;
import java.util.*;

// 1. Class loading
class Menu {
    static { IO.println("Menu loaded"); }
    static String today() { return "Masala"; }
}

class Unused {
    static { IO.println("Unused loaded"); }
}

// 3. Garbage collection
class Cup {
    Cup other;
}

// 5. Memory leaks
List<byte[]> cache = new ArrayList<>();

void remember() {
    cache.add(new byte[1_000_000]);
}

Map<Integer, byte[]> lastFive = new LinkedHashMap<>() {
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, byte[]> eldest) {
        return size() > 5;
    }
};

// 4. JIT
int price(int cups) { return cups * 15; }

// 6. Stack
int dig(int n) { return dig(n + 1); }

// 8. Stack trace
int parseToken(String text) { return Integer.parseInt(text); }

void main() {
    IO.println("-- 1 Class loading");
    IO.println("Start");
    IO.println(Menu.today());
    IO.println(Menu.today());
    IO.println("Menu loader: " + Menu.class.getClassLoader().getName());
    IO.println("String loader: " + String.class.getClassLoader());
    IO.println("java.sql loader: " + java.sql.Driver.class.getClassLoader().getName());

    IO.println("-- 2 Heap generations");
    for (var pool : ManagementFactory.getMemoryPoolMXBeans()) {
        if (pool.getType() == MemoryType.HEAP) IO.println(pool.getName());
    }

    IO.println("-- 3 Garbage collection");
    var toy = new Object();
    var note = new WeakReference<>(toy);
    IO.println("Reachable: " + (note.get() != null));
    toy = null;
    for (int i = 0; i < 10 && note.get() != null; i++) System.gc();
    IO.println("Collected: " + (note.get() == null));
    var a = new Cup();
    var b = new Cup();
    a.other = b;
    b.other = a;
    var watch = new WeakReference<>(a);
    a = null;
    b = null;
    for (int i = 0; i < 10 && watch.get() != null; i++) System.gc();
    IO.println("Cup pair collected: " + (watch.get() == null));

    IO.println("-- 4 JIT");
    long total = 0;
    for (int i = 0; i < 200_000; i++) total += price(i % 4 + 1);
    IO.println("Total: " + total);
    var jit = ManagementFactory.getCompilationMXBean();
    IO.println(jit.getName());
    IO.println("JIT worked: " + (jit.getTotalCompilationTime() > 0));

    IO.println("-- 5 Memory leaks");
    for (int i = 0; i < 20; i++) remember();
    IO.println(cache.size() + " MB kept");
    cache.clear();
    IO.println(cache.size() + " MB kept");
    for (int i = 1; i <= 20; i++) lastFive.put(i, new byte[1_000_000]);
    IO.println(lastFive.size() + " MB kept, oldest: " + lastFive.keySet().iterator().next());

    IO.println("-- 6 Stack and heap");
    try {
        dig(1);
    } catch (StackOverflowError e) {
        IO.println("Stack full: " + e.getClass().getSimpleName());
    }
    try {
        var huge = new long[100_000_000];
    } catch (OutOfMemoryError e) {
        IO.println("Heap full: " + e.getMessage());
    }

    IO.println("-- 7 String pool");
    String pooled = "chai";
    String built = new String("chai");
    IO.println(pooled == built);
    IO.println(pooled.equals(built));
    IO.println(pooled == built.intern());
    String part = "ch";
    String joined = part + "ai";
    IO.println(joined == pooled);
    IO.println(("ch" + "ai") == pooled);

    IO.println("-- 8 Stack trace");
    try {
        parseToken("7a");
    } catch (NumberFormatException e) {
        IO.println(e.getMessage());
        var frames = e.getStackTrace();
        IO.println("Frames: " + frames.length);
        IO.println("Top: " + frames[0].getClassName() + "." + frames[0].getMethodName());
        for (var f : frames) {
            if (f.getClassName().startsWith("RajuNight")) {
                IO.println("Yours: " + f);
                break;
            }
        }
    }
}
