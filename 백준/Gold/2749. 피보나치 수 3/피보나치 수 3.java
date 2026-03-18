import java.util.Scanner;

public class Main {
	static int MOD = 1_000_000;
	
    static long[][] multiply(long[][] a, long[][] b) {
        long[][] res = new long[2][2];

        res[0][0] = (a[0][0] * b[0][0] + a[0][1] * b[1][0]) % MOD;
        res[0][1] = (a[0][0] * b[0][1] + a[0][1] * b[1][1]) % MOD;
        res[1][0] = (a[1][0] * b[0][0] + a[1][1] * b[1][0]) % MOD;
        res[1][1] = (a[1][0] * b[0][1] + a[1][1] * b[1][1]) % MOD;

        return res;
    }

    static long[][] power(long[][] matrix, long n) {
        if (n == 1) return matrix;

        long[][] half = power(matrix, n / 2);
        long[][] result = multiply(half, half);

        if (n % 2 == 1) {
            result = multiply(result, matrix);
        }

        return result;
    }

    static long fibonacci(long n) {
        if (n == 0) return 0;

        long[][] base = {
            {1, 1},
            {1, 0}
        };

        long[][] result = power(base, n);
        return result[0][1]; // F(n)
    }
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long N = sc.nextLong();
		if (N==0) {
			System.out.println(0); return;
		}
		if (N==1) {
			System.out.println(1); return;
		}
		System.out.println(fibonacci(N));
	}
}
