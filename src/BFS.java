package com.company;

import java.util.*;

public class Main {

	public static List<Integer> breadthFirstSearch(List<Integer>[] g, int v) {
		int n = g.length;
		List<Integer> traversal = new ArrayList<>(n);
		boolean[] visited = new boolean[n];
		Queue<Integer> q = new LinkedList<>();
		visited[v] = true;
		traversal.add(v);
		q.add(v);
		while (!q.isEmpty()) {
			int aux = q.remove();
			for (int adj : g[aux]) {
				if (!visited[adj]) {
					visited[adj] = true;
					traversal.add(adj);
					q.add(adj);
				}
			}
		}
		return traversal;
	}
	public static List<Integer>[] breadthFirstSearch(List<Integer>[] g) {
		int n = g.length;
		List<Integer> traversals[] = new List[n];
		for (int v = 0; v < n; v++) {
			traversals[v] = breadthFirstSearch(g, v);
		}
		return traversals;
	}

    public static void main(String[] args) {
	int nodos, aristas, nodoinicial;
	Scanner reader = new Scanner(System.in);
	nodos= reader.nextInt();
	aristas = reader.nextInt();
	nodoinicial= reader.nextInt();
	List<Integer>[] g = new List[nodos+1];
    for (int i=1;i<=nodos;i++){
        g[i]=new ArrayList(nodos);
        }
    for (int j=1;j<=aristas;j++){
    	int nodo1=reader.nextInt();
		int nodo2=reader.nextInt();
		g[nodo1].add(nodo2);
		g[nodo2].add(nodo1);
	}
		List<Integer> resultado = breadthFirstSearch(g,nodoinicial);
		for (int nodo: resultado) {
			if(resultado.indexOf(nodo) != resultado.size()-1){
				System.out.print(nodo + " ");
			}else{
				System.out.print(nodo);
			}
		}
}
}
