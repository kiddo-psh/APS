import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, answer;
    static int[] indegree, topoOrder, ways;
    static List<Integer>[] adj;
    static boolean[] visited;
    static Queue<Integer> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        input();
        topologySort();
        System.out.println(getChainCount());
    }

    static long getChainCount() {
        ways = new int[N+1];
        Arrays.fill(ways, 1);

        final int MOD = 1_000_000_007;
        long answer = 0;

        for (int cur : topoOrder) {
           answer = (answer + ways[cur]) % MOD;
           Arrays.fill(visited, false);

           q.offer(cur);
           visited[cur] = true;
           while (!q.isEmpty()) {
               int u = q.poll();
               for (int v : adj[u]) {
                   if (visited[v]) continue;

                   visited[v] = true;
                   q.offer(v);
                   ways[v] = (ways[v] + ways[cur]) % MOD;
               }
           }
           q.clear();
        }

        return answer;
    }

    static void topologySort() {
        for (int i=1; i<=N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        int index = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            topoOrder[index++] = u;
            for (int v : adj[u]) {
                if (--indegree[v] == 0) {
                    q.offer(v);
                }
            }
        }

        q.clear();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N+1];
        for (int i=1; i<=N; i++) adj[i] = new ArrayList<>();

        indegree = new int[N+1];
        topoOrder = new int[N];
        visited = new boolean[N+1];

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj[u].add(v);
            indegree[v]++;
        }

        br.close();
    }
}
