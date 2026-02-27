import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 
 * 메모리: 14256kb / 실행시간:104ms
 * 
 * */

public class Main {
	
	static int[][] schedule = new int[1_500_000][2];
	static int[] dp = new int[1_500_001];
	
	public static void main(String[] args) throws IOException {
		// 입력부
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			schedule[i][0] = Integer.parseInt(st.nextToken());
			schedule[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// DP
		int paid = 0;
		for (int i = 0; i < N; i++) {    
		    int d = schedule[i][0];
		    int p = schedule[i][1];
		    dp[i] = Math.max(paid, dp[i]);
		    if (i + d <= N) {
		        dp[i + d] = Math.max(dp[i + d], dp[i] + p);
		    }
		    // i일까지의 최대 보수
		    paid = Math.max(paid, dp[i]);
		}
		// dp[N] 반영
		paid = Math.max(paid, dp[N]);

		System.out.print(paid);
	}
}
