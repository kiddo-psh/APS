import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, count;
    static int[][] town, dist;
    static Queue<int[]> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        StringBuilder output = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            town = new int[N][N];
            dist = new int[N][N];

            for (int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<N; j++) {
                    town[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            count = 0;

            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    dfs(i,j,1);
                }
            }

            output.append("#").append(tc).append(" ").append(count).append("\n");
        }

        System.out.print(output);
        br.close();
    }
    static void dfs(int r, int c, int k) {
        if (k==N+2) return;

        int cost = k*k + (k-1)*(k-1);
        cost *= -1;
        int cnt = 0;

        q.clear();
        q.offer(new int[]{r,c});

        for (int i=0; i<N; i++) Arrays.fill(dist[i], 0);
        dist[r][c] = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cr = cur[0];
            int cc = cur[1];

            if (dist[cr][cc] > k) continue;
            if (town[cr][cc] == 1) {
                cost += M;
                cnt++;
            }

            for (int d=0; d<4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (!inRange(nr, nc)) continue;
                if (dist[nr][nc] != 0) continue;

                dist[nr][nc] = dist[cr][cc]+1;
                q.offer(new int[] {nr, nc});
            }
        }

        if (cost >= 0) count = Math.max(count, cnt);
        dfs(r, c, k+1);
    }

    static final int[] dr = {-1,1,0,0};
    static final int[] dc = {0,0,-1,1};

    static boolean inRange(int r, int c) {
        return r>=0 && r<N && c>=0 && c<N;
    }
}
