import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String header1 = args[0].split("=")[1];
        int threshold = Integer.parseInt(args[1].split("=")[1]);

        Reader read = new Reader();

        File correlacao = new File(header1);
        Grafo mapa = new Grafo();
        try {
            mapa = read.parseFile(correlacao);
        } catch (FileNotFoundException err) {
            System.out.println("Erro ao carregar ficheiro de correlação");
            System.exit(-1);
        }

        mapa.compare(threshold);
    }
}
