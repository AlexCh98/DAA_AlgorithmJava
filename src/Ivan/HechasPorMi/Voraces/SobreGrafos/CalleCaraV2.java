package Ivan.HechasPorMi.Voraces.SobreGrafos;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class CalleCaraV2 {

    private static int islas;
    public static int carreteras;
    public static int coste;

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
            Carretera aux = (Carretera) o;
            if (this.coste == aux.coste) {
                if ((this.origen == aux.origen && this.destino == aux.destino) || (this.origen == aux.destino && this.destino == aux.origen)) {
                    return 0;
                } else {
                    return 1;
                }
            } return Integer.compare(aux.origen,this.coste);
        }
    }

    public static void main(String[] args) {
        Scanner escaner = new Scanner(System.in);
        int numeroCiudades = Integer.parseInt(escaner.nextLine());
        while (numeroCiudades != 0) {
            int numCarreteras = Integer.parseInt(escaner.nextLine());
            List[] listaCarreteras = new List[numeroCiudades];
            for (int i = 0; i < numeroCiudades; i++) {
                listaCarreteras[i] = new ArrayList();
            }
            for (int j = 0; j < numCarreteras; j++) {
                String[] linea = escaner.nextLine().split(" ");
                int origen = Integer.parseInt(linea[0]);
                int destino = Integer.parseInt(linea[1]);
                int peso = Integer.parseInt(linea[2]);
                listaCarreteras[origen].add(new CalleCara.Carretera(origen, destino, peso));
                listaCarreteras[destino].add(new CalleCara.Carretera(destino, origen, peso));
            }
            AlgoritmoPrim(listaCarreteras);
            System.out.println(islas+" "+carreteras+" "+coste);
            System.out.println("---");
            coste = 0;
            islas = 0;
            carreteras = 0;
            numeroCiudades = Integer.parseInt(escaner.nextLine());
        }
    }
    public static void AlgoritmoPrim (List[] listadecarreteras){
        boolean[] nodos_visitados = new boolean[listadecarreteras.length];
        for (int i = 0; i < listadecarreteras.length; i++) {
            if (!nodos_visitados[i]){
                nodos_visitados[i]=true;
                islas++;
                PriorityQueue<Carretera> solucion = new PriorityQueue<>();
                solucion.addAll(listadecarreteras[i]);
                while (!solucion.isEmpty()){
                    Carretera auxiliar = solucion.poll();
                    if (!nodos_visitados[auxiliar.destino]){
                        nodos_visitados[auxiliar.destino]=true;
                        coste= coste+auxiliar.coste;
                        carreteras=carreteras+1;
                        solucion.addAll(listadecarreteras[auxiliar.destino]);
                    }
                }
            }
        }
    }
}

