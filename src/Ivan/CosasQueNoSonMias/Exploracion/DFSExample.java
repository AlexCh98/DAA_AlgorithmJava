package Ivan.CosasQueNoSonMias.Exploracion;

public class DFSExample {
	
    // Driver method to
    public static void main(String args[])    {
           
        DirectedGraph g3 = new DirectedGraph(4);
        DFS dfs = new DFS(g3);

        
        g3.addEdge(0, 1);
        g3.addEdge(0, 2);
        g3.addEdge(1, 2);
        g3.addEdge(2, 0);
        g3.addEdge(2, 3);
        g3.addEdge(3, 3);
 
        System.out.println("");
        System.out.println("Following is Depth First Traversal "+
                           "(starting from vertex 2)");
 
        dfs.depthFirstSearch(2);
        
        //dfs.printDFStraversal();
    }
}


