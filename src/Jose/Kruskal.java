package Jose;

import java.util.*;

public class Kruskal {

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


    public static class Grafo {
        SortedSet<Arista> aristas;
        List<Integer>[] listaAdyacencia;

    }


    private static List<Arista> Kruskal(Grafo grafo, int n_nodos, int n_aristas) {
        List<Arista> solucion = new ArrayList<>(n_nodos - 1);
        SortedSet<Arista> aristas = new TreeSet<>(grafo.aristas);
        ArrayList<Set<Integer>> componentes = new ArrayList<>(n_nodos);
        for (int i = 1; i <= n_nodos; i++) {
            Set<Integer> componente = new HashSet<>(n_nodos);
            componente.add(i);
            componentes.add(componente);
            grafo.listaAdyacencia[i] = new ArrayList<>(n_nodos + 1);
        }
        while (solucion.size() < n_nodos - 1 && !aristas.isEmpty()) {
            Arista arista = aristas.first();
            int nodo_1 = arista.arista[0];
            int nodo_2 = arista.arista[1];
            if (comprobar(nodo_1, nodo_2, componentes)) {

                solucion.add(arista);
            }
            aristas.remove(arista);
            //System.out.println(solucion.size());
        }
        return solucion;
    }

    /**
     * Este metodo comprueba si se puede añadir la arista [nodo_1, nodo_2] y
     * en caso afirmativo actualiza las componenres.
     */
    private static boolean comprobar(int nodo_1, int nodo_2, ArrayList<Set<Integer>> componentes) {
        for (Set<Integer> componente : componentes) {
            if (componente.contains(nodo_1) && componente.contains(nodo_2)) {
                return false;
            } else if (componente.contains(nodo_1)) {
                componente.add(nodo_2);
            } else if (componente.contains(nodo_2)) {
                componente.add(nodo_1);
            }
        }
        return true;
    }


    public static void main(String[] args) {
        int n_nodos = 8;
        int n_aristas = 15;
        Scanner reader = new Scanner(System.in);
        Grafo grafo = new Grafo();
        grafo.listaAdyacencia = new List[n_nodos + 1];
        grafo.aristas = new TreeSet<>();
        for (int i = 1; i <= n_nodos; i++) {
            grafo.listaAdyacencia[i] = new ArrayList<>(n_nodos + 1);
        }
        for (int i = 0; i < 22; i++) {
            String[] linea = reader.nextLine().split(" ");
            int origen = Integer.parseInt(linea[0]);
            int destino = Integer.parseInt(linea[1]);
            grafo.listaAdyacencia[origen].add(destino);
            int peso = Integer.parseInt(linea[2]);
            grafo.aristas.add(new Arista(new int[]{origen, destino}, peso));
        }

        StringBuilder sb = new StringBuilder();
        sb.append("DATOS; \n");
        sb.append("Lista de ayacencia: \n");
        for (int i = 1; i <= n_nodos; i++) {
            sb.append("[").append(i).append("] -> ").append(grafo.listaAdyacencia[i]).append("\n");
        }
        sb.append("Aristas; \n");
        for (Arista arista : grafo.aristas) {
            sb.append(arista);
        }
        sb.append("\nSOLUCION: \n");
        List<Arista> solucion = Kruskal(grafo, n_nodos, n_aristas);
        int pesoTotal = 0;
        for (Arista arista : solucion) {
            sb.append(arista);
            pesoTotal += arista.peso;
        }
        sb.append("Peso total: ").append(pesoTotal);
        System.out.print(sb);
    }
}
