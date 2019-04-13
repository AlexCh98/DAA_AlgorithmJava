package Ivan.CódigosClase.Complejidad;

import java.util.Random;

public class codigos {

	
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		int n = 10;
		int v[] = generarAleatorio(n);
		char c[] = generarAleatorioCaracteres(n);
		bubbleSort(v);
		suma(v);
		suma_digitos(n);
		char[] s = "aaaccab".toCharArray();
		zip(s);
	}

	private static int[] generarAleatorio(int n) {
		int v[] = new int[n];
		Random random = new Random(10);
		for (int i = 0; i < n; i++)
			v[i] = random.nextInt(10 * n);
		return v;
	}

	private static char[] generarAleatorioCaracteres(int n) {
		char v[] = new char[n];
		Random random = new Random(10);
		for (int i = 0; i < n; i++)
			v[i] = (char) ('a' + random.nextInt(26));
		return v;
	}

	public static void bubbleSort(int v[]) {
		for (int i = 0; i < v.length - 1; i++)
			for (int j = v.length - 1; j > i; j--)
				if (v[j] < v[j - 1]) {
					int aux = v[j];
					v[j] = v[j - 1];
					v[j - 1] = aux;
				}
	}

	public static int suma(int v[]) {
		int s = 0;
		for (int i = 0; i < v.length; i++) {
			s += v[i];
		}
		return s;
	}

	public static void zip(char v[]) {
		for (int i = 0; i < v.length; i++) {
			char element = v[i];
			int contador = 1;
			for (; i < v.length - 1; i++) {
				if (v[i] == v[i + 1])
					contador++;
				else
					break;
			}
			System.out.printf("(%c,%d) ", element, contador);
		}
	}

	public static int suma_digitos(int n) {
		int s = 0;
		while (n > 0) {
			int dig = n % 10;
			s += dig;
			n = n / 10;
		}
		return s;
	}

}
