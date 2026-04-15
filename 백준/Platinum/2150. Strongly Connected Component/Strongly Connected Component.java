import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V, E;
    static List<Integer>[] graph, reverse;
    static boolean[] visited;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V+1];
        reverse = new ArrayList[V+1];

        for (int i=1; i<=V; i++) {
            graph[i] = new ArrayList();
            reverse[i] = new ArrayList();
        }

        for (int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph[A].add(B);
            reverse[B].add(A);
        }

        visited = new boolean[V+1];
        for (int i=1; i<=V; i++) {
            if (!visited[i]) dfs1(i);
        }

        StringBuilder output = new StringBuilder();
        List<List<Integer>> scc = new ArrayList<>();
        int cnt = 0;
        Arrays.fill(visited, false);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                List<Integer> list = new ArrayList<>();
                dfs2(node, list);
                Collections.sort(list);
                scc.add(list);
                cnt++;
            }
        }

        output.append(cnt).append("\n");
        Collections.sort(scc, (a,b) -> Integer.compare(a.get(0), b.get(0)));
        for (List<Integer> list : scc) {
            for (int x : list) {
                output.append(x).append(" ");
            }
            output.append(-1).append("\n");
        }

        System.out.print(output);

        br.close();
    }

    static void dfs1(int u) {
        visited[u] = true;

        for (int v : graph[u]) {
            if (visited[v]) continue;
            dfs1(v);
        }

        stack.push(u);
    }

    static void dfs2(int u, List<Integer> list) {
        visited[u] = true;
        list.add(u);

        for (int v : reverse[u]) {
            if (visited[v]) continue;
            dfs2(v, list);
        }
    }
}
