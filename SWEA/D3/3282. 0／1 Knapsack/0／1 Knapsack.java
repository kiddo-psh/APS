import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            int[] V = new int[N+1];
            int[] C = new int[N+1];
            for (int i=1; i<=N; i++) {
                st = new StringTokenizer(br.readLine());

                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                V[i] = v;
                C[i] = c;
            }

            int[][] dp = new int[N+1][K+1];
            for (int i=1; i<=N; i++) {
                for (int j=0; j<=K; j++) {
                    if (j < V[i]) dp[i][j] = dp[i-1][j];
                    else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-V[i]] + C[i]);
                }
            }

            output.append("#").append(tc).append(" ").append(dp[N][K]).append("\n");
        }
        System.out.println(output);
        br.close();
    }
}
