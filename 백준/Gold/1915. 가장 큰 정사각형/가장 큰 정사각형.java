import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] grid = new int[N][M];
        int[][] dp = new int[N][M];

        for (int r=0; r<N; r++) {
            String S = br.readLine();
            for (int c=0; c<M; c++) {
                grid[r][c] = S.charAt(c) - '0';
            }
        }

        for (int r=0; r<N; r++) {
            dp[r][0] = grid[r][0];
        }

        for (int c=0; c<M; c++) {
            dp[0][c] = grid[0][c];
        }
        
        for (int r=1; r<N; r++) {
            for (int c=1; c<M; c++) {
                if (grid[r][c] == 0) continue;

                dp[r][c] = Math.min(dp[r-1][c], Math.min(dp[r][c-1], dp[r-1][c-1])) + 1;
            }
        }
        
        int maxLen = 0;
        
        for (int r=0; r<N; r++) {
            for (int c=0; c<M; c++) {
                maxLen = Math.max(maxLen, dp[r][c]);
            }
        }

        System.out.println( maxLen*maxLen);

        br.close();
    }
}
