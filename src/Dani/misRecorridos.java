package poo;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        List<Integer>[] g;
        g = new List[9];

        g[0] = new ArrayList<>();
        g[1] = new ArrayList<>();
        g[2] = new ArrayList<>();
        g[3] = new ArrayList<>();
        g[4] = new ArrayList<>();
        g[5] = new ArrayList<>();
        g[6] = new ArrayList<>();
        g[7] = new ArrayList<>();
        g[8] = new ArrayList<>();

        //Nodo 1 diapositivas
        g[0].add(1);
        g[0].add(3);
        g[0].add(7);

        //Nodo 2 diapositivas
        g[1].add(0);
        g[1].add(2);
        g[1].add(3);

        //Nodo 3 diapositivas
        g[2].add(1);
        g[2].add(3);
        g[2].add(4);

        //Nodo 4 diapositivas
        g[3].add(0);
        g[3].add(1);
        g[3].add(2);
        g[3].add(6);

        //Nodo 5 diapositivas
        g[4].add(2);
        g[4].add(5);

        //Nodo 6 diapositivas
        g[5].add(4);
        g[5].add(6);

        //Nodo 7 diapositivas
        g[6].add(3);
        g[6].add(5);
        g[6].add(8);

        //Nodo 8 diapositivas
        g[7].add(0);
        g[7].add(8);

        //Nodo 9 diapositivas
        g[8].add(6);
        g[8].add(7);

        List<Integer>[] recorridosProfunidad = recorridosA(g);

        for(int i=0; i<recorridosProfunidad.length-1;i++) {
            for(int j=0; j<recorridosProfunidad[i].size()-1;j++){
                System.out.print(recorridosProfunidad[i].get(j)+",");
            }
            System.out.println();
        }
    }

    //PROFUNDIDAD
    public static List<Integer>[] recorridosP(List<Integer>[] g){
        int n = g.length;
        List<Integer>[] solucion = new List[n];
        for(int i=0; i<n-1; i++){
            solucion[i] = recorridoProfundidad(g, i);
        }
        return solucion;
    }

    public static List<Integer> recorridoProfundidad(List<Integer>[] g, int nodo){
        int n = g.length;
        List<Integer> recorrido = new ArrayList<>(n);
        boolean[] visitados = new boolean[n];
        recursivoProfundidad(g,nodo,recorrido,visitados);
        return recorrido;
    }

    public static void recursivoProfundidad(List<Integer>[] g, int nodo, List<Integer> recorrido, boolean[] visitados){
        recorrido.add(nodo);
        visitados[nodo] = true;

        for(int sigNodo: g[nodo]){
            if(!visitados[sigNodo]) {
                recursivoProfundidad(g, sigNodo , recorrido, visitados);
            }
        }
    }


    //ANCHURA
    public static List<Integer>[] recorridosA(List<Integer>[] g){
        int n = g.length;
        List<Integer>[] soluciones = new List[n];
        for(int i=0; i<n-1; i++){
            soluciones[i] = recorridoAnchura( g, i);
        }
        return soluciones;
    }

    public static List<Integer> recorridoAnchura(List<Integer>[] g, int nodo){
        int n=g.length;
        Queue<Integer> colaParaVisitar = new LinkedList<>();
        boolean[] visitados = new boolean[n];
        List<Integer> sol = new ArrayList<>();

        sol.add(nodo);
        visitados[nodo] = true;
        colaParaVisitar.addAll(g[nodo]);

        while(!colaParaVisitar.isEmpty()){
            int sigNodo = colaParaVisitar.remove();
            if(!visitados[sigNodo]) {
                sol.add(sigNodo);
            }
            visitados[sigNodo] = true;
            for(int adj: g[sigNodo]){
                if(!visitados[adj]){
                    colaParaVisitar.add(adj);
                }
            }
        }
        return sol;
    }
}
