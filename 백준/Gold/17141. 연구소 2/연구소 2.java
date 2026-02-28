import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, area;
    static int[][] map;
    static boolean[] vVisited;
    static List<Integer> vList = new ArrayList<>();

    static int answer = Integer.MAX_VALUE;

    static void dfs(int start, int count) {
        if (count == M) {
            answer = Math.min(answer, bfs());
            return;
        }

        for (int i=start; i<vList.size(); i++) {
            if (vVisited[i]) continue;

            int idx = vList.get(i);
            int r = idx / N; int c = idx % N;

            map[r][c] = 9; vVisited[i] = true;
            dfs (i+1, count+1);
            map[r][c] = 2; vVisited[i] = false;
        }
    }

    static int bfs() {
        int[][] dist = new int[N][N];
        for (int i=0; i<N; i++) {
            Arrays.fill(dist[i], -1);
        }

        Queue<Integer> q = new ArrayDeque<>();
        int count = 0;
        for (int i=0; i<vVisited.length; i++) {
            if (vVisited[i]) {
                int idx = vList.get(i);
                int r = idx/N; int c = idx%N;

                q.offer(idx);
                dist[r][c] = 0;
                count++;
            }
        }
        if (area == count) return 0;
        

        while (!q.isEmpty()) {
            int cur = q.poll();
            int r = cur/N; int c = cur%N;


            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d], nc = c +dc[d];

                if (!inRange(nr,nc)) continue;
                if (dist[nr][nc]!=-1) continue;
                if (map[nr][nc] == 1 || map[nr][nc] == 9) continue;

                dist[nr][nc] = dist[r][c] + 1;
                count++;
                q.offer(nr*N+nc);

                if (area == count) {
                    return dist[nr][nc];
                }
                if (answer <= dist[nr][nc]) return Integer.MAX_VALUE;
            }
        }

        return Integer.MAX_VALUE;
    }

    static boolean inRange(int r, int c) {
        return r>=0 && r<N && c>=0 && c<N;
    }

    static final int[] dr = {-1,1,0,0};
    static final int[] dc = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                int n = Integer.parseInt(st.nextToken());
                map[i][j] = n;
                if (n == 2) vList.add(i*N+j);
                if (n != 1) area++;
            }
        }

        vVisited = new boolean[vList.size()];
        dfs(0, 0);
        if (answer==Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
        br.close();
    }
}
