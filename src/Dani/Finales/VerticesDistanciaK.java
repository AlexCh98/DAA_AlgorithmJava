package Dani.Finales;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class VerticesDistanciaK {

    public static void main(String[] args){

        List[] grafo = new List[8];
        for(int i=0; i<grafo.length; i++){
           grafo[i] = new ArrayList<>();
        }

        //A
        grafo[0].add(1);
        grafo[0].add(2);
        grafo[0].add(4);
        grafo[0].add(5);

        //B
        grafo[1].add(0);
        grafo[1].add(2);
        grafo[1].add(3);

        grafo[2].add(0);
        grafo[2].add(1);
        grafo[2].add(3);

        grafo[3].add(1);

        grafo[4].add(0);
        grafo[4].add(6);
        grafo[4].add(7);

        grafo[5].add(0);

        grafo[6].add(4);

        grafo[7].add(4);





        int nodoInicial = 1;
        int k=3;
        List<Integer> adyacentes = grafo[nodoInicial];
        boolean[] visitados = new boolean[grafo.length];
        visitados[nodoInicial] = true;
        List<Integer> solucion = nodosADistanciaK(k, grafo, visitados, 1, adyacentes);

        System.out.print("Nodos a distancia " + 3 +": ");
        for(int n: solucion){
            System.out.print(n +" ");
        }
    }

    //Como las aristas no tienen peso se puede hacer por BFS si no, habría que usar Dijkstra

    public static List<Integer> nodosADistanciaK(int k, List[] grafo, boolean[] visitados, int distanciaActual, List<Integer> nodosADistanciaActual){

        System.out.println("Profundidad: "+distanciaActual);
        System.out.println("k: "+ k);

        if(!seHaRecorrido(visitados)) {
            List<Integer> sol = new ArrayList<>();
            if (distanciaActual == k) { //Hay que cambiar este if para los distintos apartados
                for (int e : nodosADistanciaActual) {
                    if (!visitados[e]) {
                        sol.add(e);
                    }
                }
            }else{

                List<Integer> sigAdyacentes = new ArrayList<>();
                for(int n: nodosADistanciaActual){
                    if(!visitados[n]){
                        visitados[n] = true;
                        sigAdyacentes.addAll(grafo[n]);
                    }
                }
                sol.addAll(nodosADistanciaK(k, grafo, visitados, distanciaActual+1, sigAdyacentes));

            }

            return sol;

        }
        return null;
    }

    public static boolean seHaRecorrido(boolean[] visitados){
        int i=0;
        while(i<visitados.length){
            if(!visitados[i]){
                return false;
            }

            i++;
        }
        return true;
    }
}
