import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	
	static final int DAY = 0;
	static final int NIGHT = 1;
	
	static final int[] dr = {-1,1,0,0};
	static final int[] dc = {0,0,-1,1};
	
	static boolean inRange(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
	
	static class Point {
		int r,c,time,k,dist;
		
		public Point(int r, int c, int time, int k, int dist) {
			this.r=r; this.c=c; this.time=time; this.k=k; this.dist=dist;
		}
	}
	
	static char[][] map;
	static boolean[][][][] visited;
	static Queue<Point> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new boolean[N][M][K+1][2];
		
		for (int i=0; i<N; i++) {
			String S = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = S.charAt(j);
			}
		}
		
		int answer = -1;
		q.offer(new Point(0,0,DAY,0,1));
		visited[0][0][0][0] = true;
		
		while (!q.isEmpty()) {
		    Point cur = q.poll();
		    int r = cur.r, c = cur.c;

		    if (r==N-1 && c==M-1) { answer = cur.dist; break; }

		    boolean needWait = false; // ★ 이 상태에서 "밤에 벽 때문에" 기다릴 필요가 있었는지

		    for (int d=0; d<4; d++) {
		        int nr = r + dr[d], nc = c + dc[d];
		        if (!inRange(nr, nc)) continue;

		        if (map[nr][nc] == '1') {
		            if (cur.k == K) continue;

		            if (cur.time == DAY) {
		                if (visited[nr][nc][cur.k+1][NIGHT]) continue;
		                visited[nr][nc][cur.k+1][NIGHT] = true;
		                q.offer(new Point(nr, nc, NIGHT, cur.k+1, cur.dist+1));
		            } else { 
		                // 밤에는 벽을 못 부숨 → "대기 후보"만 표시
		                needWait = true;
		            }
		        } else { // '0'
		            int nt = 1 - cur.time;
		            if (visited[nr][nc][cur.k][nt]) continue;
		            visited[nr][nc][cur.k][nt] = true;
		            q.offer(new Point(nr, nc, nt, cur.k, cur.dist+1));
		        }
		    }

		    // ★ 밤에 벽 때문에 막혔던 적이 있으면, 노드당 딱 1번만 기다리기
		    if (needWait && cur.time == NIGHT) {
		        if (!visited[r][c][cur.k][DAY]) {
		            visited[r][c][cur.k][DAY] = true;
		            q.offer(new Point(r, c, DAY, cur.k, cur.dist+1));
		        }
		    }
		}
		
		System.out.println(answer);
		br.close();
	}
}
