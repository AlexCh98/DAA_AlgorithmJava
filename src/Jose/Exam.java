package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Exam {

    public static void main(String[] Args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] linea = reader.readLine().split(" ");
        int numero_alumnos = Integer.parseInt(linea[0]);
        int numero_conexiones = Integer.parseInt(linea[1]);
        int numeros_examenes = Integer.parseInt(linea[2]);
        int[][] clase = new int[numero_alumnos][numero_alumnos];
        for (int i = 0; i < numero_conexiones; i++) {
            linea = reader.readLine().split(" ");
            int alumno_u = Integer.parseInt(linea[0]);
            int alumno_v = Integer.parseInt(linea[1]);
            clase[alumno_u][alumno_v] = 1;
            clase[alumno_v][alumno_u] = 1;
        }
        int[] colocacion = new int[numero_alumnos];
        System.out.println(colocar(clase, numeros_examenes, colocacion, 0) ? "OK" : "NO HAY SUFICIENTE");
    }

    private static boolean colocar(int[][] grafo, int numeroExamenes, int examenes[], int alumno) {
        if (alumno == grafo.length) return true;
        for (int ex = 1; ex <= numeroExamenes; ex++) {
            if (esBueno(examenes, ex, grafo, alumno)) {
                examenes[alumno] = ex;
                if (colocar(grafo, numeroExamenes, examenes, alumno + 1)) return true;
                examenes[alumno] = 0;
            }
        }
        return false;
    }

    private static boolean esBueno(int[] examenes, int ex, int[][] grafo, int alumno) {
        for (int i = 0; i < examenes.length; i++) {
            if (grafo[i][alumno] == 1 && ex == examenes[i]) return false;
        }
        return true;
    }
}
