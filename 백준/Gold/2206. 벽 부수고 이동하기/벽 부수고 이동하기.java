import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][][] visited;
	static Queue<Point> q = new ArrayDeque<>();
	
	static class Point {
		int r, c, dist, wall;
		Point (int r, int c, int dist, int wall) {
			this.r=r; this.c=c; 
			this.dist=dist; this.wall=wall;
		}
	}
	
	static final int[] dr = {-1,1,0,0};
	static final int[] dc = {0,0,-1,1};
	
	static boolean inRange(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M][2];
		
		for (int r=0; r<N; r++) {
			String s = br.readLine();
			for (int c=0; c<M; c++) {
				map[r][c] = s.charAt(c) - '0';
			}
		}
		
		q.offer(new Point(0,0,1,0));
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			if (cur.r==N-1 && cur.c==M-1) {
				System.out.println(cur.dist);
				return;
			}
			
			for (int d=0; d<4; d++) {
				int nr = cur.r+dr[d];
				int nc = cur.c+dc[d];
				
				if(!inRange(nr,nc)) continue;
				
				if(map[nr][nc]==1) {
					if(cur.wall==1) continue;
					if(visited[nr][nc][1]) continue;
					
					visited[nr][nc][1] = true;
					q.offer(new Point(nr, nc, cur.dist+1, 1));
					
				} else {
					if (visited[nr][nc][cur.wall]) continue;
					
					visited[nr][nc][cur.wall] = true;
					q.offer(new Point(nr, nc, cur.dist+1, cur.wall));
				}
			}
		}
		System.out.println(-1);
		br.close();
	}
}
