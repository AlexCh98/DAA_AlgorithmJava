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
        int k=2;
        int indice = (vector.size()-1)/2; //En este caso la mediana
        int mediana = kElemento(indice, vector);
        System.out.println("Mediana: " + mediana);

        System.out.println("2 más cercanos: ");
        List<Integer> candidatos = new ArrayList<>();
        for(int i=0; i<k; i++){
            candidatos.add(kElemento(mediana-i,vector));
            candidatos.add(kElemento(mediana+i,vector));
        }
        //Me da mucha pereza compararlos
        candidatos.clear();

        System.out.println("2 más lejanos");
        for(int i=0; i<k; i++){
            candidatos.add(kElemento(mediana-i,vector));
            candidatos.add(kElemento(mediana+i,vector));
        }
        //Me da mucha más pereza compararlos
        candidatos.clear();

        System.out.println("Posiciones raras");
        candidatos.add(kElemento(mediana-k/2,vector));
        candidatos.add(kElemento(mediana+k/2,vector));
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
            }else if(num> pivote){
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
            return kElemento(indice - (menores.size() + iguales.size()),mayores);
        }
    }

}
