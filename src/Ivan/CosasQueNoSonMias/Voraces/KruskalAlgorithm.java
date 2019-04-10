package Ivan.CosasQueNoSonMias.Voraces;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class KruskalAlgorithm {
	private Graph graph;
	private int n;
    private int connectedComponents[];
    private int numOfConnectedComponents;

	public KruskalAlgorithm(Graph g) {
		this.n = g.getNumberOfVertices();
		this.graph = g;
		connectedComponents = new int[this.n];
				
		this.numOfConnectedComponents = n;
		for(int i = 0; i < n; i++){
			this.connectedComponents[i] = i;
		}
	}

	private boolean isFeasible(int o, int d){
		return (this.connectedComponents[o] != this.connectedComponents[d]);	
	}
	
	private void updateConnectedComponents(int oldComp, int newComp){
		for(int i = 0; i < n; i++){
			if(this.connectedComponents[i] == oldComp ){
				this.connectedComponents[i] = newComp;
			}			
		}
		this.numOfConnectedComponents--;
	}
	
	private boolean isSolution(){
		return (this.numOfConnectedComponents == 0);	
	}
	
	public Set<Edge> kruskalAlgorithm() {		
		HashSet<Edge> sol = new HashSet<>(n-1);
		List<Edge> candidates = this.graph.getEdges();
		Collections.sort(candidates, new EdgeComparator());	
		Iterator<Edge> it = candidates.iterator();
		
		while(it.hasNext() && !isSolution()){
			Edge edge = it.next(); //Si no se ordena hay que elegir la arista de menor peso
			int origin = edge.getSourcevertex();
			int dest = edge.getDestinationvertex();
			//No hace falta quitar el candidato porque se recorre la lista ordenada
			if(isFeasible(origin, dest)){
				sol.add(edge);
				updateConnectedComponents(connectedComponents[origin], connectedComponents[dest]);				
			}		
		}
		return sol;
	}
}