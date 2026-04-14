import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N, M;
    public static void main(String[] args) throws IOException {
        StringBuilder output = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            List<Integer>[] graph = new ArrayList[N+1];
            for (int i=1; i<=N; i++) graph[i] = new ArrayList<>();

            List<Integer>[] reverse = new ArrayList[N+1];
            for (int i=1; i<=N; i++) reverse[i] = new ArrayList<>();

            while (M-->0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph[a].add(b);
                reverse[b].add(a);
            }

            //int[] inCount = new int[N+1]; // 나보다 작은 사람 수
            //int[] outCount = new int[N+1]; //나보다 큰 사람 수

            int answer = 0;
            for (int i=1; i<=N; i++) {
                int outCount = bfs(i, graph);
                int inCount = bfs(i, reverse);

                if (outCount + inCount == N-1) answer++;
            }

            output.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(output);
        br.close();
    }

    private static int bfs(int i, List<Integer>[] graph) {
        int count = 0;

        boolean[] visited = new boolean[N+1];
        visited[i] = true;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(i);

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int v : graph[u]) {
                if (visited[v]) continue;
                visited[v] = true;
                q.offer(v);
                count++;
            }
        }

        return count;
    }
}
