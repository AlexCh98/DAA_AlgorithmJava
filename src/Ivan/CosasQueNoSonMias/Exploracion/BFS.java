package Ivan.CosasQueNoSonMias.Exploracion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BFS {
	private DirectedGraph g;
	private boolean visited[];
	private List<Integer> vertices;

	public BFS(DirectedGraph g) {
		this.g = g;
		// Mark all the vertices as not visited (set as false by default in java)
		this.visited = new boolean[this.g.getNumVertices()];
		this.vertices = new ArrayList<>();
	}

	// prints BFS traversal from a given source s
	public void breathFirstSearch(int s) {

		// Create a queue for BFS
		Queue<Integer> q = new LinkedList<Integer>();

		// Mark the current node as visited and enqueue it
		visited[s] = true;
		q.add(s);

		while (q.size() != 0) {
			// Dequeue a vertex from queue and print it
			s = q.poll();
			System.out.print(s + " ");
			this.vertices.add(s);

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it

			/*Iterator<Integer> i = g.getAdjacents(s).iterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					visited[n] = true;
					q.add(n);
				}
			}*/

			for (int n : g.getAdjacents(s)) {
				if (!visited[n]) {
					visited[n] = true;
					q.add(n);
				}
			}
		}
	}

	public void printBFStraversal() {
		// Print contents of list
		System.out.println("");
		for(int i = 0; i < this.vertices.size(); i++){
			System.out.print(this.vertices.get(i) + " ");
		}
	}

	public List<Integer> getBFStraversal() {
		return this.vertices;
	}
}
