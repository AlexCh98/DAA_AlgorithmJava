package Dani;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class DarkSoulsBB {

    public static void main(String[] args) throws IOException{
	// write your code here
        //FileReader f = new FileReader("entradaSouls");
        Scanner scan = new Scanner(System.in);

        int numEnemigos = scan.nextInt();
        long[][] enemigos = new long[numEnemigos][2];
        long nivel = scan.nextLong();

        enemigos[0][0] = nivel;
        enemigos[0][1] = nivel;

        long exp = nivel;
        for(int i=1; i<numEnemigos; i++){
            nivel = scan.nextLong();
            exp+=nivel;
            enemigos[i][0] = nivel;
            enemigos[i][1] = exp;
        }

        long numCasos = scan.nextLong();

        for(int i=0; i<numCasos; i++) {
            long nivelJugador = scan.nextLong();
            int pos = busquedaBinaria(nivelJugador, enemigos);
            if(pos > -1) {
                System.out.println(pos + 1 + " " + enemigos[pos][1]);
            }else{
                System.out.println("0 0");
            }
        }
    }

    public static int busquedaBinaria(long nivelJugador, long[][] enemigos){
        int medio;
        int max = enemigos.length-1;
        int min = 0;
        int pos = 0;

        if(enemigos[max][0] < nivelJugador){
            return max;
        }else if(enemigos[min][0] > nivelJugador){
            return -1;
        }


        while(min<=max){
            medio = (max+min)/2;
            if(enemigos[medio][0] > nivelJugador) {
                max = medio-1;
            }else if(enemigos[medio][0] < nivelJugador){
                min = medio+1;
            }else if(enemigos[medio][0] == nivelJugador){
                pos = medio;
                break;
            }
        }

        if(max < min){
            pos = max;
        }


        return pos;
    }
}
