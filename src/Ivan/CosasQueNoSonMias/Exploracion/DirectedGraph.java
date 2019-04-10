package Ivan.CosasQueNoSonMias.Exploracion;

import java.util.HashSet;
import java.util.Iterator;

public class DirectedGraph {

	private int V; // No. of vertices
	private HashSet<Integer>[] adj; // Adjacency Lists

	// Constructor
	public DirectedGraph(int v) {
		V = v;
		adj = new HashSet[v];
		for (int i = 0; i < v; ++i)
			adj[i] = new HashSet<Integer>();
	}

	// Function that returns reverse (or transpose) of this graph
	public DirectedGraph getTranspose() {
		DirectedGraph newGraph = new DirectedGraph(V);
		for (int v = 0; v < V; v++) {
			// Recur for all the vertices adjacent to this vertex
			Iterator<Integer> i = this.getAdjacents(v).iterator();
			while (i.hasNext())
				newGraph.adj[i.next()].add(v);

		}
		return newGraph;
	}

	// Function to add an edge into the graph
	public void addEdge(int v, int w) {
		adj[v].add(w);
	}

	public Iterable<Integer> getAdjacents(int v) {
		return this.adj[v];
	}

	public int getNumVertices() {
		return this.V;
	}

}
