package Ivan.CosasQueNoSonMias.Exploracion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DFS {

	private DirectedGraph g;
	private boolean visited[];
	private List<Integer> vertices;

	public DFS(DirectedGraph g) {
		this.g = g;
		// Mark all the vertices as not visited (set as false by default in java)
		this.visited = new boolean[this.g.getNumVertices()];
		this.vertices = new ArrayList<>(this.g.getNumVertices());
	}

	// A function used by DFS
	private void recursiveDFS(int v) {
		// Mark the current node as visited and print it
		visited[v] = true;
		System.out.print(v + " ");
		this.vertices.add(v);

		// Recur for all the vertices adjacent to this vertex

		/*Iterator<Integer> i = g.getAdjacents(v).iterator();
		while (i.hasNext()) {
			int n = i.next();
			if (!visited[n])
				recursiveDFS(n);*/

		for (int n : g.getAdjacents(v)) {
			if (!visited[n])
				recursiveDFS(n);
		}
	}

	// The function to do DFS traversal. It uses recursive DFSUtil()
	public void depthFirstSearch(int v) {
		// Call the recursive helper function to print DFS traversal
		if (!visited[v]) {
			recursiveDFS(v);
		}
	}

	// The function to do DFS traversal. It uses recursive DFSUtil()
	public void depthFirstSearch() {
		int n = this.g.getNumVertices();

		// Call the recursive helper function to print DFS traversal
		// starting from all vertices one by one
		for (int i = 0; i < n; ++i) {
			if (!visited[i]) {
				recursiveDFS(i);
			}
		}
	}

	public void printDFStraversal() {
		// Print contents of list
		System.out.println("");
		for(int i = 0; i < this.vertices.size(); i++){
			System.out.print(this.vertices.get(i) + " ");
		}
	}

	public List<Integer> getDFStraversal() {
		return this.vertices;
	}
}
