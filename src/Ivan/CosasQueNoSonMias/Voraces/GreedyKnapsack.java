package Ivan.CosasQueNoSonMias.Voraces;

import java.util.HashSet;

public class GreedyKnapsack {

    private Data data;
    private double[] sol;

    public GreedyKnapsack(Data k) {
        this.data = k;
    }

    private int getBestItem(HashSet<Integer> cand) {
        double bestRatio = 0;
        int bestItem = 0;
        for (int i : cand) {
            double r = this.data.getProfit(i) / this.data.getWeight(i);
            if (r > bestRatio) {
                bestRatio = r;
                bestItem = i;
            }
        }
        return bestItem;

    }

    private boolean isFeasible(int bestItem, double freeWeight) {
        return (freeWeight - this.data.getWeight(bestItem) > 0);
    }

    public void greedyAlgorithmKS() {
        int n = this.data.size();

        HashSet<Integer> cand = new HashSet<>(n);
        for (int i = 0; i < n; i++) {
            cand.add(i);
        }

        double freeWeight = this.data.getMaxWeight();
        this.sol = new double[n];
        boolean solution = false;

        while (!cand.isEmpty() && !solution) {
            int bestItem = getBestItem(cand);
            cand.remove(bestItem);
            if (isFeasible(bestItem, freeWeight)) {
                this.sol[bestItem] = 1.0;
                freeWeight -= this.data.getWeight(bestItem);
            } else {//Split the last item, if possible
                this.sol[bestItem] = freeWeight / this.data.getWeight(bestItem);
                solution = true;
            }
        }
    }

    public void print() {

        System.out.println("item" + "\t" + "profit" + "\t" + "weight" + "\t" + "Ratio" + "\t" + " %Item");
        for (int n = 0; n < this.data.size(); n++) {
            System.out.println(n + "\t" + data.getProfit(n) + "\t"
                    + data.getWeight(n) + "\t" + String.format("%.2f", data.getProfit(n) / data.getWeight(n))
                    + "\t" + String.format("%.2f", this.sol[n]));
        }
    }
}
