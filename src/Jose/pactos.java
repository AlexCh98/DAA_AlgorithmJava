import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class pactos {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numeroCandidatos = Integer.parseInt(reader.readLine());
        Datos datos = new Datos();
        datos.diputados = new int[numeroCandidatos];
        datos.perjuicios = new int[numeroCandidatos];
        datos.nombres = new String[numeroCandidatos];
        int ganador = 0;
        int maximoDiputados = Integer.MIN_VALUE;
        int diputadosTotales = 0;
        for (int i = 0; i < numeroCandidatos; i++) {
            String[] linea = reader.readLine().split(" ");
            datos.nombres[i] = linea[0];
            datos.diputados[i] = Integer.parseInt(linea[1]);
            diputadosTotales += datos.diputados[i];
            if (datos.diputados[i] > maximoDiputados) {
                ganador = i;
                maximoDiputados = datos.diputados[i];
            }
            datos.perjuicios[i] = Integer.parseInt(linea[2]);
        }
        System.out.println(datos.nombres[ganador]);
        calcularPactos(datos, ganador, diputadosTotales / 2 + 1).forEach(System.out::println);
    }

    public static class Datos {
        int[] diputados;
        int[] perjuicios;
        String[] nombres;
    }


    private static TreeSet<String> calcularPactos(Datos datos, int ganador, int diputadosNecesarios) {
        TreeSet<String> solucion = new TreeSet<>();
        Set<Integer> candidatos = new HashSet<>();
        for (int i = 0; i < datos.diputados.length; i++) {
            if (i != ganador) candidatos.add(i);
        }
        int diputadosActuales = datos.diputados[ganador];
        while (diputadosActuales <= diputadosNecesarios) {

            int candidato = mejorCandiato(candidatos, datos);
            candidatos.remove(candidato);
            diputadosActuales += datos.diputados[candidato];
            solucion.add(datos.nombres[candidato]);
        }
        return solucion;
    }

    private static int mejorCandiato(Set<Integer> candidatos, Datos datos) {
        int mejorCandidato = 0;
        double mejorRatio = Integer.MIN_VALUE;
        for (int candidato : candidatos) {
            double ratio = (double) datos.diputados[candidato] / datos.perjuicios[candidato];
            if (ratio > mejorRatio) {
                mejorCandidato = candidato;
                mejorRatio = ratio;
            }
        }
        return mejorCandidato;
    }

}

