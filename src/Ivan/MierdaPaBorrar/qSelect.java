package Ivan.MierdaPaBorrar;

import java.util.ArrayList;
import java.util.List;

public class qSelect {
    public static int TAM = 20;

    public static void main(String[] args) {
        List<Integer> datos = new ArrayList<>(TAM);
        for (int x = 0; x < TAM; x++) {
            datos.add((int) (Math.random() * 20) + 1);
        }
        for (int i = 0; i < TAM; i++) {
            System.out.print(datos.get(i) + " ");
        }
        mediana(datos);
    }

    private static void mediana(List<Integer> datos) {
        int med = (datos.size() - 1) / 2;
        System.out.println("La mediana es: " + qSelect(datos, med));

    }

    private static int qSelect(List<Integer> datos, int med) {
        int pivote = 0;
        int resultado = 0;
        List<Integer> poencima = new ArrayList<>();
        List<Integer> podebajo = new ArrayList<>();
        List<Integer> igualpivote = new ArrayList<>();
        pivote = datos.get(datos.size() / 2);
        for (Integer numero : datos) {
            if (numero < pivote) {
                podebajo.add(numero);
            } else if (numero > pivote) {
                poencima.add(numero);
            } else {
                igualpivote.add(numero);
            }
        }
            if (med < podebajo.size()) {
                resultado = qSelect(podebajo, med);
            } else if (med < podebajo.size() + igualpivote.size()) {
                resultado=pivote;
            }else{
                resultado = qSelect(poencima, (med - podebajo.size() - igualpivote.size()));
            }
        return resultado;
    }
}
