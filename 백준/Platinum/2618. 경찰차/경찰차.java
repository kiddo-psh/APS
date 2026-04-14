import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int W, N;
    static int[][] dp, event, choice;
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());

        event = new int[W+1][2];

        for (int i=1; i<=W; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            event[i][0] = Integer.parseInt(st.nextToken());
            event[i][1] = Integer.parseInt(st.nextToken());
        }

        dp = new int[W+1][W+1];
        choice = new int[W+1][W+1];

        for (int i=0; i<=W; i++) {
            Arrays.fill(dp[i], -1);
        }

        int answer = solve(0, 0);

        StringBuilder output = new StringBuilder();
        output.append(answer).append("\n");

        trace(0, 0, output);

        System.out.print(output);

        br.close();
    }

    private static void trace (int a, int b, StringBuilder sb) {
        int next = Math.max(a, b) + 1;
        if (next > W) return;

        if (choice[a][b] == 1) {
            sb.append(1).append("\n");
            trace(next, b, sb);
        } else {
            sb.append(2).append("\n");
            trace(a, next, sb);
        }
    }

    private static int dist1(int a, int b) {
        int r1, r2, c1, c2;
        if (a==0) {
            r1 = 1;
            c1 = 1;
        } else {
            r1 = event[a][0];
            c1 = event[a][1];
        }
        r2 = event[b][0];
        c2 = event[b][1];

        return Math.abs(r1-r2) + Math.abs(c1-c2);
    }
    private static int dist2(int a, int b) {
        int r1, r2, c1, c2;
        if (a==0) {
            r1 = N;
            c1 = N;
        } else {
            r1 = event[a][0];
            c1 = event[a][1];
        }
        r2 = event[b][0];
        c2 = event[b][1];

        return Math.abs(r1-r2) + Math.abs(c1-c2);
    }

    private static int solve (int a, int b) {
        int next = Math.max(a, b) + 1;
        if (next > W) return 0;

        if (dp[a][b] != -1) return dp[a][b];

        int cost1 = dist1(a, next) + solve(next, b);
        int cost2 = dist2(b, next) + solve(a, next);

        if (cost1 <= cost2) {
            dp[a][b] = cost1;
            choice[a][b] = 1;
        } else {
            dp[a][b] = cost2;
            choice[a][b] = 2;
        }

        return dp[a][b];
    }
}
