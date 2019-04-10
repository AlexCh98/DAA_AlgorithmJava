package Ivan.CosasQueNoSonMias.Voraces;

import java.util.Set;

public class Main {

    public static void main(String[] args) {

        /*Data k = new Data(10);
        GreedyKnapsack gk = new GreedyKnapsack(k);
        gk.greedyAlgorithmKS();
        System.out.println("Optimal solution to knapsack instance with values given as follows");
        gk.print();
        System.out.println("=======+============+=======+============+============");*/

        ///////////////////////////////////////////////////////////////////////////////////////////////

        /*Tasks tasks = new Tasks(10);
        GreedyWaitTime gTask = new GreedyWaitTime(tasks);
        gTask.greedyAlgorithmWT();
        System.out.println("Optimal soluation to waiting time instance");
        gTask.print();*/

        ///////////////////////////////////////////////////////////////////////////////////////////////

        Edge edge0 = new Edge(0, 2, 1);
        Edge edge1 = new Edge(3, 4, 1);
        Edge edge2 = new Edge(0, 3, 2);
        Edge edge3 = new Edge(4, 1, 2);
        Edge edge4 = new Edge(2, 3, 3);
        Edge edge5 = new Edge(5, 1, 4);
        Edge edge6 = new Edge(2, 6, 5);
        Edge edge7 = new Edge(0, 6, 6);
        Edge edge8 = new Edge(6, 1, 7);
        Edge edge9 = new Edge(6, 1, 8);
        Edge edge10 = new Edge(3, 5, 9);

        Graph g = new Graph(7);
        g.add(edge0); g.add(edge1); g.add(edge2); g.add(edge3); g.add(edge4);
        g.add(edge5); g.add(edge6); g.add(edge7); g.add(edge8); g.add(edge9);
        g.add(edge10);

        /*KruskalAlgorithmExamen kruskal = new KruskalAlgorithmExamen(g);
        Set<Edge> solucion = kruskal.kruskalAlgorithm();*/

        PrimAlgorithm prim = new PrimAlgorithm(g, 0);
        Set<Edge> solucion = prim.greedyAlgorithm();


        /*int n = 10;
        int m = 25;

        Graph g = new Graph(n, m);
        KruskalAlgorithm kruskalAlgorithm = new KruskalAlgorithm(g);
        Set<Edge> minSpanningTree = kruskalAlgorithm.kruskalAlgorithm();

        System.out.println("The spanning tree is ");
        for(Edge e: minSpanningTree) {
            int o = e.getSourcevertex();
            int d = e.getDestinationvertex();
            double w = e.getWeight();
            System.out.println("Origin " + o + " Destination " + d + " Weight " + w);
        }*/



    }
}
