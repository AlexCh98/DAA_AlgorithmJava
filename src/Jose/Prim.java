package Jose;

import java.util.*;

public class Prim {
    public static class Arista implements Comparable{
        int [] arista;
        int peso;

        public Arista(int[] arista, int peso) {
            this.arista = arista;
            this.peso = peso;
        }

        @Override
        public String toString() {
            return "Arista: " + Arrays.toString(arista) + "\t Peso: " + peso+"\n";
        }

        @Override
        public int compareTo(Object o) {
            Arista otra = (Arista) o;
            if(this.peso == otra.peso){
                if(this.arista[0] == otra.arista[0]  && this.arista[1] ==  otra.arista[1]
                        || this.arista[1] == otra.arista[0]  && this.arista[0] ==  otra.arista[1]){
                    return 0;
                }else{
                    return -1;
                }
            }else{
                return Integer.compare(this.peso, otra.peso);
            }
        }
    }


    public static class Grafo{
        SortedSet<Arista> aristas;
        List<Integer>[] listaAdyacencia;
    }

    public static void main(String[] args){
        int n = 7;
        Grafo grafo = new Grafo();
        List<Integer>[] listaAdyacencia = new List[n+1];
        for(int i = 0; i <= n; i++){
            listaAdyacencia[i] = new ArrayList<>(n+1);
        }
        Set<Arista> aristas = new TreeSet<>();
        Scanner reader  = new Scanner(System.in);
        for(int i = 0; i < 22; i++){
            String [] linea = reader.nextLine().split(" ");
            int origen = Integer.parseInt(linea[0]);
            int destino = Integer.parseInt(linea[1]);
            int peso = Integer.parseInt(linea[2]);
            //System.out.println("linea = " + i+"\t origen = " + origen +"\t destino );
            listaAdyacencia[origen].add(destino);
            aristas.add(new Arista(new int[]{origen, destino},peso));
        }
        System.out.println(aristas);
    }
}
