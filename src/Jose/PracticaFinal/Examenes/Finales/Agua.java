package Examenes.Finales;

public class Agua {

    public static void main(String[] args) {
        int[] alturas = new int[]{5, 1, 8, 4};
        int[] area = new int[]{2,3, 1, 1};
        System.out.println(calcularVolumen(2, alturas, area));
        int volumen = 11;
        System.out.println(ejecutar(volumen, alturas, area, 8));
    }

    private static int calcularVolumen(int nivel, int [] alturas, int [] area){
        int volumen = 0;
        for (int i = 0; i < area.length; i++) {
            volumen += area[i] * Math.min(nivel, alturas[i]);
        }
        return volumen;
    }
    private static int ejecutar(int volumen, int[] alturas, int [] area, int nivelMaximo){
        if(volumen > calcularVolumen(nivelMaximo, alturas, area)) return nivelMaximo;
        int inicio = 0, fin = nivelMaximo;
        while(fin >= inicio){
            int medio = (inicio + fin)/2;
            int volumenMedio = calcularVolumen(medio, alturas, area);
            if(volumenMedio == volumen) return medio;
            if(volumenMedio > volumen) fin = medio -1;
            else inicio = medio +1;
        }
        return fin;
    }
}
