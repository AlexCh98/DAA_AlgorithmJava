package poo;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class miKruskal {

    public static class Arista implements Comparable{
        public int inicio;
        public int fin;
        public Integer peso;

        public Arista(int inicio, int fin, int peso){
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
            }if(this.peso > aux.peso){
                return 1;
            }else{
                return -1;
            }
        }

        public int getInicio() {
            return inicio;
        }

        public int getFin() {
            return fin;
        }

        public Integer getPeso() {
            return peso;
        }
    }

    public static void main(String[] args) {
        // write your code here
        Integer[][] g = new Integer[8][8];
        Set<Arista> aristas = new TreeSet<>();

        try{
            FileReader f = new FileReader("entradaPrim");
            Scanner scan = new Scanner(f);

            //Rellenar el conjunto de aristas (candidatos)
            for(int i=0; i<11; i++){
                Arista ar = new Arista(scan.nextInt(), scan.nextInt(),scan.nextInt());
                aristas.add(ar);
            }

            List<Arista> solucion = Kruskal(aristas,g);

            System.out.print("Solución: {");
            for(Arista ar: solucion){
                System.out.print("{"+ar.inicio + "," + ar.fin + "}");
            }
            System.out.print("}");

        }catch(IOException ex){
            ex.getMessage();
        }
    }

    private static List<Arista> Kruskal(Set<Arista> candidatos, Integer[][] g){
        //Inicialización
        int n = g.length;
        List<Arista> sol = new ArrayList<>();
        List<Set<Integer>> componentes_conexas = new ArrayList<>();
        boolean isSol = false;

        Queue<Arista> aristas = new LinkedList<>();
        for(Arista ar: candidatos){
            aristas.add(ar);
        }

        for(int i=1; i<g.length ; i++){
            Set<Integer> s = new HashSet<>();
            s.add(i);
            componentes_conexas.add(s);
        }

        //Buscamos la solución
        while(componentes_conexas.size() > 1){

            //Coger mejor arista y quitarla
            Arista mejorArista = aristas.remove(); //getBestArista
            System.out.println("Mejor arista: {"+mejorArista.inicio+","+mejorArista.fin+"} ");

            //Comprobar si se puede añadir a la solución
            if(esFactible(mejorArista,componentes_conexas)){
                //Traza
                System.out.println(" ---La insertamos");

                System.out.print("Componentes conexas: ");
                for(Set<Integer> s: componentes_conexas){
                    System.out.print("{");
                    for(int a: s){
                        System.out.print(a+",");
                    }
                    System.out.print("}");
                }
                System.out.println();

                //Añadir
                sol.add(mejorArista);

            }else{
                System.out.println(" ---La rechazamos");
            }
            System.out.println();
        }

        return sol;
    }

    private static Arista getBestArista(Queue<Arista> candidatos){
        return candidatos.remove();
    }

    private static boolean esFactible(Arista cand, List<Set<Integer>> componentes_conexas){
        //Si no hace ciclo, es decir, si sólo uno de los vértices de la arista ha sido visitado
        boolean ciclo = false;
        int i =0;
        int posI = 0;
        int posF = 0;
        while(!ciclo && i < componentes_conexas.size() ){
            if(componentes_conexas.get(i).contains(cand.inicio) && componentes_conexas.get(i).contains(cand.fin)){
                posI = i;
                posF = i;
                ciclo = true;
            }else if(componentes_conexas.get(i).contains(cand.inicio)){
                posI = i;
            }else if(componentes_conexas.get(i).contains(cand.fin)){
                posF = i;
            }
            i++;
        }
        if(posI != posF){
            componentes_conexas.get(posI).addAll(componentes_conexas.get(posF));
            componentes_conexas.remove(posF);
        }

        return !ciclo;
    }
}
