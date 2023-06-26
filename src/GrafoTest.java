import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrafoTest {

    private Grafo grafo;

    @BeforeEach
    public void setup() {
        grafo = new Grafo();
    }

    @Test
    public void testAddAndGet() {
        grafo.add("A", "B", 5);
        grafo.add("B", "C", 10);

        assertEquals(5, grafo.get("A", "B"));
        assertEquals(10, grafo.get("B", "C"));
        assertEquals(0, grafo.get("A", "C"));
    }
}
