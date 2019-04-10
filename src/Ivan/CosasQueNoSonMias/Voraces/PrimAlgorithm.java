package Ivan.CosasQueNoSonMias.Voraces;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.MAX_VALUE;

public class PrimAlgorithm {
    private Graph g;
    private int n;
    private HashSet<Integer> visitados;
    private List<Edge> aristas;

    public PrimAlgorithm(Graph g, int nodoInicial) {
        this.g = g;
        this.n = g.getNumberOfVertices();
        this.visitados = new HashSet<>();
        this.aristas = this.g.getEdges();
        this.visitados.add(nodoInicial);
    }

    public Set<Edge> greedyAlgorithm() {
        HashSet<Edge> sol = new HashSet<>();

        while (this.visitados.size() != n) {
            Edge best = getBest();
            sol.add(best);
            this.visitados.add(best.getDestinationvertex());
            this.visitados.add(best.getSourcevertex());

        }

        return sol;
    }

    public boolean esAristaValida(int i){
        Edge arista = this.aristas.get(i);

        return (this.visitados.contains(arista.getSourcevertex()) && !this.visitados.contains(arista.getDestinationvertex())) ||
                (!this.visitados.contains(arista.getSourcevertex()) && this.visitados.contains(arista.getDestinationvertex()));

    }

    private Edge getBest() {
        int best = MAX_VALUE;
        for (int i = 0; i < this.aristas.size(); i++) {
            Edge arista = this.aristas.get(i);
            if (arista.getWeight() < best && esAristaValida(i)){
                best = i;
            }
        }
        return this.aristas.get(best);
    }

}
