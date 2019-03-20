package Jose;

import java.math.BigInteger;
import java.util.Random;

public class Karatsuba {
    public static void main(String[] Args) {
        Random rnd = new Random(System.nanoTime());
        int numero_de_cifras = 100000;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numero_de_cifras; i++) {
            sb.append(rnd.nextInt(9) + 1);
        }
        BigInteger x = new BigInteger(sb.toString());
        BigInteger y = new BigInteger(sb.reverse().toString());
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        long time_start = System.nanoTime();
        x.multiply(y);
        long time_end = System.nanoTime();
        System.out.println("He tardado " + (time_end - time_start) / Math.pow(10, 9) + " segundos dos numeros de " + numero_de_cifras + " cifras con el algoritmo de BigInteger");
        int umbral = (int) Math.pow(2, 21);
        time_start = System.nanoTime();
        karatsuba2(x, y, umbral);
        time_end = System.nanoTime();
        System.out.println("He tardado " + (time_end - time_start) / Math.pow(10, 9) + " segundos dos numeros de " + numero_de_cifras + " cifras con el algoritmo de karatsuba(base 2) con umbral " + umbral);

    }

    public static BigInteger karatsuba2(BigInteger x, BigInteger y, int umbral) {
        int N = Math.max(x.bitLength(), y.bitLength());
        if (N <= umbral) return x.multiply(y);
        N = (N / 2) + (N % 2);
        BigInteger b = x.shiftRight(N);
        BigInteger a = x.subtract(b.shiftLeft(N));
        BigInteger d = y.shiftRight(N);
        BigInteger c = y.subtract(d.shiftLeft(N));
        BigInteger ac = karatsuba2(a, c, umbral);
        BigInteger bd = karatsuba2(b, d, umbral);
        BigInteger abcd = karatsuba2(a.add(b), c.add(d), umbral);

        return ac.add(abcd.subtract(ac).subtract(bd).shiftLeft(N)).add(bd.shiftLeft(2 * N));
    }

    public static BigInteger karatsuba_10(BigInteger x, BigInteger y) {
        if (x.compareTo(BigInteger.TEN) < 0 || y.compareTo(BigInteger.TEN) < 0) {
            return x.multiply(y);
        }
        int n = Math.max(x.toString().length(), y.toString().length());
        int m = n / 2 + n % 2;

        BigInteger[] a_b = x.divideAndRemainder(BigInteger.valueOf(10).pow(m));
        BigInteger a = a_b[0];
        BigInteger b = a_b[1];
        BigInteger[] c_d = y.divideAndRemainder(BigInteger.valueOf(10).pow(m));
        BigInteger c = c_d[0];
        BigInteger d = c_d[1];

        BigInteger step1 = karatsuba_10(a, c);
        BigInteger step2 = karatsuba_10(b, d);
        BigInteger step3 = karatsuba_10(a.add(b), c.add(d));
        BigInteger step4 = step3.subtract(step2).subtract(step1);
        return step1.multiply(BigInteger.valueOf(10).pow(m * 2)).add(step2)
                .add(step4.multiply(BigInteger.valueOf(10).pow(m)));
    }
}
