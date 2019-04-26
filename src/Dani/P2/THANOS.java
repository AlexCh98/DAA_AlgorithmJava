package Dani.P2;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;
public class THANOS {

    public static int caminos;

    public static void main(String[] args) throws IOException{

        //FileReader f = new FileReader("entrada.txt");
        Scanner scan = new Scanner(System.in);

        int numPlanetas = scan.nextInt();
        int numConexiones = scan.nextInt();

        List[] grafo= new List[numPlanetas];

        for (int i = 0; i <numPlanetas ; i++) {
            grafo[i] = new ArrayList();
        }

        //Rellenar grafo
        for (int i = 0; i <numConexiones ; i++) {
            int inicio = scan.nextInt();
            int destino = scan.nextInt();
            grafo[inicio].add(destino);
            grafo[destino].add(inicio);
        }

        int[] camino = new int[numPlanetas];
        Arrays.fill(camino,-1);

        camino[0] = 0;
        caminos = 0;

        hamiltoniano(1, grafo, camino);
        System.out.println(caminos);
    }

    public static void hamiltoniano(int pos, List[]grafo, int[] camino){

        List<Integer> adj = new ArrayList<>();
        //Añadimos los adjuntos del planeta anterior
        adj.addAll(grafo[camino[pos-1]]);

        while(!adj.isEmpty()){

            //Cogemos el siguiente planeta
            int sig = adj.remove(0);

            //Comprobamos que no está en el camino
            if(esFactible(sig,camino,pos)){

                //Si es solucion la imprimimos y sumamos uno, si no lo guardamos en el camino y pasamos al siguiente planeta
                if(pos == grafo.length-1 && cierraCiclo(grafo,sig)){
                    camino[pos] = sig;
                    //imprimirCamino(camino);
                    caminos+=1;
                }else if(pos<grafo.length-1){
                    camino[pos] = sig;
                    hamiltoniano(pos+1, grafo, camino); //Siguiente planeta en el recorrido
                }
            }
        }
        //Dejamos esta posicion "vacia"
        camino[pos] = -1;
    }

    public static boolean esFactible(int planeta, int[] camino, int lim){

        int i=0;
        boolean encontrado = false;

        while(!encontrado && i<=lim ){
            encontrado = camino[i] == planeta;
            i++;
        }

        return !encontrado;
    }

    public static boolean cierraCiclo(List[] grafo, int planeta){
        //Si se puede llegar al inicio
        return grafo[planeta].contains(0);
    }

    public static void imprimirCamino(int[] camino){
        for (int i = 0; i <camino.length ; i++) {
            System.out.print(camino[i]+", ");
        }
        System.out.println();
    }
}

