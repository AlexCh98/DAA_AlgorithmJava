package Dani;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CalleCara {

    public static long coste;

    public static class Arista implements Comparable{
        public int inicio;
        public int fin;
        public long peso;

        public Arista(int inicio, int fin, long peso){
            this.inicio = inicio;
            this.fin = fin;
            this.peso = peso;
        }

        @Override
        public int compareTo(Object other) {
            Arista aux = (Arista) other;
            if(aux.peso == this.peso){
                if(aux.inicio == this.fin){
                    if(fin > aux.fin){
                        return 1;
                    }else{
                        return -1;
                    }
                }else{
                    return 1;
                }
                //Ordenados de mayor a menor
            }if(this.peso > aux.peso){
                return -1;
            }else{
                return 1;
            }
        }

        public StringBuilder aString(){
           StringBuilder str = new StringBuilder();
           str.append(inicio+" ");
           str.append(fin+ " ");
           str.append(peso);
           return str;
        }
    }

    public static void main(String[] args) throws IOException{

        FileReader f = new FileReader("entrada.txt");
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(f);

        String linea = input.readLine();
        while(!linea.equals("0")){

            coste = 0;

            int numCiudades = Integer.parseInt(linea);
            linea = input.readLine();
            long numCarreteras = Long.parseLong(linea);

            List[] plano = new List[numCiudades];
            Set<Integer> nodosNoVisitados = new HashSet<>();

            for(int i=0; i<numCiudades; i++){
                plano[i] = new ArrayList();
                nodosNoVisitados.add(i);
            }

            for(int i=0; i<numCarreteras; i++){
                linea = input.readLine();
                String[] partes = linea.split(" ");
                int inicio = Integer.parseInt(partes[0]);
                int destino = Integer.parseInt(partes[1]);
                long precio = Long.parseLong(partes[2]);

                plano[inicio].add(new Arista(inicio, destino, precio));
                plano[destino].add(new Arista(destino, inicio, precio));
            }

            int islas = 0;
            int carreteras = 0;

            while(!nodosNoVisitados.isEmpty()){
                Iterator it = nodosNoVisitados.iterator();
                int ciudad = (int) it.next();
                islas++;

                nodosNoVisitados.remove(ciudad);

                List<Arista> isla = primIslas(ciudad, plano, nodosNoVisitados);
                carreteras += isla.size();

                /*for(Arista i: isla){
                    coste+=i.peso;
                }*/
            }

            System.out.println(islas +" "+carreteras+" "+ coste);
            System.out.println("---");

            linea = input.readLine();
        }
    }

    public static List<Arista> primIslas(int ciudad, List[] plano, Set<Integer> nodos){

        List<Arista> isla = new ArrayList<>();

        PriorityQueue<Arista> cola = new PriorityQueue<>();
        cola.addAll(plano[ciudad]);

        //Para llevar un seguimiento de en las que hemos estado
        Set<Integer> visitados = new HashSet<>();
        visitados.add(ciudad);

        //Empieza el bucle voraz
        while(!cola.isEmpty()){
            //Cogemos la mejor
            Arista carretera = getBestCarretera(cola);
            //Si se puede meter
            if(esFactible(carretera, visitados)){
                coste += carretera.peso;
                isla.add(carretera);

                cola.addAll(plano[carretera.fin]);

                visitados.add(carretera.fin);
                nodos.remove(carretera.fin);
            }

            cola.remove(carretera);

        }

        return isla;
    }

    public static Arista getBestCarretera(PriorityQueue<Arista> cola){
        return cola.poll();
    }


    public static boolean esFactible(Arista ar, Set<Integer> conjunto){
        //En este caso el conjunto es el de no visitados así que tengo que ver que haga ciclo con ese conjunto

        //Si solo tiene uno de los extremos en el conjunto
        return !conjunto.contains(ar.fin);
    }
}
