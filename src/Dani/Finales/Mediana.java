package Dani.Finales;

import java.util.ArrayList;
import java.util.List;

//Medianas
public class Mediana {

    public static void main(String[] args){
        List<Integer> vector = new ArrayList<>();
        vector.add(2);
        vector.add(3);
        vector.add(4);
        vector.add(1);
        vector.add(9);
        vector.add(8);
        vector.add(7);
        int d=2;
        int indice = (vector.size()-1)/2; //En este caso la mediana
        int mediana = kElemento(indice, vector);
        System.out.println("Mediana: " + mediana);

        List<Integer> candidatosInf = new ArrayList<>();
        List<Integer> candidatosSup = new ArrayList<>();
        List<Integer> solucion = new ArrayList<>();

        System.out.print("2 más cercanos: ");
        for(int i=1; i<=d; i++){
            candidatosInf.add(kElemento(indice-i,vector));
            candidatosSup.add(kElemento(indice+i,vector));
        }
        //Me da mucha pereza compararlos
        int puntInf=0;
        int puntSup=0;

        while(puntInf<candidatosInf.size() && puntSup<candidatosSup.size() && solucion.size()<d){
            int inf = candidatosInf.get(puntInf);
            int sup = candidatosSup.get(puntSup);

            if(mediana-inf <= sup-mediana){
                solucion.add(inf);
                puntInf++;
            }else{
                solucion.add(sup);
                puntSup++;
            }
        }

        for(int s: solucion){
            System.out.print(s+" ");
        }
        System.out.println();

        candidatosInf.clear();
        candidatosSup.clear();
        solucion.clear();


        System.out.print("2 más lejanos: ");
        puntInf=0;
        puntSup=0;

        for(int i=0; i<d; i++){
            candidatosInf.add(kElemento(i,vector));
            candidatosSup.add(kElemento(vector.size()-1-i,vector));
        }

        while(puntInf<candidatosInf.size() && puntSup<candidatosSup.size() && solucion.size()<d){
            int inf = candidatosInf.get(puntInf);
            int sup = candidatosSup.get(puntSup);

            if(mediana-inf >= sup-mediana){
                solucion.add(inf);
                puntInf++;
            }else{
                solucion.add(sup);
                puntSup++;
            }
        }

        for(int s: solucion){
            System.out.print(s+" ");
        }
        System.out.println();

        //Me da mucha más pereza compararlos
        candidatosInf.clear();
        candidatosSup.clear();
        solucion.clear();

        System.out.print("Posiciones raras: ");
        solucion.add(kElemento(indice-d/2,vector));
        solucion.add(kElemento(indice+d/2,vector));

        for(int s: solucion){
            System.out.print(s+" ");
        }
        System.out.println();

        //Estos ya salen directamente
    }

    public static int kElemento(int indice, List<Integer> list){

        int pivote = list.get(list.size()/2);
        List<Integer> menores = new ArrayList<>();
        List<Integer> mayores = new ArrayList<>();
        List<Integer> iguales = new ArrayList<>();

        //Organizamos
        for(int num: list){
            if(num < pivote){
                menores.add(num);
            }else if(num > pivote){
                mayores.add(num);
            }else{
                iguales.add(num);
            }
        }

        //Elegimos la lista en la que esté la mediana

        if(menores.size() > indice){
            //El índice sigue siendo el mismo porque la mediana esta en la misma posicion de menores que en la lista de llegada
            return kElemento(indice,menores);

        }else if(menores.size()+iguales.size() > indice){
            //Lo hemos encontrado
            return pivote;
        }else{
            //La mediana está en el conjunto de mayores. Ajustamos la posicion de la mediana relativamente en la lista de mayores
            return kElemento(indice - menores.size() - iguales.size(),mayores);
        }
    }

}
