package Alex.AlgoritmosDyV;
import java.util.Scanner;

/*LA BUSQUEDA BINARIA ES EL ALGORITMO MÁS RÁPIDO PARA UN ARRAY ORDENADO, SINO HABRÍA QUE ORDENARLO
A LA ANTIGUA*/

public class DarsoulsBusquedaBinaria {

    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        int numEnemigos = scanner.nextInt();
        //String[] nivelesEnemigos = scanner.nextLine().split(" ");
        int[] arrayNiveles = new int[numEnemigos];
        long[] acumulacionNivel = new long[numEnemigos];
        for(int i=0;i<numEnemigos;i++){
            arrayNiveles[i]= scanner.nextInt();
            if(i==0){
                acumulacionNivel[0] = arrayNiveles[0];
            }else {
                acumulacionNivel[i] = acumulacionNivel[i - 1] + arrayNiveles[i];
            }
        }
        int numCasosPrueba = scanner.nextInt();
        for(int i = 0;i<numCasosPrueba;i++){
            int nivelCaballero = scanner.nextInt();
            //System.out.println("Nivel Caballero:"+nivelCaballero);
            int posicionSalida = (int) busquedaBinaria(arrayNiveles, nivelCaballero);
            //System.out.println("Posicion Salida:"+(posicionSalida+1));

            if(posicionSalida==-1){
                System.out.println(0+" "+0);
            }else{
                System.out.println((posicionSalida+1)+" "+acumulacionNivel[posicionSalida]);
            }
        }
    }

   public static int busquedaBinaria(int[] valores, int valorBuscado) {
        int encontrado = -1;
        int primero = 0;
        int ultimo = valores.length - 1;
        if(valorBuscado < valores[0]){
            return -1;
        }else if(valorBuscado>valores[ultimo]){
            return ultimo;
        }else{
            while ((primero <= ultimo)) {

                int medio = (primero + ultimo) / 2;
                if (valores[medio] == valorBuscado) {

                    return medio;
                } else if (valores[medio] < valorBuscado) {

                    primero = medio + 1;

                } else {

                    ultimo = medio - 1;
                }
            }
            return ultimo;
        }
   }


   /* public static int[] partida(int[] vec,int nivel){
        int i=0;
        int contador = 0;
        int puntos = 0;
        while (i<vec.length){
            if(nivel>=vec[i]){
                puntos+=vec[i];
                contador++;
            }else{
                i=vec.length;
            }
            i++;
        }
        return new int[]{contador,puntos};
    }*/


}