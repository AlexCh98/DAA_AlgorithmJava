package DivideYVenceras.Juez;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DS_Score {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int enemigos = Integer.parseInt(reader.readLine());
        long[][] niveles_enemigos = new long[enemigos][2];
        String[] linea = reader.readLine().split(" ");
        niveles_enemigos[0][0] = Long.parseLong(linea[0]);
        niveles_enemigos[0][1] = Long.parseLong(linea[0]);
        for (int enemigo = 1; enemigo < enemigos; enemigo++) {
            long nivel = Long.parseLong(linea[enemigo]);
            niveles_enemigos[enemigo][0] = nivel;
            niveles_enemigos[enemigo][1] = niveles_enemigos[enemigo - 1][1] + nivel;
        }
        int casos_prueba = Integer.parseInt(reader.readLine());
        for (int i = 0; i < casos_prueba; i++) {
            int nivel_caballero = Integer.parseInt(reader.readLine());
            int enemigos_derrotados = busquedaBinaria(niveles_enemigos, nivel_caballero);
            System.out.println(enemigos_derrotados > -1 ? (enemigos_derrotados + 1)+ " "+  niveles_enemigos[enemigos_derrotados][1] : "0 0");
        }
    }

    private static int busquedaBinaria(long[][] arr, int element) {
        int inicio = 0, fin = arr.length - 1;
        if (element < arr[inicio][0]) return -1;
        if (element > arr[fin][0]) return fin;
        while (inicio <= fin) {
            int medio = (fin + inicio) / 2;
            if (arr[medio][0] == element) return medio;
            if (arr[medio][0] > element) fin = medio - 1;
            else inicio = medio + 1;
        }
        return fin;
    }
}
/*
7
1 2 3 4 5 6 7
3
3
10
2

 */