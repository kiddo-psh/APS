import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N, M;
    static final int INF = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        StringBuilder output = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            int[][] graph = new int[N+1][N+1];
            for (int i=1; i<=N; i++) Arrays.fill(graph[i], INF);
            for (int i=1; i<=N; i++) graph[i][i] = 0;

            while (M-->0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph[a][b] = 1;
            }

            for (int k=1; k<=N; k++) {
                for (int i=1; i<=N; i++) {
                    for (int j=1; j<=N; j++) {
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                    }
                }
            }

            int answer = 0;

            for (int i=1; i<=N; i++) {
                int cnt = 0;
                for (int j=1; j<=N; j++) {
                    if (i==j) continue;
                    if (graph[i][j] != INF || graph[j][i] != INF) cnt++;
                }
                if (cnt == N-1) answer++;
            }

            output.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(output);
        br.close();
    }
}
