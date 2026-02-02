import java.io.*;
import java.util.*;
 
public class Solution {
 
    static int N, M;
    static final int MAX_N = 50001;
 
    static int[] indegree = new int[MAX_N];
    static int[] queue = new int[MAX_N];
    static int head, tail;
 
    @SuppressWarnings("unchecked")
    static List<Integer>[] graph = new ArrayList[MAX_N];
 
    // 초기화 
    static void init(int N) {
        for (int i = 1; i <= N; i++) {
            if (graph[i] == null) {
                graph[i] = new ArrayList<>();
            } else {
                graph[i].clear();
            }
            indegree[i] = 0;
        }
    }
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
            // 입력부
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
 
            init(N);
            out.append("#").append(tc).append(" ");
 
            int a, b;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
 
                graph[a].add(b);
                indegree[b]++;
            }
 
            // queue 초기화
            head = 0;
            tail = 0;
 
            for (int i = 1; i <= N; i++) {
                if (indegree[i] == 0) {
                    queue[tail++] = i;
                }
            }
 
            while (head != tail) {
                int cur = queue[head++];
                out.append(cur).append(" ");
 
                for (int next : graph[cur]) {
                    if (--indegree[next] == 0) {
                        queue[tail++] = next;
                    }
                }
            }
 
            out.append("\n");
        }
        System.out.print(out);
    }
}