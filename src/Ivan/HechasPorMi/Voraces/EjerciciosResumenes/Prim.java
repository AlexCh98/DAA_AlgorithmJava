package Ivan.HechasPorMi.Voraces.EjerciciosResumenes;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Prim {

    public static class Arista implements Comparable  {
        int origen;
        int destino;
        int coste;

        public Arista(int origen, int destino, int coste) {
            this.origen = origen;
            this.destino = destino;
            this.coste = coste;
        }
        @Override
        public int compareTo(Object o) {
            Arista otra = (Arista) o;
            if (this.coste == otra.coste) {
                if (this.origen == otra.origen && this.destino == otra.destino || this.destino == otra.origen && this.origen == otra.destino) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return Integer.compare(otra.coste, this.coste);
            }

        }

        public int getOrigen() {
            return origen;
        }

        public void setOrigen(int origen) {
            this.origen = origen;
        }

        public int getDestino() {
            return destino;
        }

        public void setDestino(int destino) {
            this.destino = destino;
        }

        public int getCoste() {
            return coste;
        }

        public void setCoste(int coste) {
            this.coste = coste;
        }
    }

    public static void main(String[] args) {
        int numero_nodos = 10;
        List[] nodos = new List[numero_nodos];
        for (int i = 0; i <numero_nodos ; i++) {
            nodos[i]=new ArrayList();
        }
        nodos[0].add(new Arista(0,1,1));
        nodos[0].add(new Arista(0,3,2));
        nodos[1].add(new Arista(1,4,3));
        nodos[1].add(new Arista(1,0,1));
        nodos[2].add(new Arista(2,4,4));
        nodos[3].add(new Arista(3,0,2));
        nodos[4].add(new Arista(4,2,4));
        nodos[4].add(new Arista(4,1,3));


     algoritmo_Prim(nodos);
        for (int i = 0; i < nodos.length; i++) {
            System.out.println(nodos[i]);
        }
    }

    private static void algoritmo_Prim(List[] nodos) {
        boolean [] visitados = new boolean[nodos.length];
        for (int i = 0; i < nodos.length; i++) {
            if (!visitados[i]){
                visitados[i]=true;
                PriorityQueue<Arista> cola =new PriorityQueue<>();
                cola.addAll(nodos[i]);
                while (!cola.isEmpty()){
                    Arista aux = (Arista) cola.poll();
                    if(!visitados[aux.destino]){
                        visitados[aux.destino]=true;
                        cola.addAll(nodos[aux.destino]);
                    }
                }
            }
        }
    }

}