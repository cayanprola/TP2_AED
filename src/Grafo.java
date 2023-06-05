import java.text.DecimalFormat;
import java.util.*;

public class Grafo {
    private Map<String, Map<String, Integer>> mapa;

    public Grafo() {
        mapa = new HashMap<>();
    }

    public void add(String origem, String destino, int valor) {
        if (mapa.get(origem) == null) {
            mapa.put(origem, new HashMap<>());
        }

        mapa.get(origem).put(destino, valor);
    }

    public int get(String origem, String destino) {
        if (mapa.containsKey(origem) && mapa.get(origem).containsKey(destino)) {
            return mapa.get(origem).get(destino);
        }

        return 0;
    }

    public void compare(int threshold) {
        HashMap<Double, ArrayList<String>> percentages = new HashMap<>();

        for (String key : mapa.keySet()) {
            int numberAbove = 0;
            int pesos = 0;

            for (int valor : mapa.get(key).values()) {
                if (valor >= threshold) {
                    pesos += valor;
                    numberAbove += 1;
                }
            }

            DecimalFormat f = new DecimalFormat("##.00");
            double percentage = Double.parseDouble(f.format((double) pesos / numberAbove));
            percentages.putIfAbsent(percentage, new ArrayList<>());
            percentages.get(percentage).add(key);
        }


        TreeMap<Double, ArrayList<String>> sorted = new TreeMap<>(Collections.reverseOrder());
        sorted.putAll(percentages);
        List<Double> keys = new ArrayList<>(sorted.keySet());

        int cont = 1;
        percentages.clear();
        for (Map.Entry<Double, ArrayList<String>> entry : sorted.entrySet()) {
            for (String submissao : entry.getValue()) {
                if (cont < 3) {
                    percentages.putIfAbsent(entry.getKey(), new ArrayList<>());
                    percentages.get(entry.getKey()).add(submissao);
                } else {
                    // .get(2) para pegar a 3 maior percentagem para fazer o ranking 3
                    percentages.putIfAbsent(keys.get(2), new ArrayList<>());
                    percentages.get(keys.get(2)).add(submissao);
                }
            }
            cont += 1;
        }

        sorted.clear();
        sorted.putAll(percentages);
        cont = 1;

        for (Map.Entry<Double, ArrayList<String>> entry : sorted.entrySet()) {
            System.out.print(cont + ": ");
            for (String submissao : entry.getValue()) {
                System.out.print(submissao + " ");
            }
            System.out.print("(" + entry.getKey() + "%)");
            System.out.println();
            cont += 1;
        }
    }
}