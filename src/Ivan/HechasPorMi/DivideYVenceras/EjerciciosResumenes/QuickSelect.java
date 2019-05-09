package Ivan.HechasPorMi.DivideYVenceras.EjerciciosResumenes;

import java.util.ArrayList;
import java.util.List;

public class QuickSelect {
    public static int TAM =200;
    public static void main(String[] args) {
        List<Integer> datos = new ArrayList<>(TAM);
        for (int x=0;x<TAM;x++) {
            datos.add((int) (Math.random() * 20) + 1);
        }
        for (int i = 0; i <TAM ; i++) {
            System.out.print(datos.get(i)+" ");
        }
        cuartiles(datos);
    }

    private static void cuartiles(List<Integer> datos) {
        int primcuartil=(datos.size()-1)/4;
        int mediana=(datos.size()-1)/2;
        int tercuartil=3*(datos.size()-1)/4;
        System.out.println();
        System.out.println("El primer cuartil es: "+quickSelect(datos,primcuartil));
        System.out.println();
        System.out.println("La mediana es: "+quickSelect(datos,mediana));
        System.out.println();
        System.out.println("El tercer cuartil es: "+quickSelect(datos,tercuartil));
    }

    public static int quickSelect(List<Integer>datos,int valor){
        int resultado=0;
        int pivote=0;
        List<Integer> porEncimaPivote= new ArrayList<>();
        List<Integer> porDebajoPivote= new ArrayList<>();
        List<Integer> igualPivote= new ArrayList<>();
        pivote=datos.get(datos.size()/2);
        for (Integer num:datos) {
            if (num<pivote){
                porDebajoPivote.add(num);
            }else if (num>pivote){
                porEncimaPivote.add(num);
            }else{
                igualPivote.add(num);
            }
        }
        if (valor<porDebajoPivote.size()){
            resultado=quickSelect(porDebajoPivote,valor);}
        else if (valor<porDebajoPivote.size()+igualPivote.size()){
            resultado=pivote;}
        else{
            resultado=quickSelect(porEncimaPivote,(valor-porDebajoPivote.size()-igualPivote.size()));
        }
        return resultado;
    }
}
