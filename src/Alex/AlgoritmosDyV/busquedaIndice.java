package Alex.AlgoritmosDyV;

public class busquedaIndice {
    public static void main(String[] args){
        int v[] = {-3, -2, -1, 0, 1, 5};
        int a[] = {-5, 0, 2, 5, 6, 7, 8};
        int found = busqueda(v);
        int found1 = busqueda(a);
        System.out.println(found);
        System.out.println(found1);
    }

    private static int busqueda(int[] a) {
        return semiBuquedaBinaria(a,0,a.length);
    }

    private static int semiBuquedaBinaria(int[] vector, int inicio, int fin) {
        if(inicio > fin)
            return -1;
        int mitad = (inicio+fin)/2;
        if(mitad == vector[mitad]){
            return mitad;
        }else if(mitad < vector[mitad]){
            return semiBuquedaBinaria(vector,inicio,mitad-1);
        }else{
            return semiBuquedaBinaria(vector,mitad+1,fin);
        }
    }
}
