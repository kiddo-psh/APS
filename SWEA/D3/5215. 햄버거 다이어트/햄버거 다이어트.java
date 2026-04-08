import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            int[][] foods = new int[N+1][2];

            for (int i=1; i<=N; i++) {
                st = new StringTokenizer(br.readLine());
                foods[i][0] = Integer.parseInt(st.nextToken());
                foods[i][1] = Integer.parseInt(st.nextToken());
            }

            int[][] dp = new int[N+1][L+1];
            for (int i=1; i<=N; i++) {
                for (int j=0; j<=L; j++) {
                    int cal = foods[i][1];
                    int score = foods[i][0];

                    if (j < cal) dp[i][j] = dp[i-1][j];
                    else dp[i][j] = Math.max(dp[i-1][j-cal]+score, dp[i-1][j]);
                }
            }

            output.append("#").append(tc).append(" ").append(dp[N][L]).append("\n");
        }
        System.out.println(output);
        br.close();
    }
}
