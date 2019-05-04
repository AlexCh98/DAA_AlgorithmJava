package Alex.AlgoritmosDyV;

public class Karatsuba {
    /*  Algoritmo de Divide y Vencerás utilizado para multiplicar enteros grandes.
        Tiene una complejidad de O(n^log_2 3)
     */
    public static void main(String[] args){
        int x = 123;
        int y = 100;
       // int multiplicacion = karatsuba(x,y,1);
    }

    /*private static int karatsuba(int x, int y, int n) {
        if(esSuficienteSimple(n)){
            return x*y;
        }else {
            int lado = x / 2;
            int l2 = y / 2;
            int s1 = karatsuba(x1, y1, n / 2);
            int s2 = karatsuba(x1, y2, n / 2);
            int s3 = karatsuba(x2, y1, n / 2);
            int s4 = karatsuba(x2, y2, n / 2);
            int aux = suma(s2, s3);
            s1 = desplazarIzq(s1, n);
            aux = desplazarIzq(aux, n / 2);
            int z = suma(s1, aux);
            z = suma(z, s4);
            return z;
        }
    }*/
}
