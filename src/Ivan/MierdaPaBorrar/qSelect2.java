package Ivan.MierdaPaBorrar;

import java.util.ArrayList;
import java.util.List;

public class qSelect2 {
    public static int TAM = 20;
    public static void main(String[] args) {
        List<Integer> datos = new ArrayList<>(TAM);
        for (int x = 0; x < TAM; x++) {
            datos.add((int) (Math.random() * 20) + 1);
        }
        for (int i = 0; i < TAM; i++) {
            System.out.print(datos.get(i) + " ");
        }
        cuartil(datos);
    }

    private static void cuartil(List<Integer> datos) {
        int valor= (datos.size()-1)/2;
        System.out.println("El primer cuartil es: "+qSelec(datos,valor));
    }

    private static int qSelec(List<Integer> datos, int valor) {
        int resultado=0;
        int pivote=0;
        ArrayList<Integer> pordebajo= new ArrayList<>();
        ArrayList<Integer> porencima= new ArrayList<>();
        ArrayList<Integer> igualpiv= new ArrayList<>();
        pivote=datos.get(datos.size()/2);
        for (Integer num :
                datos) {
            if (num < pivote) {
                pordebajo.add(num);
            } else if (num > pivote) {
                porencima.add(num);
            } else {
                igualpiv.add(num);
            }
        }
        if (valor<pordebajo.size()){
            resultado=qSelec(pordebajo,valor);
        }
        else if (valor<pordebajo.size()+igualpiv.size()){
            resultado=pivote;
        }else{
            resultado=qSelec(porencima,(valor-pordebajo.size()-igualpiv.size()));
        }
        return resultado;
        }
    }
