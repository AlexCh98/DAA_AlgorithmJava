package Alex.EjerciciosProblemasClase;

import java.io.IOException;
import java.util.Scanner;

public class HelloWorld {

    public static void main(String[] args) throws IOException {
        int i;
        Scanner reader = new Scanner(System.in);
        int num = reader.nextInt();
        for (i=0;i<num;i++){
            System.out.println("Hello World!");

        }

    }
}
