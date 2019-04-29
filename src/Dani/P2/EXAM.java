package Dani.P2;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;

public class EXAM {

    public static void main(String[] args) throws IOException {

        // FileReader f = new FileReader("entrada.txt");
        Scanner scan = new Scanner(System.in);

        int alumnos = scan.nextInt();
        int conexiones = scan.nextInt();
        int tipos = scan.nextInt();

        List[] grafo = new List[alumnos];

        for (int i = 0; i <alumnos ; i++) {
            grafo[i] = new ArrayList();
        }
        for(int i=0; i<conexiones; i++){
            int a1 = scan.nextInt();
            int a2 = scan.nextInt();

            grafo[a1].add(a2);
            grafo[a2].add(a1);
        }

        int[] colocados = new int[alumnos];
        Arrays.fill(colocados,-1);

        boolean[] visitados = new boolean[alumnos];

        if(coloca(grafo,tipos,colocados,0)){
            System.out.println("OK");
        }else{
            System.out.println("NO HAY SUFICIENTE");
        }

    }

    //La idea es hacer un recorrido en profundidad rellenando los colores de cada nodo

    public static boolean coloca(List[] grafo, int tipos, int[] colocados, int k){


        //Compruebo que todos tengan un examen
        if(estanColocados(colocados)){
            return true;
        }else {
            boolean suficientes = false;
            int i = 0;

            //Pruebo a poner los distintos examenes en este nodo
            while (i < tipos && !suficientes) {
                //System.out.println("Nodo " + k + ", Tipo " + i);
                //SI los adyacentes no son del mismo tipo
                if (esFactible(i, k, grafo, colocados)) {
                    colocados[k] = i;
                    suficientes = coloca(grafo, tipos, colocados, k+1);
                }

                //i++ para probar con el siguiente tipo de examen en este nodo
                i++;
            }
            colocados[k] = -1;
            return suficientes;
        }

    }

    public static boolean estanColocados(int[]colocados){
        boolean si = true;
        int i=0;
        //Para ver si todos los alumnos tienen algun examen asignado
        while (i<colocados.length && si){
            if(colocados[i] == -1){
                si = false;
            }
            i++;
        }

        return si;
    }

    //Comprueba que los adyancentes sean de otro tipo
    public static boolean esFactible(int tipo, int nodo, List[]grafo, int[] colocados){
        boolean esFac = true;
        Iterator it = grafo[nodo].iterator();

        //Recorro los adyacentes y miro si son del mismo tipo
        while(it.hasNext() && esFac){
            int t = (int)it.next();
            esFac = colocados[t]!=tipo;
        }
        return esFac;
    }
}
