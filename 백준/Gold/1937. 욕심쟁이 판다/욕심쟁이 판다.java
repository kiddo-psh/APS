import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] grid, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][N];
        int answer = 0;

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                answer = Math.max(answer, solve(i,j));
            }
        }

        System.out.print(answer);
        br.close();
    }

    private static int solve(int r, int c) {
        if (dp[r][c] != 0) return dp[r][c];

        dp[r][c] = 1;
        for (int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (!inRange(nr, nc)) continue;
            if (grid[r][c] < grid[nr][nc]) {
                dp[r][c] = Math.max(dp[r][c], 1+solve(nr,nc));
            }
        }
        return dp[r][c];
    }

    static int[] dr = new int[] {-1,1,0,0};
    static int[] dc = new int[] {0,0,-1,1};

    static boolean inRange (int r, int c) {
        return r>=0 && r<N && c>=0 && c<N;
    }
}
