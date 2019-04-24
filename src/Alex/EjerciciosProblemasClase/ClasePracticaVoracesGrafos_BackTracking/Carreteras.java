package Alex.EjerciciosProblemasClase.ClasePracticaVoracesGrafos_BackTracking;



import java.util.*;

public class Carreteras {
    public static class Carretera implements Comparable{
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
                if (this.origen == otra.origen && this.destino == otra.destino
                        || this.destino == otra.origen && this.origen == otra.destino) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                return Integer.compare(otra.coste, this.coste);
            }
        }
    }
    public static void main(String [] args){
        Scanner lector = new Scanner(System.in);
        int numeroCiudades = Integer.parseInt(lector.nextLine());
        while(numeroCiudades != 0){
            int numeroCarreteras = Integer.parseInt(lector.nextLine());
            //ArrayList<Carretera> listadoCarreteras = new ArrayList<>();
            int[][] listadoCarreteras = new int[numeroCiudades][numeroCiudades];
            for (int j = 0; j < numeroCarreteras;j++){
                String[] linea = lector.nextLine().split(" ");
                int origen = Integer.parseInt(linea[0]);
                int destino = Integer.parseInt(linea[1]);
                int peso = Integer.parseInt(linea[2]);
                listadoCarreteras[origen][destino] = Math.max(listadoCarreteras[origen][destino],peso);
                listadoCarreteras[destino][origen] = Math.max(listadoCarreteras[destino][origen],peso);
            }

            Prim(listadoCarreteras);
            numeroCiudades = Integer.parseInt(lector.nextLine());

        }
    }

    private static List<Carretera> Prim(int[][] matrizAdaycencia) {
        List<Carretera> solucion = new ArrayList<>();
        int n = matrizAdaycencia.length;

        int verticeOrigen = (int) (Math.random() * (n - 1)) + 1;
        System.out.println("NODO ORIGEN:"+verticeOrigen);
        HashSet<Integer> vertices = new HashSet<>();
        PriorityQueue<Carretera> cola = new PriorityQueue<>();

        vertices.add(verticeOrigen);
        while (vertices.size() < n - 1) {
            rellenarCola(cola, matrizAdaycencia, verticeOrigen, vertices);
            Carretera mejorArista = cola.poll();
            assert mejorArista != null;
            if (!vertices.contains(mejorArista.destino)) {
                solucion.add(mejorArista);
                verticeOrigen = mejorArista.destino;
                vertices.add(verticeOrigen);
            }
        }
        return solucion;
    }

    private static void rellenarCola(PriorityQueue<Carretera> cola, int[][] matrizAdyacencia, int verticeOrigen, HashSet<Integer> vertices) {
        for (int i = 1; i < matrizAdyacencia[verticeOrigen].length; i++) {
            if (matrizAdyacencia[verticeOrigen][i] > 0 && !vertices.contains(i)) {
                cola.offer(new Carretera(verticeOrigen, i, matrizAdyacencia[verticeOrigen][i]));
            }
        }
    }

}
