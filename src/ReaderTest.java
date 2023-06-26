import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReaderTest {

    private Reader reader;

    @BeforeEach
    public void setup() {
        reader = new Reader();
    }

    @Test
    public void testParseFile() {
        File file = new File("testFile.csv");

        try {
            Grafo grafo = reader.parseFile(file);

            assertEquals(5, grafo.get("A", "B"));
            assertEquals(10, grafo.get("B", "C"));
            assertEquals(0, grafo.get("A", "C"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testParseTimeFile() {
        File file = new File("testTimeFile.csv");

        try {
            ArrayList<String> ordem = reader.parseTimeFile(file);

            assertEquals(3, ordem.size());
            assertEquals("A", ordem.get(0));
            assertEquals("B", ordem.get(1));
            assertEquals("C", ordem.get(2));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
