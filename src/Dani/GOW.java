package poo;

import java.io.*;

public class GOW {

    public static void main(String[] args) throws IOException {
	// write your code here
        //FileReader f = new FileReader("entradaGOW");
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(in);

        int nEnemigos = Integer.parseInt(input.readLine());
        int[] enemies = new int[nEnemigos];
        String[] partes = input.readLine().split(" ");

        for(int i=0; i<nEnemigos;i++){
            enemies[i] = Integer.parseInt(partes[i]);
        }

        int nCasos = Integer.parseInt(input.readLine());
        partes = input.readLine().split(" ");
        int[] nivelesKratos = new int[nCasos];

        for(int i=0; i<nCasos;i++){
            nivelesKratos[i] = Integer.parseInt(partes[i]);
        }

        for(int i=0; i<nCasos; i++){
            int nivelKratos = nivelesKratos[i];
            int pos = busquedaBinaria(enemies,nivelKratos);

            if(pos == -1){
                System.out.println("X "+enemies[0]);
            }else if(pos == -2){
                System.out.println(enemies[enemies.length-1]+" X");
            }else if(enemies[pos] == nivelKratos){
                if(pos == 0){
                    System.out.println("X "+enemies[pos+1]);
                }else if (pos == enemies.length-1){
                    System.out.println(enemies[pos-1]+ " X");
                }else{
                    System.out.println(enemies[pos-1]+ " " + enemies[pos+1]);
                }
            }else{
                System.out.println(enemies[pos]+" "+enemies[pos+1]);
            }
        }
    }

    public static int busquedaBinaria(int[] enemies, int nivelKratos){
        int max = enemies.length-1;
        int min = 0;

        if(nivelKratos < enemies[min]){ //Si es menor que todos
            return -1;
        }else if(nivelKratos > enemies[max]){ //Si es mayor que todos
            return -2;
        }

        while(max >= min){
            int medio = (max+min)/2;

            if(nivelKratos == enemies[medio]){
                return medio;

            }else if(enemies[medio] > nivelKratos){
                max = medio-1;

            }else if(enemies[medio] < nivelKratos){
                min = medio+1;
            }
        }

        return max;
    }
}
