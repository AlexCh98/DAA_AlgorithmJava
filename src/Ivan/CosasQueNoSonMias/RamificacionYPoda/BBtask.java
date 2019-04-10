package Ivan.CosasQueNoSonMias.RamificacionYPoda;

import java.util.PriorityQueue;

public class BBtask {
    private Data data;
    private Solution bestSolution;
    private PriorityQueue<Solution> pq;

    private int nodosGenerados;

    public BBtask(Data data) {
        this.data = data;
        pq = new PriorityQueue<>();
        this.nodosGenerados = 0;
    }

    public Solution getSolution() {
        this.bestSolution = initSol();
        pq.add(new Solution(data));
        while (!pq.isEmpty()) {
            Solution current = pq.poll();
            if (current.isSolution()) {
                if (current.getCost() < bestSolution.getCost()) {
                    bestSolution = current;
                }
            } else {//Generar hijos
                if (current.getLowerBound() < bestSolution.getCost())
                    for (Solution child : current.getChildren()) {//Solo devuelve hijos factibles
                        if (child.getLowerBound() < bestSolution.getCost()) {
                            pq.add(child);
                            this.nodosGenerados++;
                        }
                    }
            }
        }
        System.out.println("Nodos generados: " + nodosGenerados);
        return bestSolution;
    }

    private Solution initSol() {
        Solution solution = new Solution(data);
        for (int i = 0; i < data.size(); i++)
            solution.add(i);
        return solution;
    }

}
