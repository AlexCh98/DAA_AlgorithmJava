package Dani;

import java.io.*;
import java.util.*;

public class finalStory {


    public static void main(String[] args) throws IOException {
	// write your code here
        //FileReader f = new FileReader("entrada");
        Scanner scan = new Scanner(System.in);
        int casos = Integer.parseInt(scan.nextLine());

        for(int i=0; i<casos; i++){
            long dañoRecibido = 0;
            int dañoJug = Integer.parseInt(scan.nextLine());
            int numEnemigos = Integer.parseInt(scan.nextLine());
            int[][]enemigos = new int[numEnemigos][3]; //Enemigo --> Daño, vida, turnos para matar
            Set<Integer> cand = new HashSet<Integer>();
            long dañoTotalEnemigos = 0;

            String[] daños = scan.nextLine().split(" ");
            String[] vidas = scan.nextLine().split(" ");

            //Inicializar enemigos
            for(int j=0; j<numEnemigos; j++){
                enemigos[j][0] = Integer.parseInt(daños[j]);
                enemigos[j][1] = Integer.parseInt(vidas[j]);
                if(enemigos[j][1]%dañoJug != 0){
                    enemigos[j][2] = (enemigos[j][1]/dañoJug)+1;
                }else{
                    enemigos[j][2] = (enemigos[j][1]/dañoJug);
                }
                cand.add(j);
                dañoTotalEnemigos += enemigos[j][0];
            }

            //mergeSort(enemigos,numEnemigos);

            //Voraz
            while(!cand.isEmpty()){
                int mejorOpcion = getBestEnemigo(cand, enemigos);
                //System.out.println(mejorOpcion);
                while(enemigos[mejorOpcion][1] > 0){ //Mientras que esté vivo el enemigo no se cambia de objetivo
                    dañoRecibido += dañoTotalEnemigos;
                    enemigos[mejorOpcion][1] = enemigos[mejorOpcion][1] - dañoJug;
                    //System.out.println(dañoRecibido);
                }
                dañoTotalEnemigos -= enemigos[mejorOpcion][0];
                cand.remove(mejorOpcion);
            }
            System.out.println(dañoRecibido);
        }
    }

    private static int getBestEnemigo(Set<Integer> cand, int[][] enemigos){
        Iterator it = cand.iterator();
        int mejorEnemigo = (int) it.next();
        double mejorRatio = Integer.MIN_VALUE;
        double ratioactual;

        for(int i: cand) {

            ratioactual = (double) enemigos[i][0] / enemigos[i][2]; // Daño/Turnos

            if (ratioactual > mejorRatio) {
                mejorEnemigo = i;
                mejorRatio = ratioactual;
            } else if ((ratioactual == mejorRatio) && (enemigos[i][0] > enemigos[mejorEnemigo][0])){ //En el caso de que tengan el mismo ratio nos quedamos con el que más daño tenga
                mejorEnemigo = i;
                mejorRatio = ratioactual;
            } else if (ratioactual == mejorRatio && enemigos[i][1] < enemigos[mejorEnemigo][1]){
                mejorRatio = ratioactual;
            }
        }
        return mejorEnemigo;
    }

    /*public static void mergeSort(int[][] array, int n) {
        if (n < 2) {
            return;
        }
        int medio = n / 2;
        int[][] mitad1 = new int[medio][3];
        int[][] mitad2 = new int[n - medio][3];

        //Copiar la primera mitad
        for (int i = 0; i < medio; i++) {
            mitad1[i] = array[i];
        }
        for (int i = medio; i < n; i++) {
            mitad2[i - medio] = array[i];
        }
        mergeSort(mitad1, medio);
        mergeSort(mitad2, n - medio);

        merge(array, mitad1, mitad2, medio, n - medio);

    }
    public static void merge(int[][] array, int[][] mitad1, int[][] mitad2, int limite1, int limite2) {

        int i = 0, j = 0, k = 0;

        //ordenamos
        while (i < limite1 && j < limite2) {
            if (mitad1[i][2] >= mitad2[j][2]) {
                array[k++] = mitad1[i++];
            }
            else {
                array[k++] = mitad2[j++];
            }
        }

        //rellenamos ordenadamente
        while (i < limite1) {
            array[k++] = mitad1[i++];
        }
        while (j < limite2) {
            array[k++] = mitad2[j++];
        }
    }
*/
}
