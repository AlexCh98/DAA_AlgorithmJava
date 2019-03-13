package poo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Djikstra {

    public static void main(String[] args) {
	// write your code here
        int[][] grafo = new int[5][5];


        //Grafo dirigido de diapositivas

        //Nodo 1
        grafo[0][1] = 5;
        grafo[0][3] = 3;
        //Nodo 2
        grafo[1][4] = 1;
        //Nodo 4
        grafo[3][1] = 1;
        grafo[3][2] = 11;
        grafo[3][4] = 6;
        //Nodo 5
        grafo[4][2] = 1;

        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(grafo[i][j] == 0){
                    grafo[i][j] = 1000000000; //Significa infinito
                }
            }
        }

        int[] vectorDistancias = Djikstra(0, grafo);
        //Mostrar solución
        System.out.print("[");
        for(int i=0; i<grafo.length ; i++){
            System.out.print(vectorDistancias[i] + ",");
        }
        System.out.println("]");
    }


    public static int[] Djikstra(int nodo, int[][] grafo){

        int[] solucion = new int[grafo.length];

        Set<Integer> nodosNoSeleccionados = new HashSet<>();

        //Inicializar solucion y nodos
        for(int i=0; i<grafo.length; i++){
            solucion[i] = grafo[nodo][i];
            nodosNoSeleccionados.add(i);
        }
        solucion[nodo] = 0; //La distancia a sí mismo es 0
        nodosNoSeleccionados.remove(nodo); //Quitar el nodo desde el que se empieza

        //Bucle voraz
        while(!nodosNoSeleccionados.isEmpty()){

            //Muestro estado del vector de distancias
            System.out.print("[");
            for(int i=0; i<grafo.length ; i++){
                System.out.print(solucion[i] + ",");
            }
            System.out.println("]");

            //Coger el mejor nodo
            int mejorNodo = getMejorNodo(nodosNoSeleccionados,solucion);
            System.out.println("Cojo " + mejorNodo);

            //Lo quito del conjunto de candidatos
            nodosNoSeleccionados.remove(mejorNodo);

            //Actualizo el vector de distancias
            actualizarDistancias(mejorNodo,solucion,grafo,nodosNoSeleccionados);


        }



        return solucion;
    }

    public static int getMejorNodo(Set<Integer> candidatos, int[] vectorDistancias){
        int mejor = 0;
        int menorDistancia = Integer.MAX_VALUE;

        for(int cand: candidatos){
            if(vectorDistancias[cand] < menorDistancia){
                menorDistancia = vectorDistancias[cand];
                mejor = cand;
            }
        }

        return mejor;
    }

    public static void actualizarDistancias(int mejorNodo, int[] solucion, int[][] grafo, Set<Integer> nodosNoSeleccionados){

        int distanciaANodo = solucion[mejorNodo]; //Guardo la distancia al mejorNodo para sumarlo a la distancia de este a sus adyacentes

        for(int n: nodosNoSeleccionados){
            if(solucion[n] > distanciaANodo+grafo[mejorNodo][n]){
                solucion[n] = distanciaANodo+grafo[mejorNodo][n];
            }
        }
    }
}
