import java.text.DecimalFormat;
import java.util.*;

public class Grafo {
    private Map<String, Map<String, Integer>> mapa;

    public Grafo() {
        mapa = new HashMap<>();
    }

    public void add(String origem, String destino, int valor) {
        //Adds the correlation between the origin and destination as valor
        if (mapa.get(origem) == null) {
            mapa.put(origem, new HashMap<>());
        }

        mapa.get(origem).put(destino, valor);
    }

    public int get(String origem, String destino) {
        //Return the weight based on the origin and the destination vertexes
        if (mapa.containsKey(origem) && mapa.get(origem).containsKey(destino)) {
            return mapa.get(origem).get(destino);
        }

        return 0;
    }

    public void compare(ArrayList<String> ordem, int threshold) {
        int numGrupos = 1;

        while (ordem.size() > 0) {
            String grupo = ordem.get(0);

            double peso = 0;
            int numeroAcima = 0;

            System.out.print(numGrupos + ": ");
            //Iterate over the vertexes and print the values above the threshold
            int numPrint = 0;
            for (Map.Entry<String, Integer> entry : mapa.get(grupo).entrySet()) {
                if (entry.getValue() >= threshold) {
                    if (numPrint == 0) {
                        System.out.print(entry.getKey());
                    } else {
                        System.out.print(", " + entry.getKey());
                    }

                    numPrint += 1;

                    peso += entry.getValue();
                    numeroAcima += 1;

                    ordem.remove(entry.getKey());
                }
            }
            //Calculate the percentage of the weights
            DecimalFormat f = new DecimalFormat("##.00");
            double percentage = Double.parseDouble(f.format((double) peso / numeroAcima));

            System.out.print(" (" + percentage + "%)");
            System.out.println();
            numGrupos += 1;
        }
    }
}