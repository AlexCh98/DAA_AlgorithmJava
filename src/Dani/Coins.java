package poo;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Coins {

    public static void main(String[] args) throws IOException{

        //FileReader f = new FileReader("entradaCoin");
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(in);

        int[] sistMonetario = {500,200,100,50,20,10,5,2,1};

        Map<Integer,Long> monedasDisponibles = new HashMap<>();

        long numCasos = Long.parseLong(input.readLine());

        String[] partes = input.readLine().split(" ");
        long maxValor = 0;
        long numMonedasTotal = 0;

        for(int i=0; i<sistMonetario.length; i++){
            long numMonedas = Long.parseLong(partes[i]);
            numMonedasTotal+=numMonedas;
            maxValor += numMonedas*sistMonetario[i];
            monedasDisponibles.put(sistMonetario[i],numMonedas);
        }

        for(long i=0; i<numCasos; i++){
            long aDevolver = Long.parseLong(input.readLine());
            if(aDevolver > maxValor){
                System.out.println("-1");
            }else if(aDevolver == maxValor){
                System.out.println(numMonedasTotal);
            }else if(aDevolver == 0){
                System.out.println("0");
            }else {
                System.out.println(voraz(monedasDisponibles, aDevolver, sistMonetario));
            }
        }
    }

    public static int voraz(Map<Integer,Long> monedasDisponibles, long aDev, int[] sistMonetario){
        long valorActual = 0;
        int n = sistMonetario.length;
        int i=0;
        boolean isSol = false;
        int numMonedas = 0;

        while(i<n && !isSol){
            if(monedasDisponibles.get(sistMonetario[i]) > 0) {
                long mejorNumMonedas = getBestMoneda(monedasDisponibles, i, sistMonetario, aDev, valorActual);

                //System.out.println("Valor de la moneda = " + sistMonetario[i]);
                //System.out.println("MejorNumeroMonedas = " + mejorNumMonedas);


                valorActual += mejorNumMonedas * sistMonetario[i];
                numMonedas += mejorNumMonedas;
                //System.out.println("ValorActual = "+valorActual);
                //System.out.println();
            }
            i++;

            if(valorActual==aDev){
                //System.out.println("Aqui");
                isSol = true;
            }
        }

        if(isSol){
            return numMonedas;
        }else{
            return -1;
        }
    }

    public static long getBestMoneda(Map<Integer,Long> monedasDisponibles,int i,int[] sistMonetario, long aDev, long valorActual){

        long numMonedas = (aDev-valorActual)/sistMonetario[i];
        if(numMonedas > monedasDisponibles.get(sistMonetario[i])){
            return monedasDisponibles.get(sistMonetario[i]);
        }else{
            return numMonedas;
        }
    }

    public static boolean isFactible(long mejorMoneda,long valorActual,long aDev){
        return (mejorMoneda+valorActual <= aDev);
    }
}
