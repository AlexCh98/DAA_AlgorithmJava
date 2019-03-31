package poo;


import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Skyline {

    public static class Edificio{
        public ArrayList<int[]> pisos;

        public Edificio(){
            this.pisos = new ArrayList<>();
        }

        public StringBuilder pasarString(){
            StringBuilder cad = new StringBuilder();
            for(int[] p: this.pisos){
                cad.append(p[0]+" "+p[1]+" ");
            }
            return cad;
        }
    }

    public static void main (String[] args) throws IOException{

        //Entrada
        FileReader f = new FileReader("entradaSky");
        Scanner scan = new Scanner(f);
        int numEdificios = scan.nextInt();

        List<Edificio> edificios = new ArrayList<>(numEdificios);

        //Rellenar edificios
        for(int i=0; i<numEdificios; i++){
            int[] inicio = {scan.nextInt(), scan.nextInt()};
            int[] fin = {scan.nextInt(),0};
            Edificio edificio = new Edificio();
            edificio.pisos.add(inicio);
            edificio.pisos.add(fin);
            edificios.add(edificio);
        }

        /*System.out.println("Muestro edificios:");
        for(Edificio e: edificios) {
            System.out.println(e.pasarString());
        }*/

        Edificio solucion = fusionarEdificios(edificios);

        //System.out.println("\nMuestro solución:");
        System.out.println(solucion.pasarString());
    }

    public static Edificio fusionarEdificios(List<Edificio> edificios){
        if(edificios.size() == 1){
            return edificios.get(0);
        }else{
            int n = edificios.size()/2;

            //Dividir
            Edificio bloque1 = fusionarEdificios(edificios.subList(0,n));
            Edificio bloque2 = fusionarEdificios(edificios.subList(n,edificios.size()));

            //Vencer
            //System.out.println("Fusion");
            return crearBloque(bloque1,bloque2);
        }
    }

    public static Edificio crearBloque(Edificio bloque1, Edificio bloque2){

        Edificio bloqueFinal = new Edificio();

        int long1 = bloque1.pisos.size();
        int long2 = bloque2.pisos.size();

        bloqueFinal.pisos = new ArrayList<>(long1+long2);

        int punt1 = 0; //Puntero a bloque 1
        int punt2 = 0; //Puntero a bloque 2
        int altura1 = 0; //Para guardar la altura actual del bloque 1
        int altura2 = 0; //Para guardar la altura actual del bloque 2

        int ultAltura = 0; //Para guardar un vértice sólo si cambia de altura

        while(punt1 < long1 && punt2 < long2){
            int[] ed1 = bloque1.pisos.get(punt1); //Punto que se va a meter en el bloqueFinal {x,y}
            int[] ed2 = bloque2.pisos.get(punt2); //Punto que se va a meter en el bloqueFinal {x,y}


            //CASO 1--------------------------------------------------------------------------
            if(ed1[0] < ed2[0]){ //Si el punto del bloque 1 está antes que el del bloque 2

                altura1 = ed1[1]; //Guardo la nueva altura

                if(altura1 > altura2){ //Si el nuevo punto sobrepasa al otro bloque guardamos el punto original y actualizo la altura del bloque1
                    bloqueFinal.pisos.add(ed1);
                    ultAltura = altura1; //Actualizo la nueva altura

                }else if(altura2 != ultAltura){ //Si es más bajo lo guardamos con la altura del otro bloque, lo de alt2!=ultAltura es para no guardar vértices inncecesarios
                    bloqueFinal.pisos.add(new int[]{ed1[0],altura2});
                    ultAltura = altura2;
                }

                punt1++; //Pasamos al siguiente punto del bloque 1


                //CASO 2--------------------------------------------------------------------------
            }else if(ed2[0] < ed1[0]){ //Si el punto del bloque 2 está antes que el del bloque 1

                altura2 = ed2[1]; //Guardo la nueva altura

                if(altura2 > altura1){ //Si el nuevo punto sobrepasa al otro bloque guardamos el punto original y actualizo la altura del bloque1
                    bloqueFinal.pisos.add(ed2);
                    ultAltura = altura2; //Actualizo la nueva altura

                }else if(altura1 != ultAltura){ //Si es más bajo lo guardamos con la altura del otro bloque
                    bloqueFinal.pisos.add(new int[]{ed2[0],altura1});
                    ultAltura = altura1;
                }

                punt2++; //Pasamos al siguiente punto del bloque 2

                //CASO 3--------------------------------------------------------------------------
            }else if(ed1[0] == ed2[0]){ //Si empiezan en el mismo punto
                altura1 = ed1[1];
                altura2 = ed2[1];

                if(altura1 >= altura2 && altura1 != ultAltura){ //Si la altura1 es mayor o igual que la 2 nos quedamos con el edificio1
                    bloqueFinal.pisos.add(ed1);
                    ultAltura = altura1;
                }else if(altura2 != ultAltura){
                    bloqueFinal.pisos.add(ed2);
                    ultAltura = altura2;
                }
                punt1++;
                punt2++;
            }

        }

        if(punt1 == long1){
            altura1 = 0;
            for(int i=punt2; i<long2; i++){
                bloqueFinal.pisos.add(bloque2.pisos.get(i));
            }
        }else{
            altura2 = 0;
            for(int i=punt1; i<long1; i++){
                bloqueFinal.pisos.add(bloque1.pisos.get(i));
            }
        }


        return bloqueFinal;
    }
}
