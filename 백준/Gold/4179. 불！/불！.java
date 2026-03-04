import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static String output;
	static int R, C;
	static char[][] map;
	
	static int[][] fire, jihoon;
	
	static Queue<Point> q1 = new ArrayDeque<>();
	static Queue<Point> q2 = new ArrayDeque<>();
	
	static class Point {
		int r, c;
		public Point(int r, int c) {this.r=r; this.c=c;}
	}
	
	static void fireDist() {
		while (!q2.isEmpty()) {
			Point cur = q2.poll();
			for (int d=0; d<4; d++) {
				int nr = cur.r + dr[d], nc = cur.c + dc[d];
				
				if (!inRange(nr, nc)) continue;
				if (map[nr][nc] == '#') continue;
				if (fire[nr][nc] >= 0) continue;
				
				fire[nr][nc] = fire[cur.r][cur.c] + 1;
				q2.offer(new Point(nr, nc));
			}
		}
	}
	
	static void escape() {
		while (!q1.isEmpty()) {
			Point cur = q1.poll();
			for (int d=0; d<4; d++) {
				int nr = cur.r+dr[d], nc = cur.c+dc[d];
				
				if (!inRange(nr, nc)) {
					output = Integer.toString(jihoon[cur.r][cur.c]+1);
					return;
				}
				if (map[nr][nc] == '#') continue;
				if (fire[nr][nc]!=-1 && fire[nr][nc] <= jihoon[cur.r][cur.c]+1) continue;
				if (jihoon[nr][nc]>=0) continue;
				
				jihoon[nr][nc] = jihoon[cur.r][cur.c] + 1;
				q1.offer(new Point(nr, nc));
			}
		}
		
		output = "IMPOSSIBLE";
		return;
	}
	
	static final int[] dr = {-1,1,0,0};
	static final int[] dc = {0,0,-1,1};
	
	static boolean inRange(int r, int c) {
		return r>=0 && r<R && c>=0 && c<C;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		fire = new int[R][C];
		jihoon = new int[R][C];
		
		for (int i=0; i<R; i++) {
			Arrays.fill(fire[i], -1);
			Arrays.fill(jihoon[i], -1);
		}
		
		for (int i=0; i<R; i++) {
			String S = br.readLine();
			for (int j=0; j<C; j++) {
				map[i][j] = S.charAt(j);
				if(map[i][j]=='J') {
					q1.offer(new Point(i, j));
					jihoon[i][j] = 0;
				}
				if(map[i][j]=='F') {
					q2.offer(new Point(i, j));
					fire[i][j] = 0;
				}
			}
		}
		
		fireDist();
		escape();
		
		System.out.println(output);
		br.close();
	}
}
