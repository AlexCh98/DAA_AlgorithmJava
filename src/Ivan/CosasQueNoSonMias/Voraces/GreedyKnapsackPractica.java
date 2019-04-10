package Ivan.CosasQueNoSonMias.Voraces;

import java.util.HashSet;

public class GreedyKnapsackPractica {

    //16 minutos

    private Data data;
    private double[] sol;

    public GreedyKnapsackPractica(Data data) {
        this.data = data;
        sol = new double[data.size()];
    }

    private int getBestItem(HashSet<Integer> cand) {
        double bestRatio = 0;
        int bestItem = 0;
        for (int i : cand) {
            double ratio = data.getProfit(i) / data.getWeight(i);
            if (ratio > bestRatio) {
                bestRatio = ratio;
                bestItem = i;
            }
        }
        return bestItem;
    }

    private boolean isFeasible(int bestItem, double freeWeight) {
        return (freeWeight - data.getWeight(bestItem)) > 0;
    }

    public void greedyAlgorithmKS() {
        HashSet<Integer> cand = new HashSet<>();
        double freeWeight = data.getMaxWeight();

        for (int i = 0; i < data.size(); i++) {
            cand.add(i);
        }

        boolean solution = false;

        while (!cand.isEmpty() && !solution) {
            int bestItem = this.getBestItem(cand);
            cand.remove(bestItem);

            if (isFeasible(bestItem, freeWeight)) {
                this.sol[bestItem] = 1;
                freeWeight -= data.getWeight(bestItem);
            } else {
                this.sol[bestItem] = freeWeight / data.getWeight(bestItem);
                solution = true;
            }

        }

    }

}
