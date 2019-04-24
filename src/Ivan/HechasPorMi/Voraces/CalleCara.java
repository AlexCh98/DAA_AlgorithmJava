package Ivan.HechasPorMi.Voraces;

import java.util.*;

public class CalleCara {
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

            public int compareTo(Object o) {
                Carretera otra = (Carretera) o;
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
        }


    public static void main(String [] args) {
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
                listaCarreteras[origen].add(new Carretera(origen, destino, peso));
                listaCarreteras[destino].add(new Carretera(destino, origen, peso));
            }
            Prim(listaCarreteras);
            System.out.println(islas+" "+carreteras+" "+coste);
            System.out.println("---");
            coste = 0;
            islas = 0;
            carreteras = 0;
            numeroCiudades = Integer.parseInt(escaner.nextLine());
        }
    }
    private static void Prim (List[] listaCarreteras){
        boolean[] visitado = new boolean[listaCarreteras.length];
        for (int i = 0; i < listaCarreteras.length ; i++) {
            if (!visitado[i]){
                islas ++;
                PriorityQueue<Carretera> cola = new PriorityQueue<>();
                cola.addAll((listaCarreteras[i]));
                visitado[i]= true;
                while (!cola.isEmpty()){
                    Carretera carreter = cola.poll();
                    if(!visitado[carreter.destino]){
                        coste += carreter.coste;
                        carreteras +=1;
                        cola.addAll(listaCarreteras[carreter.destino]);
                        visitado [carreter.destino]=true;
                    }
                }
            }
        }
    }
}