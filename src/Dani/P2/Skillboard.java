package Dani.P2;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Skillboard {

    public static class Arista{
        public int inicio;
        public int destino;
        public int peso;

        public Arista(int inicio, int destino, int peso) {
            this.inicio = inicio;
            this.destino = destino;
            this.peso = peso;
        }

        public int compareTo(Object other){
            Arista aux = (Arista) other;

            if(peso==((Arista) other).peso){
                return -1;
            }else if(peso < ((Arista) other).peso){
                return -1;
            }else{
                return 1;
            }
        }

        public int getInicio() {
            return inicio;
        }

        public void setInicio(int inicio) {
            this.inicio = inicio;
        }

        public int getDestino() {
            return destino;
        }

        public void setDestino(int destino) {
            this.destino = destino;
        }

        public int getPeso() {
            return peso;
        }

        public void setPeso(int peso) {
            this.peso = peso;
        }
    }

    public static void main(String[] args) throws IOException {
        // write your code here
        //FileReader f = new FileReader("entrada.txt");
        Scanner scan = new Scanner(System.in);

        int numHabilidades = scan.nextInt();
        int numCaminos = scan.nextInt();
        List[] grafo = new List[numHabilidades];

        for(int i=0; i<numHabilidades; i++){
            grafo[i] = new ArrayList();
        }

        for(int i=0; i<numCaminos; i++){
            int inicio = scan.nextInt()-1; //Resto uno para que el primer nodo sea el 0
            int destino = scan.nextInt()-1;
            int peso = scan.nextInt();

            grafo[inicio].add(new Arista(inicio,destino,peso));
            grafo[destino].add(new Arista(destino,inicio,peso));
        }

        //Al ser conexo solo hace falta ejecutar una vez prim para sacar el árbol de minimmo recubrimiento
        prim(grafo);
    }

    public static void prim(List[] grafo){
        boolean[] visitados = new boolean[grafo.length];
        visitados[0] = true;

        PriorityQueue<Arista> aristas = new PriorityQueue<>(Comparator.comparing(arista-> arista.getPeso()));
        aristas.addAll(grafo[0]);

        int costeMinimo = 0;
        while(!aristas.isEmpty()){

            Arista mejor = aristas.poll(); //Al estar ordenadas, la mejor está garantizada

            if(esFactible(mejor,visitados)){
                costeMinimo += mejor.getPeso();
                visitados[mejor.getDestino()] = true;
                aristas.addAll(grafo[mejor.getDestino()]);
            }
        }
        System.out.print(costeMinimo);
    }

    public static boolean esFactible(Arista ar, boolean[] visitados){
        //Que no haga ciclo
        return !visitados[ar.getDestino()];
    }
}
