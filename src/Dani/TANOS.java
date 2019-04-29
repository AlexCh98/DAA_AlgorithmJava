package Dani;

public class TANOS {
    public static void main(String[] args) {
        boolean [][]grafo = new boolean[11][11];
        int[]solucion = new int [grafo.length+1];//porque el inicio y el fin son los mismos
        boolean visitados[] = new boolean[grafo.length+1];
        int[]numSoluciones = new int[1];
        grafo [7][8] = true;
        grafo [8][7] = true;

        grafo [6][7] = true;
        grafo [7][6] = true;

        grafo [9][1] = true;
        grafo [1][9] = true;

        grafo [8][0] = true;
        grafo [0][8] = true;

        grafo [5][10] = true;
        grafo [10][5] = true;

        grafo [10][9] = true;
        grafo [9][10] = true;

        grafo [3][0] = true;
        grafo [0][3] = true;

        grafo [4][0] = true;
        grafo [0][4] = true;

        grafo [5][0] = true;
        grafo [0][5] = true;


        grafo [3][1] = true;
        grafo [1][3] = true;

        grafo [1][4] = true;
        grafo [4][1] = true;

        grafo [5][1] = true;
        grafo [1][5] = true;

        grafo [3][2] = true;
        grafo [2][3] = true;

        grafo [2][4] = true;
        grafo [4][2] = true;

        grafo [2][5] = true;
        grafo [5][2] = true;

        grafo [3][6] = true;
        grafo [6][3] = true;

        visitados[0]= true;
        solucion[0] = 0;

        ciclosTodasSoluciones(grafo,1,solucion,visitados,numSoluciones);
        System.out.println();
        System.out.println("Hay "+numSoluciones[0]+" posibles rutas");

    }

    public static void ciclosTodasSoluciones (boolean g[][], int etapa, int sol[], boolean visitados [],int numSoluciones[]) {

        for (int intento=0; intento<g.length; intento++) {

            if (esFactible(g,etapa,sol,visitados,intento)) {
                sol[etapa]=intento;
                visitados[intento]=true;
                if (etapa==sol.length-1) {
                    imprimir(sol);
                    numSoluciones[0]+=1;
                }else {
                    ciclosTodasSoluciones (g,etapa+1,sol,visitados,numSoluciones);
                }

                sol[etapa]=0;
                if(intento != 0) {
                    visitados[intento] = false;
                }

            }
        }
    }

    public static void imprimir (int sol[]) {
        for (int i=0; i<sol.length; i++) {
            System.out.print ((sol[i])+" ");
        }
        System.out.println ();
    }

    public static boolean esFactible(boolean[][]grafo,int etapa,int[]solucion,boolean[]visitados,int intento){
        int origen = solucion[etapa-1];
        int destino = intento;

        if(grafo[origen][destino]== false){ //para comprobar que esten conectados
            return false;
        }


        if((visitados[destino]) && (etapa< solucion.length-1)){// para evitar ciclos comprubas que no este visitado tu siguiente nodo
            return false;
        }

        if((etapa  == solucion.length-1) && (destino!= solucion[0])){ // para asegurar que la ultima posicion de aolucion tiene el origen
            return false;
        }



        return true;
    }
}
