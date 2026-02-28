import java.util.Scanner;

public class Main {
	static long N, K;
	static boolean flag;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextLong();
		K = sc.nextLong();
		
		double d = Math.sqrt(K);
		flag = K==(long)d*d ? true : false;
		
		lowerbound(K);
		
		sc.close();
	}
	
	static void lowerbound(long K) {
		long left = 0;
		long right = N*N;
		long answer = 0;
		
		while(left<=right) {
			long mid = (left+right)/2;
			
			if(count(mid) >= K) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		
		System.out.println(answer);
	}
	
	static long count (long num) {
		long cnt = 0;
		for (int i=1; i<=N; i++) {
			cnt += Math.min(num/i, N);
		}
		return cnt;
	}
}
