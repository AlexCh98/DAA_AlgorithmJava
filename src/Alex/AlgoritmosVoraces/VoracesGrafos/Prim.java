package Alex.AlgoritmosVoraces;



public class Prim {
    public static class Vertice {
        private String nombre;
        int numVertice;

        public Vertice(String nombre, int numVertice) {
            this.nombre = nombre;
            this.numVertice = numVertice;
        }
        public Vertice(String nombre) {
            this.nombre = nombre;
            numVertice = -1;
        }
        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getNumVertice() {
            return numVertice;
        }

        public void setNumVertice(int numVertice) {
            this.numVertice = numVertice;
        }
        public boolean equals(Vertice n){
            return nombre.equals(n.nombre);
        }
    }

    public static class Grafo{
        public static int INFINITO = 0xFFFF;
        private int[][] matrizPesos;
        private Vertice[] arrayVertices;
        private int numVertices;

        public Grafo(int[][] matrizPesos, Vertice[] vertice, int numVertice) {
            this.matrizPesos = matrizPesos;
            this.arrayVertices = vertice;
            this.numVertices = numVertice;
        }
        public Grafo(int num) {
            this.matrizPesos = new int[num][num];
            this.arrayVertices = new Vertice[num];
            for(int i=0;i<num;i++){
                for(int j=0;j<num;j++){
                    matrizPesos[i][j] = INFINITO;
                }
            }
            this.numVertices = 0;
        }
        public void nuevoVertice(String nom){
            boolean esta = numVertice(nom) >=0;
            if(!esta){
                Vertice v  = new Vertice(nom);
                v.setNumVertice(numVertices);
                arrayVertices[numVertices++] = v;
            }
        }
        /*Devuelve el peso del arco entre los nodos a y b*/
        public int pesoArco(String a, String b){
            int va, vb;
            va = numVertice(a);
            vb = numVertice(b);
            return matrizPesos[va][vb];
        }

        /*Devuelve el numero del vertice*/
        public int getNumVertices(){
            return numVertices;
        }

        /*Devuleve el array de vertices*/
        public Vertice[] getArrayVertices(){
            return arrayVertices;
        }
        /*Crea un nuevo arco*/
        public void nuevoArco(String a,String b, int peso){
            int va = numVertice(a);
            int vb = numVertice(b);
            matrizPesos[va][vb] = peso;
        }

        /*Comprueba que existe el vertice con el nombre pasado*/
        public int numVertice(String nombre){
            Vertice v = new Vertice(nombre);
            boolean encontrado = false;
           int i = 0;
           while (i<numVertices && !encontrado){
                encontrado = arrayVertices[i].equals(v);
                if(!encontrado){
                    i++;
                }
           }
           return (i<numVertices) ? i : -1;
        }

        /*Devuelve la matriz de pesos*/
        public int[][] getMatrizPesos(){
            return matrizPesos;
        }


    }
    public static class AlgoritmoPrim{
        private int[][] pesos;
        private int n;
        private Vertice[] vertices;
        int cont;
        public AlgoritmoPrim(Grafo grafo, Vertice[] vertice){
            n = grafo.getNumVertices();
            pesos = grafo.getMatrizPesos();
            vertices = vertice;
            cont = 0;
        }

        protected int arbolExpansionPrim(){
            int costeMin, menorArista, z;
            int[] coste = new int[n];
            int[] masCercano = new int[n];
            boolean[] visitado = new boolean[n];
            for(int i= 0;i<n;i++)
                visitado[i] = false;
            visitado[0] = true; //Empiezo en el nodo 0;
            costeMin = 0;
            /*Coste de las Aristas Nodo 0*/
            for(int i=1;i<n;i++){
                coste[i] = pesos[0][i];
                masCercano[i] = 0;
            }
            for(int i=1;i<n;i++){
                menorArista = coste[1];
                z=1;
                for(int j=2;j<n;j++){
                    if(coste[j]<menorArista){
                        menorArista = coste[j];
                        z = j;
                    }
                }
                costeMin += menorArista;
                System.out.println((++cont)+" Pasada: Vertice: "+ vertices[masCercano[z]].getNombre()+" -> "+"Vertice: "+vertices[z].getNombre()+" Peso: "+coste[z]);
                visitado[z] = true;
                coste[z] = Grafo.INFINITO;
                /*Ajustamos costes para el resto de vertices*/
                for(int j=1;j<n;j++){
                    if(pesos[z][j] < coste[j] && !visitado[j]){
                        coste[j] = pesos[z][j];
                        masCercano[j] = z;
                    }
                }
            }
            return costeMin;
        }


    }

    public static void main(String[] args){
        int numeroVertices = 7;
        Grafo grafo = new Grafo(numeroVertices);

        /*"Nombre de las Aristas":*/
        String a = "A";
        String b = "B";
        String c = "C";
        String d = "D";
        String e = "E";
        String f = "F";
        String g = "G";

        /*CREAMOS LOS VERTICES*/
        grafo.nuevoVertice(a);
        grafo.nuevoVertice(b);
        grafo.nuevoVertice(c);
        grafo.nuevoVertice(d);
        grafo.nuevoVertice(e);
        grafo.nuevoVertice(f);
        grafo.nuevoVertice(g);

        /*Realizamos los enlaces*/
        grafo.nuevoArco(a, b,7);
        grafo.nuevoArco(b, a,7);
        grafo.nuevoArco(a, d,5);
        grafo.nuevoArco(d, a,5);
        grafo.nuevoArco(b, c,8);
        grafo.nuevoArco(c, b,8);
        grafo.nuevoArco(b, d,9);
        grafo.nuevoArco(d, b,9);
        grafo.nuevoArco(b, e,7);
        grafo.nuevoArco(e, b,7);
        grafo.nuevoArco(e, c,5);
        grafo.nuevoArco(c, e,5);
        grafo.nuevoArco(e, d,3);
        grafo.nuevoArco(d, e,3);
        grafo.nuevoArco(d, f,6);
        grafo.nuevoArco(f, d,6);
        grafo.nuevoArco(e, f,8);
        grafo.nuevoArco(f, e,8);
        grafo.nuevoArco(e, g,9);
        grafo.nuevoArco(g, e,9);
        grafo.nuevoArco(f, g,11);
        grafo.nuevoArco(g, f,11);

        System.out.println("Vertices del Grafo");
        for(int i = 0;i<numeroVertices;i++){
            System.out.print(grafo.getArrayVertices()[i].getNombre()+" ");
        }
        System.out.println();

        AlgoritmoPrim arbol = new AlgoritmoPrim(grafo, grafo.getArrayVertices());
        System.out.println("Costo del arbol minimo:" + arbol.arbolExpansionPrim());
    }
}










/*https://es.slideshare.net/angenio2/programacin-3-algoritmo-de-prim-y-de-kruskal*/