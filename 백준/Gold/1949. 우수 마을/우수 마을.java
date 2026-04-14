import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] population;
    static List<Integer>[] adj;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        population = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        adj = new ArrayList[N+1];
        for (int i=1; i<=N; i++) adj[i] = new ArrayList<>();

        for (int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        dp = new int[N+1][2]; // 우수마을 선정할지말지

        dfs(1, 0);

        System.out.print(Math.max(dp[1][0], dp[1][1]));

        br.close();
    }

    private static void dfs(int u, int p) {
        dp[u][0] = 0;
        dp[u][1] = population[u];

        for (int v : adj[u]) {
            if (v == p) continue;
            dfs(v, u);

            dp[u][1] += dp[v][0];
            dp[u][0] += Math.max(dp[v][0], dp[v][1]);
        }
    }
}

/*

마을 주민 수의 합을 최대로
인접해있으면 우수마을 선정 불가
적어도 하나 이상의 우수마을과 연결

 */