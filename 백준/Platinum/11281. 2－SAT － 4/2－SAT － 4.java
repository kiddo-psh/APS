import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] graph, reverse;
    static boolean[] visited;
    static Deque<Integer> stack = new ArrayDeque<>();
    static int[] sccId;
    static int sccCount = 0;

    static int NOT(int x) {
        return (x <= N) ? x + N : x - N;
    }

    static int toNode(int x) {
        int v = Math.abs(x);
        if (x>0) return v;
        return v + N;
    }

    static void dfs1(int u) {
        visited[u] = true;
        for (int v : graph[u]) {
            if (!visited[v]) dfs1(v);
        }
        stack.push(u);
    }

    static void dfs2(int u, int id) {
        visited[u] = true;
        sccId[u] = id;
        for (int v : reverse[u]) {
            if (!visited[v]) dfs2(v, id);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int size = N * 2;
        graph = new ArrayList[size+1];
        reverse = new ArrayList[size+1];
        for (int i=1; i<=size; i++) {
            graph[i] = new ArrayList<>();
            reverse[i] = new ArrayList<>();
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            A = toNode(A);
            B = toNode(B);

            int notA = NOT(A);
            int notB = NOT(B);

            graph[notA].add(B);
            graph[notB].add(A);

            reverse[B].add(notA);
            reverse[A].add(notB);
        }

        sccId = new int[size+1];
        visited = new boolean[size + 1];
        for (int i=1; i<=size; i++) {
            if (!visited[i]) dfs1(i);
        }

        Arrays.fill(visited,false);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                sccCount++;
                dfs2(node, sccCount);
            }
        }

        for (int i=1; i<=N; i++) {
            if (sccId[i] == sccId[i+N]) {
                System.out.println(0);
                return;
            }
        }

        int[] ans = new int[N+1];
        for (int i=1; i<=N; i++) {
            ans[i] = (sccId[i] > sccId[i+N]) ? 1 : 0;
        }

        StringBuilder output = new StringBuilder();
        output.append(1).append("\n");
        for (int i=1; i<=N; i++) {
            output.append(ans[i]).append(" ");
        }
        System.out.println(output);

        br.close();
    }
}
