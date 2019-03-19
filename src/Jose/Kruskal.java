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


    public static void main(String[] args) {
        int n_nodos = 7;
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
        sb.append(kruskal(grafo));
        //kruskal(grafo);
        System.out.print(sb);
    }

    public static List<Arista> kruskal(Grafo grafo) {
        int n = grafo.listaAdyacencia.length - 1;//numero de nodos
        List<Arista> solucion = new ArrayList<>(n - 1);//Una arista menos que el numero de nodos
        SortedSet<Arista> candidatos = new TreeSet<>(grafo.aristas);
        ArrayList<Set<Integer>> componentes = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            Set<Integer> componente = new HashSet<>(n);
            componente.add(i);
            componentes.add(componente);
        }
        System.out.println(componentes);
        while (solucion.size() != n - 1 && !candidatos.isEmpty()) {
            Arista mejorArista = candidatos.first();
            candidatos.remove(mejorArista);
            //System.out.println("Mejor Arista = " + mejorArista);
            //System.out.println("Componentes = " + componentes);
            if (isFactible(mejorArista, componentes)) {
                solucion.add(mejorArista);
                //System.out.println("Arista aceptada");
            }

        }
        if (candidatos.isEmpty() && solucion.size() != n - 1) {
            System.out.println("Imposible resolver");
            return null;
        }
        return solucion;
    }

    /**
     * Este metodo comprueba si se puede añadir la arista mejor arista y
     * en caso afirmativo actualiza las componenres.
     */
    private static boolean isFactible(Arista mejorArista, ArrayList<Set<Integer>> componentes) {
        int nodo_1 = mejorArista.arista[0];
        int nodo_2 = mejorArista.arista[1];
        for (Set<Integer> componente : componentes) {
            if (componente.contains(nodo_1) && componente.contains(nodo_2)) {
                return false;
            } else if (componente.contains(nodo_1) && !componente.contains(nodo_2)) {
                componente.add(nodo_2);
            } else if (!componente.contains(nodo_1) && componente.contains(nodo_2)) {
                componente.add(nodo_1);
            }
        }
        return true;
    }
}

