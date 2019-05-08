package DivideYVenceras;

public class Karatsuba {

    public static void main(String[] args) {
        long x = 12340;
        long y = 56780;
        long result = x * y;
        System.out.println((multiplicar(x, y) == result));
    }

    private static long multiplicar(long x, long y) {
        if (x < 10 && y < 10) {
            return x * y;
        } else {
            int n = Math.max(Long.toString(x).length(), Long.toString(y).length());
            int m = n / 2 + n % 2;


            long a = x / (long) Math.pow(10, m);
            long b = x % (long) Math.pow(10, m);
            long c = y / (long) Math.pow(10, m);
            long d = y % (long) Math.pow(10, m);
            long step1 = multiplicar(a, c);
            long step2 = multiplicar(b, d);
            long step3 = multiplicar(a + b, c + d);
            long step4 = step3 - step2 - step1;
            long step5 = step1 * (long) Math.pow(10, m * 2) + step2 + step4 * (long) Math.pow(10, m);
            return step5;
        }
    }
}
