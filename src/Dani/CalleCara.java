package Dani;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CalleCara {

    public static long coste;
    public static long carreteras;
    public static int islas;

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

        //FileReader f = new FileReader("entrada.txt");
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(in);

        String linea = input.readLine();
        while(!linea.equals("0")){

            coste = 0;
            islas = 0;
            carreteras = 0;

            //RELLENAR DATOS
            int numCiudades = Integer.parseInt(linea);
            linea = input.readLine();
            long numCarreteras = Long.parseLong(linea);

            List[] plano = new List[numCiudades];

            for(int i=0; i<numCiudades; i++){
                plano[i] = new ArrayList();
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

            //FIN RELLENAR DATOS
            primIslas(plano);
            System.out.println(islas +" "+carreteras+" "+ coste);
            System.out.println("---");

            linea = input.readLine();
        }
    }

    public static List<Arista> primIslas(List[] plano){

        boolean[] visitados = new boolean[plano.length];

        List<Arista> isla = new ArrayList<>();

        //Para llevar un seguimiento de en las que hemos estado

        //Bucle voraz
        for(int i=0; i<plano.length; i++){
            if(!visitados[i]){
                //Estamos en una nueva isla
                islas++;
                //Creamos una nueva cola de prioridad para guardar las aristas
                PriorityQueue<Arista> cola = new PriorityQueue<>();
                //Añadimos las aristas que tiene el nodo
                cola.addAll(plano[i]);

                //Lo dejamos como visitado
                visitados[i] = true;

                //Empieza el bucle voraz
                while(!cola.isEmpty()){
                    //System.out.println("Info: " +islas +" "+carreteras+" "+ coste);

                    //Cogemos la mejor
                    Arista carretera = getBestCarretera(cola);
                    /*System.out.println("Arista: "+carretera.aString());
                    System.out.println();*/

                    //Si se puede meter
                    if(esFactible(carretera, visitados)){

                        //Actualizamos costes y número de carreteras
                        coste += carretera.peso;
                        carreteras+= 1;

                        //Guardo en la isla
                        isla.add(carretera);

                        //Añadimos aristas del nodo
                        cola.addAll(plano[carretera.fin]);

                        //Ponemos como visitado el nodo destino de la arista
                        visitados[carretera.fin] = true;
                    }
                    //cola.remove(carretera);
                }
            }
        }


        return isla;
    }

    public static Arista getBestCarretera(PriorityQueue<Arista> cola){
        return cola.poll();
    }


    public static boolean esFactible(Arista ar, boolean[] conjunto){
        //En este caso el conjunto es el de no visitados así que tengo que ver que haga ciclo con ese conjunto

        //Si solo tiene uno de los extremos en el conjunto
        return !conjunto[ar.fin];
    }
}
