package Alex.EjerciciosProblemasClase.ClasePracticaVoracesGrafos_BackTracking.Repetidos;

import java.util.*;

public class Carretera {
    private static int coste;
    private static int carreteras;
    private static int islas;
    public static class Arista implements Comparable{
        int origen;
        int destino;
        int coste;

        public Arista(int origen, int destino, int coste) {
            this.origen = origen;
            this.destino = destino;
            this.coste = coste;
        }

        @Override
        public int compareTo(Object o){
            Arista arista = (Arista) o;
            if(this.coste == arista.coste){
                if(this.origen == arista.origen && this.destino == arista.destino || this.origen == arista.destino && this.destino == arista.origen){
                    return 0;
                }else{
                    return -1;
                }
            }else {
                return Integer.compare(arista.coste,coste);
            }
        }
    }
    public static void main(String[] args){
        Scanner lector = new Scanner(System.in);

    }


    public static void Prim (List[] listadoCiudades){
        boolean[] visitado = new boolean[listadoCiudades.length];
        for(int i = 0; i < listadoCiudades.length; i++){
            if(!visitado[i]) {
                islas = islas + 1;
                visitado[i] = true;
                PriorityQueue<Arista> cola = new PriorityQueue();
                cola.addAll(listadoCiudades[i]);
                while (!cola.isEmpty()) {
                    Arista mejorSolucion = cola.poll();
                    if (!visitado[mejorSolucion.destino]) {
                        visitado[mejorSolucion.destino] = true;
                        coste += mejorSolucion.coste;
                        carreteras++;
                        cola.addAll(listadoCiudades[mejorSolucion.destino]);
                    }
                }
            }
        }
    }

}
