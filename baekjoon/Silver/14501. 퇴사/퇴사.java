import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] schedule = new int[15][2];
	static int[] dp = new int[16];
	
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
		for (int i = 0; i < N; i++) {       // <= N 이 아니라 < N
		    int d = schedule[i][0];
		    int p = schedule[i][1];
		    dp[i] = Math.max(paid, dp[i]);
		    if (i + d <= N) {
		        dp[i + d] = Math.max(dp[i + d], dp[i] + p);
		    }
		    paid = Math.max(paid, dp[i]);
		}
		// 루프 밖에서 dp[N]도 반영
		paid = Math.max(paid, dp[N]);

		System.out.print(paid);
	}
}
