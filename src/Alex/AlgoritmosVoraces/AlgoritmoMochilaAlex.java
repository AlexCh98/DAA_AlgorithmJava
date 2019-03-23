package Alex.AlgoritmosVoraces;



/* ############ PROBLEMA DE LA MOCHILA ##############
 *
 *   -- Se construye una solución iterativamente.
 *   -- Se toma la decisión óptima en cada iteración.
 *   -- Una vez analizado un candidato (introducir o excluir),
 *      no se reconsidera la decisión.
 *   -- Son voraces porque en cada etapa toman la mejor decisión
 *      sin preocuparse de mañana.
 * */

/* Este ejemplo devuelve el peso máximo y el nombre de los objetos que introduce ordenados*/

import java.util.*;

public class AlgoritmoMochilaAlex {
    public static class Objeto{
        String nombre;
        int peso;
        int valor;

        public Objeto(String nombre, int peso, int valor) {
            this.nombre = nombre;
            this.peso = peso;
            this.valor = valor;
        }

        public Objeto(Objeto objeto){
            this.nombre = objeto.nombre;
            this.peso = objeto.peso;
            this.valor = objeto.valor;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Objeto objeto = (Objeto) o;
            return this.peso == objeto.peso && this.valor == objeto.valor && this.nombre.equals(objeto.nombre);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nombre, peso, valor);
        }
    }

    private static Objeto getBestItem(List<Objeto> candidatos) {
        double bestRatio = 0;
        Objeto bestItem = null;
        for (Objeto objeto: candidatos) {
            double r = objeto.valor/ (double) objeto.peso;
            if (r > bestRatio) {    /*Buscamos una r mayor porque hemos puesto valor/peso*/
                bestRatio = r;
                bestItem = new Objeto(objeto);
            }
        }
        return bestItem;
    }

    public static double greedyAlgorithm(ArrayList<Objeto> listaObjetos, double pesoMaximo, TreeSet<String> listaNombres) {
        List<Objeto> candidates = new ArrayList<>(listaObjetos);
        boolean isSol = false;
        double pesoLibre = pesoMaximo;
        double valor = 0;
        while (!isSol && !candidates.isEmpty()) {
            Objeto bestItemIdx = getBestItem(candidates);
            if(bestItemIdx.peso<pesoLibre){
                pesoLibre-=bestItemIdx.peso;
                valor += bestItemIdx.valor;
                listaNombres.add(bestItemIdx.nombre);
            }else{      /*Porque podemos coger parcialmente los objetos*/
                double porcentaje = pesoLibre / bestItemIdx.peso;
                valor += porcentaje*bestItemIdx.valor;
                listaNombres.add(bestItemIdx.nombre);
                isSol = true;
            }
            candidates.remove(bestItemIdx);

        }
        return valor;
    }

    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        int numeroArmaduras = Integer.parseInt(lector.nextLine());
        int pesoMaximoJugador = Integer.parseInt(lector.nextLine());
        String tipoPersonaje= lector.nextLine();
        ArrayList<Objeto> listaObjetos = new ArrayList<>();
        for(int i=0;i<numeroArmaduras;i++){
            String[] infoObjeto = lector.nextLine().split(" ");
            listaObjetos.add(new Objeto(infoObjeto[0],Integer.parseInt(infoObjeto[1]),Integer.parseInt(infoObjeto[2])));
        }
        double pesoMaximo = 0;
        switch (tipoPersonaje){
            case "ligero":
                pesoMaximo = pesoMaximoJugador*0.5;
                break;
            case "medio":
                pesoMaximo = pesoMaximoJugador*0.75;
                break;
            case "pesado":
                pesoMaximo = pesoMaximoJugador;
                break;
        }
        TreeSet<String> nombres = new TreeSet<>();
        double valor = greedyAlgorithm(listaObjetos, pesoMaximo, nombres);
        System.out.printf("%.2f \n",valor);
        StringBuilder sb = new StringBuilder();
        for(String nombre: nombres){
            sb.append(nombre).append("\n");
        }
        System.out.println(sb);
    }
}



