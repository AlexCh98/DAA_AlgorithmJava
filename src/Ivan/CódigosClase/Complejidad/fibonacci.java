package Ivan.CódigosClase.Complejidad;

public class fibonacci {
	public static long llamadas=0;
	
	public static void main(String[] asdf) {
		int min=1, step=1, max=50;
		long fib=0;
		long total = -System.currentTimeMillis();
		for(int i=min; i<=max; i+=step) {
			llamadas=0;
			long time = -System.currentTimeMillis();
			//fib = recFib(i);
			//fib = recMemFib(i);
			fib = iterFib(i);
			time += System.currentTimeMillis();
			System.out.printf("Fibonaci(%d): %d (ms: %d, llamadas: %d)\n", i, fib, time, llamadas);
		}
		total += System.currentTimeMillis();
		System.out.printf("Tiempo total: %d ms\n", total);
		
	}
	//Algoritmo recursivo
	//  Cota superior: O(2^n)
	//  Cota ajustada (inf/sup): 1.6^2
	//  Memoria: O(n) la altura de la pila de llamadas recursivas
	public static long recFib(int n){
		llamadas++;
		if(n<3)
			return 1;
		return recFib(n-1)+recFib(n-2);
	}
	
	//Algoritmo con memorizacion
	//  Tiempo: O(n)
	//  Memoria: O(n) los elementos de la tabla y la altura de la pila del sistema
	public static long recMemFib(int n){
		long tabla[] = new long[n+1];
		for(int i=0; i<=n; i++) {
			tabla[i]=-1;
		}
		return auxMemFib(n, tabla);
	}
	
	public static long auxMemFib(int n, long[] tabla){
		llamadas++;
		if(tabla[n]==-1)
			if(n>=3)
				tabla[n] = auxMemFib(n-1, tabla)+auxMemFib(n-2,tabla);
			else
				tabla[n]=1;
		return tabla[n];
	}
	
	//Equivalente en programacion dinámica
	//  En cada iteracion no se mantiene toda la tabla, solo
	//la informacion estrictamente necesaria.
	//  Tiempo: O(n)
	//  Memoria: O(1)
	public static long iterFib(int n) {
		long ant1=1, ant2=1;
		long fib = 1;
		for(int i=3; i<=n; i++) {
			llamadas++;//iteraciones
			fib = ant1 + ant2;
			ant2=ant1;
			ant1=fib;
		}
			
		return fib;
	}

}
