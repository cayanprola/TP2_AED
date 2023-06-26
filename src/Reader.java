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
    //Reads a file with data and save it to the graph
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
    //Reads the file in a specified order and save its values
    public ArrayList<String> parseTimeFile(File filename) throws FileNotFoundException {
        ArrayList<String> ordem = new ArrayList<>();
        boolean start = true;
        int headersAmount = 0;
        ArrayList<String> headers = new ArrayList<>();

        Scanner myReader = new Scanner(filename);
        while (myReader.hasNextLine()) {
            String[] values = myReader.nextLine().split(",", headersAmount);

            if (start) {
                start = false;
            } else {
                ordem.add(values[0]);
            }
        }
        myReader.close();

        return ordem;
    }
}
