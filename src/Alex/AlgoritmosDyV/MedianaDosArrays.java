package Alex.AlgoritmosDyV;

public class MedianaDosArrays {
    public static void main(String[] asdf) {
        //1,3,4,5,6,7,10

        int v1[] = {1, 5, 6, 7};
        int v2[] = {3, 4, 10};
        System.out.println(encontrarMediana(v1, v2));
        //randomizedTest();
    }

    private static int encontrarMediana(int[] v1, int[] v2) {
        if(v1.length > v2.length){
            return encontrarMediana(v2,v1);
        }
        int min = 0;
        int max = v1.length;
        int elementosMenoresMediana = (v1.length + v2.length +1) / 2;
        int mediana = 0;
        while (min <= max){
            int menor = (min+max) / 2;
            int menor2 = elementosMenoresMediana - menor;
            if(menor < v1.length && menor2 > 0 && v1[menor] < v2[menor2 -1]){
                min = menor + 1;
            }else if(menor > 0 && menor2 < v2.length && v1[menor - 1] > v2[menor2]){
                max = menor;
            }else{
                if(menor == 0){
                    mediana = v2[menor2 - 1];
                }else if(menor2 == 0){
                    mediana = v1[menor - 1];
                }else{
                    mediana = Math.max(v1[menor - 1],v2[menor2 - 1]);
                }
                break;
            }
        }
        return mediana;
    }


}
