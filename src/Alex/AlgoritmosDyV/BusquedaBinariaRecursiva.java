package Alex.AlgoritmosDyV;

public class BusquedaBinariaRecursiva {
    private static int valorBuscado = -2;

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
        int i = 5;
        if (i > array[array.length - 1] || i < array[0]) {
            System.out.println(false);
        } else {
            System.out.println(busquedaBinaria(array, 5));
            System.out.println(busquedaBinariaInt(array, 4));
        }
    }
   /* public static void main(String args[]){
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
        for(int i = 0;i<numCasosPrueba;i++) {
            int nivelCaballero = scanner.nextInt();
            //System.out.println("Nivel Caballero:"+nivelCaballero);
            int posicionSalida = -1;
            if (nivelCaballero > arrayNiveles[arrayNiveles.length - 1]) {
                //System.out.println("ME SALGO");
                posicionSalida = arrayNiveles.length - 1;
                System.out.println((posicionSalida + 1) + " " + acumulacionNivel[posicionSalida]);
            } else if (nivelCaballero < arrayNiveles[0]) {
                System.out.println(0 + " " + 0);

            } else {
                if (busquedaBinaria(arrayNiveles, nivelCaballero)) {
                    posicionSalida = valorBuscado;
                    System.out.println((posicionSalida + 1) + " " + acumulacionNivel[posicionSalida]);
                } else {
                    System.out.println("NO ESTA");
                    System.out.println(posicionSalida);
                }
                //System.out.println("Posicion Salida:"+(posicionSalida+1));

                valorBuscado = -1;
            }
        }*/
    //}

    private static boolean busquedaBinaria(int[] array,int i){
        return busquedaBinaria(array,0,array.length-1,i);
    }
    private static boolean busquedaBinaria(int[] array, int inicio, int fin, int i) {
        if(inicio > fin){
            return false;
        }
        int medio = (fin+inicio)/2;
        if(i == array[medio]){
            valorBuscado = medio;
            return true;
        }else{
            if(i > array[medio]){
                return busquedaBinaria(array, medio+1,fin,i);
            }else{
                return busquedaBinaria(array,inicio,medio-1,i);
            }
        }
    }
    private static int busquedaBinariaInt(int[] array,int i){
        return busquedaBinariaInt(array,0,array.length-1,i);
    }
    private static int busquedaBinariaInt(int[] array, int inicio, int fin, int i) {
        if(inicio > fin){
            return -inicio-1;
        }
        int medio = (fin+inicio)/2;
        if(i == array[medio]){
            return medio;
        }else{
            if(i > array[medio]){
                return busquedaBinariaInt(array, medio+1,fin,i);
            }else{
                return busquedaBinariaInt(array,inicio,medio-1,i);
            }
        }
    }
}
