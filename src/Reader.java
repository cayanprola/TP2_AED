import java.io.*;
import java.util.*;

public class Reader {
    /*public Grafo readFile(String filename1, String filename2) {
        //Read both files and parse them to the maps list
        try {
            File file1 = new File(filename1);
            File file2 = new File(filename2);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public Grafo parseFile(File filename) throws FileNotFoundException {
        Grafo mapa = new Grafo();
        boolean start = true;
        int headersAmount = 0;
        ArrayList<String> headers = new ArrayList<>();

        Scanner myReader = new Scanner(filename);
        while (myReader.hasNextLine()) {
            String[] values = myReader.nextLine().split(",", headersAmount);

            if (start) {
                headersAmount = values.length;
                for (int i = 1; i < values.length; i++) {
                    headers.add(values[i]);
                }
                start = false;
            } else {
                String origem = values[0];

                for (int i = 1; i < values.length; i++) {
                    String destino = headers.get(i - 1);
                    int valor = Integer.parseInt(values[i]);

                    mapa.add(origem, destino, valor);
                }
            }
        }
        myReader.close();

        return mapa;
    }
}
