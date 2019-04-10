package Ivan;

import java.util.Scanner;

public class Triminos {
    private static int [][] tablero;
    private static int color=2;
    public Triminos(int tamaño,int inif, int finf, int inic,
                            int finc , int x, int y){
        if(((Math.pow((tamaño), 2))-1)%3==0){
            tablero=new int[tamaño][tamaño];
            // Ponemos en el punto x, y la casilla ocupada.
            tablero[x][y]=1;
        }
        else{
            System.out.println("no se puede crear el tablero");
        }
    }

    public static void rellenar(int tamaño, int inif, int finf, int inic,
                                int finc, int coordX, int coordY){


        if(tamaño==2){
            for(int i=inif; i<=(finf);i++){
                for(int j=inic;j<=(finc);j++){
                    if(tablero[i][j]==0){
                        tablero[i][j]=color;
                    }
                }
            }
            color++;
        }
        else{
            int coordenadaMitadX=(finf+inif)/2;
            int coordenadaMitadY=(finc+inic)/2;
            int coord1x = 0, coord1y = 0;
            int coord2x = 0, coord2y = 0;
            int coord3x=0, coord3y=0;
            int coord4x=0, coord4y=0;

            if((coordX<=coordenadaMitadX) && (coordY<=coordenadaMitadY)){

                //Rellenamos las cuadriculas del centro
                tablero[coordenadaMitadX][coordenadaMitadY+1]=color;
                tablero[(coordenadaMitadX)+1][coordenadaMitadY]=color;
                tablero[coordenadaMitadX+1][coordenadaMitadY+1]=color;
                color++;

                //Llamada recursiva
                coord1x=coordX; coord1y=coordY;
                coord2x=coordenadaMitadX; coord2y=coordenadaMitadY+1;
                coord3x=(coordenadaMitadX)+1; coord3y=coordenadaMitadY;
                coord4x=coordenadaMitadX+1; coord4y=coordenadaMitadY+1;


            }
            else if((coordX<=coordenadaMitadX) && (coordY>coordenadaMitadY)){


                //Rellenamos las cuadriculas del centro
                tablero[(coordenadaMitadX)][coordenadaMitadY]=color;
                tablero[(coordenadaMitadX)+1][coordenadaMitadY]=color;
                tablero[(coordenadaMitadX)+1][coordenadaMitadY+1]=color;
                color++;
                //Llamada recursiva
                coord1x=coordenadaMitadX; coord1y=coordenadaMitadY;
                coord2x=coordX; coord2y=coordY;
                coord3x=(coordenadaMitadX)+1; coord3y=coordenadaMitadY;
                coord4x=coordenadaMitadX+1; coord4y=coordenadaMitadY+1;


            }
            else if((coordX>coordenadaMitadX) && (coordY<=coordenadaMitadY)){

                //Rellenamos las cuadriculas del centro
                tablero[(coordenadaMitadX)][coordenadaMitadY]=color;
                tablero[coordenadaMitadX][coordenadaMitadY+1]=color;
                tablero[coordenadaMitadX+1][coordenadaMitadY+1]=color;
                color++;
                //Llamada recursiva
                coord1x=coordenadaMitadX; coord1y=coordenadaMitadY;
                coord2x=coordenadaMitadX; coord2y=coordenadaMitadY+1;
                coord3x=coordX; coord3y=coordY;
                coord4x=coordenadaMitadX+1; coord4y=coordenadaMitadY+1;

            }
            else if((coordX>coordenadaMitadX) && (coordY>coordenadaMitadY)){

                //Rellenamos las cuadriculas del centro
                tablero[(coordenadaMitadX)][coordenadaMitadY]=color;
                tablero[coordenadaMitadX][coordenadaMitadY+1]=color;
                tablero[coordenadaMitadX+1][coordenadaMitadY]=color;
                color++;
                //Llamada recursiva
                coord1x=coordenadaMitadX; coord1y=coordenadaMitadY;
                coord2x=coordenadaMitadX; coord2y=coordenadaMitadY+1;
                coord3x=(coordenadaMitadX)+1; coord3y=coordenadaMitadY;
                coord4x=coordX; coord4y=coordY;
            }
            rellenar(tamaño/2,inif, coordenadaMitadX,inic,coordenadaMitadY, coord1x,coord1y);
            rellenar(tamaño/2,inif,coordenadaMitadX,coordenadaMitadY+1, finc,coord2x,coord2y);
            rellenar(tamaño/2,coordenadaMitadX+1,finf, inic, coordenadaMitadY,coord3x,coord3y);
            rellenar(tamaño/2,coordenadaMitadX+1, finf, coordenadaMitadY+1, finc,coord4x,coord4y);


        }
    }


    public void print() {

        for (int i=0; i<tablero.length; i++) {
            for (int j=0; j<tablero[i].length; j++)
                System.out.print(tablero[i][j] + "\t");
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Introduzca una potencia de 2");
        int tamaño = scanner.nextInt();
        int finf=tamaño-1;
        int x=5;
        int y=6;
        Triminos trimino=new Triminos(tamaño,0,finf,0,finf,x,y);
        trimino.rellenar(tamaño,0,finf,0,finf,x,y);
        trimino.print();

    }

}