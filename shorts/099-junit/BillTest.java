import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BillTest {
    int bill(int cups) { return cups * 15; }

    @Test
    void twoCupsCost30() {
        assertEquals(30, bill(2));
    }
}
