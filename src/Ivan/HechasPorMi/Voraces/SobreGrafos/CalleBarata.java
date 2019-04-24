package Ivan.HechasPorMi.Voraces.SobreGrafos;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class CalleBarata {
    private static int islas;
    private static long carreteras;
    private static long coste;

    public static class Carretera implements Comparable {
        int origen;
        int destino;
        int coste;

        public Carretera(int origen, int destino, int coste) {
            this.origen = origen;
            this.destino = destino;
            this.coste = coste;
        }

        @Override
        public int compareTo(Object o) {
            Carretera otra = (Carretera) o;
            if (this.coste == otra.coste) {
                if ((this.destino == otra.destino && this.origen == otra.origen) || (this.origen == otra.destino && this.destino == otra.origen)) {
                    return 0;
                }
                return 1;
            } else {
                return Integer.compare(this.coste, otra.coste);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scaner = new Scanner(System.in);
        int numciudades = scaner.nextInt();
        //Normalmente hay 2 casos de prueba, pero pueden ser mas
        while (numciudades != 0) {
            int numcarreteras = scaner.nextInt();
            List[] listadecarreteras = new List[numciudades];//Lista que guarda una posicion para cada ciudad
            for (int i = 0; i < numciudades; i++) {
                listadecarreteras[i] = new ArrayList();//En la posicion de cada ciudad, ponemos un ArrayList
            }
            for (int i = 0; i < numcarreteras; i++) {
                String[] entrada = scaner.nextLine().split(" ");
                int origen = Integer.parseInt(entrada[0]);
                int destino = Integer.parseInt(entrada[1]);
                int coste = Integer.parseInt(entrada[2]);
                listadecarreteras[origen].add(new Carretera(origen, destino, coste));//Para la ciudad, es decir el nodo, guarda una carretera de ida
                listadecarreteras[destino].add(new Carretera(destino,origen,coste));//Guarda la de vuelta
            }
            islas=0;
            carreteras=0;
            coste=0;
            Prim(listadecarreteras);
        }
    }
    public static void Prim (List[] listadecarreteras){
        boolean[] visitados = new boolean[listadecarreteras.length];
        for (int i = 0; i <listadecarreteras.length ; i++) {
            if (!visitados[i]){
                islas++;
                visitados[i]=true;
                PriorityQueue<Carretera> solucion =new PriorityQueue<>();
                solucion.addAll(listadecarreteras[i]);
                while (!solucion.isEmpty()){
                    Carretera opcion = solucion.poll();
                    if (!visitados[opcion.destino]){
                        visitados[opcion.destino]=true;
                        coste = coste+opcion.coste;
                        carreteras=carreteras+1;
                        solucion.addAll(listadecarreteras[opcion.destino]);
                    }
                }
            }
        }
    }
}
