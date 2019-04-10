package Ivan.CosasQueNoSonMias.BackTracking;

/**
 * Solucion por backtracking al problema de la Mochila.
 * - Podemos meter un tipo de elemento varias veces (0-k).
 * - Comprueba todas las soluciones y devuelve la optima.
 */
public class BTknapsackN {
    private Data d;
    private Solution bestSol;
    private Solution sol;

    public BTknapsackN(Data d) {
        this.d = d;
        bestSol = new Solution(this.d);
        sol = new Solution(this.d);
    }

    public Solution knapsack() {
        knapsackRec(0);
        return bestSol;
    }

    private void knapsackRec(int startIndex) {
        if (bestSol.getValue() < sol.getValue() && sol.isSolution()) {
            bestSol.copySol(sol);//Si la solucion es factible y mejor se copia
        }

        for (int ei = startIndex; ei < d.size(); ei++) {
            if (sol.isCandidate(ei)) {//EsPrometedor
                sol.addElement(ei);
                knapsackRec(ei); //No hacemos rec(i+) porque podemos repetir objetos
                sol.removeElement(ei);
            }
        }

    }
}