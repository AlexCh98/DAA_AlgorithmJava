package Ivan.CosasQueNoSonMias.BackTracking;

import java.util.ArrayList;

public class BTKnapsack01Contrarreloj {

    //10 minutos 30 segundos

    private Data d;
    private ArrayList<Integer> solParcial;
    private ArrayList<Integer> bestSol;
    private double pesoMax;

    public BTKnapsack01Contrarreloj(Data d) {
        this.d = d;
        this.solParcial = new ArrayList<>();
        this.bestSol = new ArrayList<>();
        this.pesoMax = d.getMaxWeight();
    }

    public ArrayList<Integer> algorithm() {
        BTrec(0);
        return bestSol;
    }

    private double getBestSolValue() {
        double total = 0;

        for (int i : bestSol) {
            total = +d.getProfit(i);
        }
        return total;
    }

    private double getSolValue() {
        double total = 0;
        for (int i = 0; i < bestSol.size(); i++) {
            total = +d.getProfit(solParcial.get(i));
        }
        return total;
    }

    private double getPeso() {
        double total = 0;
        for (int i = 0; i < bestSol.size(); i++) {
            total = +d.getWeight(this.solParcial.get(i));
        }
        return total;
    }

    private void BTrec(int ie) {
        if (getBestSolValue() < getSolValue()) {
            bestSol.clear();
            bestSol.addAll(solParcial);
        }
        if (ie == d.size()) {
            return;
        }
        if (d.getWeight(ie) + getPeso() < pesoMax) {
            solParcial.add(ie);
            BTrec(ie + 1);
            solParcial.remove(solParcial.indexOf(ie));
        }
        BTrec(ie + 1);
    }
}
