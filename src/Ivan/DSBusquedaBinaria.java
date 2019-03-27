import java.util.Arrays;
import java.util.Scanner;

public class DSBusquedaBinaria {
    public static void main(String[] Args) {
        Scanner scanner = new Scanner(System.in);
        int numeroEnemigos = scanner.nextInt();
        int[] nivel = new int[numeroEnemigos];
        long[] sumaNivel = new long[numeroEnemigos];
        int nivelLeido = scanner.nextInt();
        sumaNivel[0] = nivelLeido;
        nivel[0] = nivelLeido;
        for (int i = 1; i < nivel.length; i++) {
            nivelLeido = scanner.nextInt();
            nivel[i] = nivelLeido;
            sumaNivel[i] = sumaNivel[i - 1] + nivelLeido;
        }
        int numeroCasos = scanner.nextInt();
        for (int i=0; i<numeroCasos; i++){
            int caso = scanner.nextInt();
            int resultado = busquedaBinaria(nivel,caso);
            if (resultado == -1){
                System.out.println(0+" "+0);
            }
            else {
                System.out.println((resultado+1)+" "+sumaNivel[resultado]);
            }
        }

    }

    public static int busquedaBinaria(int[] enemigos, int nivel) {
        int minimo = 0;
        int maximo = (enemigos.length - 1);
        if (nivel<enemigos[0]){return -1;}
        if (nivel>enemigos[enemigos.length-1]){return maximo;}
        while (minimo <= maximo) {
            int mitad = (maximo+minimo)/2;
            if (nivel > enemigos[mitad]) {
                minimo = mitad + 1;
            }
            else if (nivel < enemigos[mitad]) {
                maximo = mitad - 1;
            }
            else if (nivel == enemigos[mitad]) {
                return mitad;
            }
        }
        return maximo;
    }
}

