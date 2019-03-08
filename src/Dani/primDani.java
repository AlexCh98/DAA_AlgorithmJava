package Dani;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;


public class primDani {

    public static void main(String[] args) {
	// write your code here
        Integer[][] g = new Integer[8][8];
        Set<Integer[]> aristas = new HashSet<>(11);

        //Nodo 1
        g[1][3] = 1;
        g[1][4] = 2;
        g[1][7] = 6;

        //Nodo 2
        g[2][5] = 2;
        g[2][6] = 4;
        g[2][7] = 7;

        //Nodo 3
        g[3][1] = 1;
        g[3][4] = 3;
        g[3][7] = 5;

        //Nodo 4
        g[4][1] = 2;
        g[4][3] = 3;
        g[4][5] = 1;
        g[4][6] = 9;

        //Nodo 5
        g[5][2] = 2;
        g[5][4] = 1;
        g[5][7] = 8;

        //Nodo 6
        g[6][2] = 4;
        g[6][4] = 9;

        //Nodo 7
        g[7][1] = 6;
        g[7][2] = 7;
        g[7][3] = 5;
        g[7][5] = 8;

        try{
            FileReader f = new FileReader("entradaPrim");
            Scanner scan = new Scanner(f);

            //Rellenar el conjunto de aristas (candidatos)
            for(int i=0; i<11; i++){
                Integer[] ar = {scan.nextInt(), scan.nextInt()};
                aristas.add(ar);
            }

            List<Integer[]> prim = Prim(aristas, g, 5);
            System.out.println();
            System.out.print("Aristas del árbol de recubrimiento mínimo:{ ");
            for(Integer[] ar: prim){
                System.out.print("{"+ar[0]+","+ar[1]+"} ");
            }
            System.out.println("}");

        }catch(IOException ex){
            ex.getMessage();
        }

    }

    private static List<Integer[]> Prim(Set<Integer[]> candidatos, Integer[][] g, int nodo){
        //Inicialización
        int n = g.length;
        List<Integer[]> sol = new ArrayList<>();
        boolean isSol = false;
        Set<Integer> vertices = new HashSet<>(n);

        //Metemos el nodo desde el que iniciamos en el conjunto de vértices visitados
        vertices.add(nodo);

        //Buscamos la solución
        while(!candidatos.isEmpty() && !isSol){

            //Coger mejor arista y quitarla
            Integer[] mejorArista = getBestArista(candidatos,g,vertices);
            System.out.print("Mejor arista: {"+mejorArista[0]+","+mejorArista[1]+"} ");
            candidatos.remove(mejorArista);

            //Comprobar si se puede añadir a la solución
            if(esFactible(mejorArista,vertices)){
                System.out.println(" ---La insertamos");
                sol.add(mejorArista);

                //Añadimos los vértices de la arista a los vértices visitados
                vertices.add(mejorArista[0]);
                vertices.add(mejorArista[1]);
            }else{
                System.out.println(" ---La rechazamos");
            }
            //Si ya se han visitados todos los vértices
            if(vertices.size() == n-1){ //Aquí pongo n-1 porque no cuento el nodo 0, está aislado
                isSol = true;
            }
        }

        return sol;
    }

    private static Integer[] getBestArista(Set<Integer[]> candidatos, Integer[][] g, Set<Integer> vertices){
        //Inicialización
        Integer[] mejor = {0,0};
        int mejorValor = Integer.MAX_VALUE;

        for(Integer[] cand: candidatos){
            //Si la arista tiene menos peso y tiene algún extremo en los vértices visitados
            if((g[cand[0]][cand[1]] < mejorValor) && ((vertices.contains(cand[0])||vertices.contains(cand[1])))){
                mejor = cand;
                mejorValor = g[cand[0]][cand[1]];
            }
        }
        return mejor;
    }

    private static boolean esFactible(Integer[]cand, Set<Integer> vertices){
        //Si no hace ciclo, es decir, si sólo uno de los vértices de la arista ha sido visitado
        return !(vertices.contains(cand[0])&&vertices.contains(cand[1]));
    }
}
