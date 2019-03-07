package Ivan;

import java.util.*;

public class DarkSoulsObjetos {

    public static class Objeto {
        String nombre;
        int peso;
        double defensa;

        public Objeto(String nombre, int peso, double defensa) {
            this.nombre = nombre;
            this.peso = peso;
            this.defensa = defensa;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Objeto objeto = (Objeto) o;
            return peso == objeto.peso &&
                    Double.compare(objeto.defensa, defensa) == 0 &&
                    nombre.equals(objeto.nombre);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nombre, peso, defensa);
        }
    }

    private static Objeto getBestItem(ArrayList<Objeto> listaobjetos) {
        double bestRatio = 0;
        Objeto bestItem = null;
        for (Objeto listaobjeto : listaobjetos) {
            double r = listaobjeto.defensa / listaobjeto.peso;
            if (r > bestRatio) {
                bestRatio = r;
                bestItem = listaobjeto;
            }
        }
        return bestItem;
    }


    public static double greedyAlgorithmKS(ArrayList<Objeto> listaobjetos, double pesomax,TreeSet<String> nombres ) {
        ArrayList<Objeto> copiaobjetos = new ArrayList<>(listaobjetos);
        double freeWeight = pesomax;
        double sol = 0;
        boolean isSol = false;
        while (!isSol && !copiaobjetos.isEmpty()) {
            Objeto bestItem = getBestItem(copiaobjetos);
            if (freeWeight >= bestItem.peso) {
                sol = sol + bestItem.defensa;
                freeWeight -= bestItem.peso;
                nombres.add(bestItem.nombre);
            } else{
                double porcentaje = freeWeight/bestItem.peso;
                sol += porcentaje*bestItem.defensa ;
                nombres.add(bestItem.nombre);
                isSol = true;
            }
            copiaobjetos.remove(bestItem);
        }
        return sol;
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int piezas = Integer.parseInt(reader.nextLine());
        int pesomax = Integer.parseInt(reader.nextLine());
        double pesoMaximo = 0;
        TreeSet<String> nombres = new TreeSet<>();
        String tipoPersonaje = reader.nextLine();
        ArrayList<Objeto> listaobjetos = new ArrayList<>();
        for (int i = 0; i < piezas; i++) {
            String[] nombre = reader.nextLine().split (" ");
            listaobjetos.add(new Objeto(nombre[0],Integer.parseInt(nombre[1]),Integer.parseInt(nombre[2])));
        }
        switch (tipoPersonaje.toLowerCase()){
            case "ligero":
                pesoMaximo = pesomax*0.5;
                break;
            case "medio":
                pesoMaximo = pesomax*0.75;
                break;
            case "pesado":
                pesoMaximo = pesomax;
                break;
        }
        double sol = greedyAlgorithmKS(listaobjetos,pesoMaximo,nombres);
        System.out.printf("%.2f \n",sol);
        StringBuilder sb = new StringBuilder();
        for(String nombre: nombres){
            sb.append(nombre).append("\n");
        }
        System.out.println(sb);
    }
}
