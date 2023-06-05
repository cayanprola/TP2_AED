import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        };

        System.out.println(percentages.toString());
    }
}
