package Alex.AlgoritmosVoraces.VoracesGrafos;


import java.util.*;

public class PrimFacil {


    public static class Arista implements Comparable {
        int[] arista;
        int peso;

        public Arista(int[] arista, int peso) {
            this.arista = arista;
            this.peso = peso;
        }

        @Override
        public String toString() {
            return "Arista: " + Arrays.toString(arista) + "\t Peso: " + peso + "\n";
        }

        @Override
        public int compareTo(Object o) {
            Arista otra = (Arista) o;
            if (this.peso == otra.peso) {
                if (this.arista[0] == otra.arista[0] && this.arista[1] == otra.arista[1]
                        || this.arista[1] == otra.arista[0] && this.arista[0] == otra.arista[1]) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                return Integer.compare(this.peso, otra.peso);
            }
        }
    }


    public static void main(String[] args) {
        int n = 7;
        int[][] matrizAdyacencia = new int[n + 1][n + 1];
        Scanner reader = new Scanner(System.in);
        for (int i = 0; i < 22; i++) {
            String[] linea = reader.nextLine().split(" ");
            int origen = Integer.parseInt(linea[0]);
            int destino = Integer.parseInt(linea[1]);
            int peso = Integer.parseInt(linea[2]);
            matrizAdyacencia[origen][destino] = peso;
        }
        for (int i = 1; i < matrizAdyacencia.length; i++) {
            System.out.println(Arrays.toString(Arrays.copyOfRange(matrizAdyacencia[i], 1, matrizAdyacencia.length)));
        }
        System.out.println("RESULTADO:");
        System.out.println(prim(matrizAdyacencia));
    }

    private static List<Arista> prim(int[][] matrizAdaycencia) {
        List<Arista> solucion = new ArrayList<>();
        int n = matrizAdaycencia.length;

        int verticeOrigen = (int) (Math.random() * (n - 1)) + 1;
        System.out.println("NODO ORIGEN:"+verticeOrigen);
        HashSet<Integer> vertices = new HashSet<>();
        PriorityQueue<Arista> cola = new PriorityQueue<>();

        vertices.add(verticeOrigen);
        while (vertices.size() < n - 1) {
            rellenarCola(cola, matrizAdaycencia, verticeOrigen, vertices);
            Arista mejorArista = cola.poll();
            assert mejorArista != null;
            if (!vertices.contains(mejorArista.arista[1])) {
                solucion.add(mejorArista);
                verticeOrigen = mejorArista.arista[1];
                vertices.add(verticeOrigen);
            }
        }
        return solucion;
    }

    private static void rellenarCola(PriorityQueue<Arista> cola, int[][] matrizAdyacencia, int verticeOrigen, HashSet<Integer> vertices) {
        for (int i = 1; i < matrizAdyacencia[verticeOrigen].length; i++) {
            if (matrizAdyacencia[verticeOrigen][i] > 0 && !vertices.contains(i)) {
                cola.offer(new Arista(new int[]{verticeOrigen, i}, matrizAdyacencia[verticeOrigen][i]));
            }
        }
    }
}


