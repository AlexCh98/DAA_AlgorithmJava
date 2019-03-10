package Alex.AlgoritmosVoraces;


/* ############ PROBLEMA DE LA MOCHILA ##############
 *
 *   -- Se construye una solución iterativamente.
 *   -- Se toma la decisión óptima en cada iteración.
 *   -- Una vez analizado un candidato (introducir o excluir),
 *      no se reconsidera la decisión.
 *   -- Son voraces porque en cada etapa toman la mejor decisión
 *      sin preocuparse de mañana.
 * */

import java.util.*;

public class AlgoritmoMochila {

    public static class Objeto{
        double[] peso;
        double[] valor;
        double pesoMaximo;
    }

    private static int getBestItem(Objeto data, List<Integer> cand) {
        double bestRatio = 0;
        int bestItem = 0;
        for (int i = 0; i < cand.size(); i++) {
            int c = cand.get(i);
            double r = data.valor[c] / data.peso[c];
            if (r > bestRatio) {
                bestRatio = r;
                bestItem = i;
            }
        }
        return bestItem;
    }

    private static boolean isFeasible(Objeto data, int bestItem, double freeWeight){
        return (freeWeight- data.peso[bestItem] > 0);
    }

    public static double[] greedyAlgorithmKS(Objeto data) {
        int n = data.valor.length;
        List<Integer> candidates = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            candidates.add(i);
        }
        double freeWeight = data.pesoMaximo;
        double[] sol = new double[n];
        boolean isSol = false;
        while (!isSol && !candidates.isEmpty()) {
            int bestItemIdx = getBestItem(data, candidates);
            int bestItem = candidates.remove(bestItemIdx);
            if (isFeasible(data, bestItem, freeWeight)) {
                sol[bestItem] = 1.0;
                freeWeight -= data.peso[bestItem];
            } else{
                //Split the last item, if possible
                sol[bestItem] = freeWeight/data.peso[bestItem];
                isSol = true;
            }
        }
        return sol;
    }

    public static void printSol(Objeto data, double[] sol) {
        System.out.println("item" +"\t" + "valor"  +"\t" +   "weight"  +"\t" +   "Ratio"  +"\t" +  "%Item");
        int n = data.valor.length;
        for (int i = 0; i < n; i++) {
            System.out.println(i + "\t\t" + data.valor[i] + "\t"
                    + data.peso[i] + "\t" + String.format("%.2f",data.valor[i] / data.peso[i])
                    + "\t" + String.format("%.2f",sol[i]));
        }
    }

    public static void main(String[] args) {
        Random rnd = new Random(1234);
        int n = 10;
        Objeto data = new Objeto();
        data.valor = new double[n];
        data.peso = new double[n];
        data.pesoMaximo = 0;

        for (int i = 0; i < n; i++) {
            data.valor[i] = Math.round(rnd.nextDouble() * 96 + 44);
            data.peso[i] = Math.round(rnd.nextDouble() * 89 + 15);
            data.pesoMaximo += data.peso[i];
        }
        data.pesoMaximo *= 0.60;


        double[] sol = greedyAlgorithmKS(data);
        System.out.println(Arrays.toString(sol));
        printSol(data, sol);
    }
}
