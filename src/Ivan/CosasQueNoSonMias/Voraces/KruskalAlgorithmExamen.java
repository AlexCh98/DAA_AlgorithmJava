package Ivan.CosasQueNoSonMias.Voraces;

import java.util.*;

public class KruskalAlgorithmExamen {
    private Graph g;
    private Set<Edge> sol;
    private int[] conexas;
    private int numeroConexas;

    //14 minutos 13 segundos

    public KruskalAlgorithmExamen(Graph g) {
        this.g = g;
        this.numeroConexas = g.getNumberOfVertices();
        this.sol = new HashSet<>(this.numeroConexas - 1);
        this.conexas = new int[numeroConexas];

        for (int i = 0; i < conexas.length; i++) {
            this.conexas[i] = i;
        }
    }

    public Set<Edge> kruskalAlgorithm() {
        List<Edge> edges = g.getEdges();
        Collections.sort(edges, new EdgeComparator());

        Iterator<Edge> it = edges.iterator();

        while (/*this.numeroConexas != 0 &&*/ it.hasNext()) { //Creo que numConexas deberia ser 1
            Edge actual = it.next();
            if (esFactible(actual.getSourcevertex(), actual.getDestinationvertex())) {
                conecta(this.conexas[actual.getSourcevertex()], this.conexas[actual.getDestinationvertex()]);
                this.sol.add(actual);
            }
        }
        return this.sol;
    }

    public boolean esFactible(int origen, int destino) {
        return this.conexas[origen] != this.conexas[destino];
    }

    public void conecta(int origen, int destino) {
        for (int i = 0; i < this.conexas.length; i++) {
            if (this.conexas[i] == origen) {
                this.conexas[i] = destino;
            }
        }
        this.numeroConexas--;
    }
}
