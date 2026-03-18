import java.util.Scanner;

public class Main {
	static Long A,B,C;
	
	static long pow (long a, long n, long mod) {
		if (n==0) return 1;
		
		long half = pow(a, n/2, mod);
		
		long result = half * half % mod;
		if (n % 2 != 0) {
			result = result * a % mod;
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.nextLong();
		B = sc.nextLong();
		C = sc.nextLong();
		
		System.out.println(pow(A, B, C));
	}
}
