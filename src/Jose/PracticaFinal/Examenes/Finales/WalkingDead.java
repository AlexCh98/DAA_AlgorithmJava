package Examenes.Finales;

import java.util.HashSet;
import java.util.Set;

public class WalkingDead {
    public static class Datos{
        int [] pesos;
        int [] valores;

    }

    public static class Mochila{
        Set<Integer> objetos;
        int valorActual;
        int mejorValor;
        int peso;
        int pesoMaximo;
    }
    public static void main(String [] args){
        Datos datos = new Datos();
        datos.pesos = new int[]{46,38,15,37,17,9,20,50};
        datos.valores = new int[]{77, 19, 78, 86, 84, 17, 29, 70};

        Mochila mochilaRick = new Mochila();
        mochilaRick.objetos = new HashSet<>();
        mochilaRick.valorActual = 0;
        mochilaRick.mejorValor = 0;
        mochilaRick.peso = 0;
        mochilaRick.pesoMaximo = 60;

        Mochila mochilaCarl = new Mochila();
        mochilaCarl.objetos = new HashSet<>();
        mochilaCarl.valorActual = 0;
        mochilaCarl.mejorValor = 0;
        mochilaCarl.peso = 0;
        mochilaCarl.pesoMaximo = 20;

        CalcularReparto(mochilaRick, mochilaCarl, datos, 0);
        System.out.println((mochilaRick.mejorValor + mochilaCarl.mejorValor));
    }

    private static void CalcularReparto(Mochila mochilaRick, Mochila mochilaCarl, Datos datos, int objetoActual){
        if(objetoActual == datos.pesos.length){
            int valorActual = mochilaCarl.valorActual + mochilaRick.valorActual;
            int mejorValor = mochilaCarl.mejorValor + mochilaRick.mejorValor;
            if(valorActual > mejorValor){
                mochilaCarl.mejorValor = mochilaCarl.valorActual;
                mochilaRick.mejorValor = mochilaRick.valorActual;
            }
        }else{
            if(mochilaRick.peso + datos.pesos[objetoActual] <= mochilaRick.pesoMaximo){
                mochilaRick.peso += datos.pesos[objetoActual];
                mochilaRick.valorActual += datos.valores[objetoActual];
                mochilaRick.objetos.add(objetoActual);
                CalcularReparto(mochilaRick, mochilaCarl, datos, objetoActual + 1);
                mochilaRick.objetos.remove(objetoActual);
                mochilaRick.valorActual -= datos.valores[objetoActual];
                mochilaRick.peso -= datos.pesos[objetoActual];
            }
            if(mochilaCarl.peso + datos.pesos[objetoActual] <= mochilaCarl.pesoMaximo){
                mochilaCarl.peso += datos.pesos[objetoActual];
                mochilaCarl.valorActual += datos.valores[objetoActual];
                mochilaCarl.objetos.add(objetoActual);
                CalcularReparto(mochilaRick, mochilaCarl, datos, objetoActual + 1);
                mochilaCarl.objetos.remove(objetoActual);
                mochilaCarl.valorActual -= datos.valores[objetoActual];
                mochilaCarl.peso -= datos.pesos[objetoActual];
            }
            CalcularReparto(mochilaRick, mochilaCarl, datos, objetoActual + 1);
        }
    }
}
