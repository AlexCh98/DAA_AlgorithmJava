package poo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
	// write your code here
        /*try{
            FileReader f = new FileReader("entrada");
            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader input = new BufferedReader(in);*/
            Scanner scan = new Scanner(System.in);
            int casos = scan.nextInt();

            for(int i=0; i<casos; i++) {
                int numActividades = scan.nextInt();

                int[][] actividades = new int[numActividades][2];
                for(int j=0; j<numActividades; j++){
                    actividades[j][0] = scan.nextInt();
                    actividades[j][1] = scan.nextInt();
                }
                int solucion = greedy(actividades);
                System.out.println(solucion);
            }

        /*}catch(IOException ex){
            System.out.println(ex.getMessage());
        }*/
    }

    private static int greedy(int[][] actividades){
        int n = actividades.length;
        Set<Integer> cand = new HashSet<Integer>(n);
        for(int i=0; i<n; i++){
            cand.add(i);
        }
        int sol=0;
        int hora = 0;

        while(!cand.isEmpty()){
            int numMejorActividad = getBestActividad(cand,actividades);
            cand.remove(numMejorActividad);
            if(isFeasible(actividades, hora, numMejorActividad)){
                sol++;
                hora = actividades[numMejorActividad][1];
            }
        }

        return sol;
    }

    private static int getBestActividad(Set<Integer> cand, int[][] actividades){
        int bestHoraFin = Integer.MAX_VALUE;
        Set<Integer> clon = cand;
        Iterator it = clon.iterator();
        int bestActividad = (int) it.next();

        for(int i: cand){
            if(actividades[i][1] <= bestHoraFin){
                bestHoraFin = actividades[i][1];
                bestActividad = i;
            }
        }
        return bestActividad;
    }

    private static boolean isFeasible(int[][] actividades, int hora, int bestActividad){
        return (actividades[bestActividad][0] >= hora);
    }
}
