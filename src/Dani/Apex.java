package poo;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Apex {

    public static void main(String[] args) throws IOException {

        FileReader f = new FileReader("entradaApex");
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(f);

        int numJugadores = Integer.parseInt(input.readLine());
        int[] mmr = new int[numJugadores]; //Para guardar los mmr de cada jugador

        String[] partes = input.readLine().split(" ");
        int mmrTotalJugadores = 0;
        for (int i = 0; i < numJugadores; i++) {
            mmr[i] = Integer.parseInt(partes[i]);
            mmrTotalJugadores += mmr[i];
        }

        int dif = Integer.parseInt(input.readLine()); //Diferencia máxima entre equipos
        int tamannoEquipo = numJugadores/2; //Tamaño del equipo


        if (backTracking(mmr, mmrTotalJugadores, dif, tamannoEquipo)) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }


    public static boolean backTracking(int[] mmr, int mmrTotalJugadores, int dif, int tamannoEquipo) {
        Stack<Integer> equipo = new Stack<>();

        long mmrEquipo = mmr[0];
        equipo.add(mmr[0]);

        return equilibraEquipo(mmr, mmrTotalJugadores, equipo, mmrEquipo, dif, tamannoEquipo, 1); //Puntero a la siguiente posicion
    }


    public static boolean equilibraEquipo(int[] mmr, int mmrTotalJugadores, Stack<Integer> equipo, long mmrEquipo, int dif, int tamannoEquipo, int punt){

        if(equipo.size() == tamannoEquipo) {

            System.out.print("Equipo: ");
            for (Integer i : equipo) {
                System.out.print(i + ", ");
            }

            System.out.println("\nEl mmr del equipo actual: " + mmrEquipo);
            System.out.println();

            if (Math.abs(mmrEquipo - (mmrTotalJugadores - mmrEquipo)) <= dif) { //Si es solucion salimos
                return true;
            }

        }else{
            for(int i=punt; i< tamannoEquipo*2; i++){
                equipo.add(mmr[i]);
                mmrEquipo += mmr[i];

                boolean x = equilibraEquipo(mmr,mmrTotalJugadores, equipo, mmrEquipo, dif, tamannoEquipo, i+1);

                if(x){
                    return true;
                }else{
                    mmrEquipo -= equipo.pop();
                }
            }
        }

        return false;
    }
}
