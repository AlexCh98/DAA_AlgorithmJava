package Jose;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bound {
    public static void main(String[] Args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int numero_niveles = Integer.parseInt(bf.readLine());
        String[] line = bf.readLine().split(" ");
        int[] niveles = new int[numero_niveles];
        for (int i = 0; i < numero_niveles; i++) {
            niveles[i] = Integer.parseInt(line[i]);
        }
        //System.out.println("Niveles " + Arrays.toString(niveles));
        int numero_casos = Integer.parseInt(bf.readLine());
        line = bf.readLine().split(" ");
        for (int i = 0; i < numero_casos; i++) {
            int nivel_buscado = Integer.parseInt(line[i]);
            //System.out.println("Nivel buscado: " + nivel_buscado);
            int indice_superior = upperBound(niveles, nivel_buscado);
            if (indice_superior == 0) {//O es el mas pequeño o es mas pequño que todos
                if (niveles[0] == nivel_buscado) {//Es el mas pequeño
                    System.out.println("X " + niveles[1]);
                } else {//Es mas pequeño que todos
                    System.out.println("X " + niveles[0]);
                }
            } else if (indice_superior == -1) {//O es el mas grande o es mas grande que todos
                if (niveles[niveles.length - 1] == nivel_buscado) {//Es el mas grande
                    System.out.println(niveles[niveles.length - 2] + " X");
                } else {//Es mas grande que todos
                    System.out.println(niveles[niveles.length - 1] + " X");
                }
            } else if (niveles[indice_superior] == nivel_buscado) {// Encontrado
                System.out.println(niveles[indice_superior - 1] + " " + niveles[indice_superior + 1]);
            } else {// Cualquier otro caso
                System.out.println(niveles[indice_superior-1] + " " + niveles[indice_superior]);
            }
        }
    }

    private static int upperBound(int[] arr, int element) {
        int min = 0;
        int max = arr.length - 1;
        if (element <= arr[min]) return min;
        if (element >= arr[max]) return -1;
        while (min <= max) {
            int medio = (min + max) / 2;
            if (arr[medio] == element) {
                return medio;
            } else if (arr[medio] > element) {
                max = medio - 1;
            } else {
                min = medio + 1;
            }
        }
        return min;
    }


}
