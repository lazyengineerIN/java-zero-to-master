import java.lang.ref.WeakReference;

void main() {
    var toy = new Object();
    var note = new WeakReference<>(toy);
    IO.println(note.get() != null);
    toy = null;
    System.gc();
    IO.println(note.get() == null);
}
