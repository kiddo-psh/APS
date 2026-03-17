import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, count;
	static int answer = Integer.MAX_VALUE;
	static int[][] map;
	
	static class Cam {
		int row, col, type;

		public void set(int row, int col, int type) {
			this.row = row;
			this.col = col;
			this.type = type;
		}
	}
	static Cam[] CCTVs = new Cam[8];
	static {
		for (int i=0; i<8; i++) CCTVs[i] = new Cam();
	}
	
	static final int[] dr = {-1,0,1,0}; // 상우하좌
	static final int[] dc = {0,1,0,-1};
	static final int[][] camDir = {
			{1, 0, 0, 0},
			{1, 0, 1, 0},
			{1, 1, 0, 0},
			{1, 1, 0, 1},
			{1, 1, 1, 1},
	};
	static final int[] camDirCount = {4,2,4,4,1};
	
	static boolean inRange(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if (0 < num && num < 6) {
					CCTVs[count++].set(i,j,num-1);
				}
			}
		}
		
		dfs(0);
		
		System.out.println(answer);
		
		br.close();
	}
	
	static void dfs(int c) {
		if (c==count) {
			int cnt = 0;
			for (int[] arr : map) {
				for (int x : arr) {
					if (x == 0) cnt++;
				}
			}
			answer = Math.min(answer, cnt);
			return;
		}
		
		
		Cam cam = CCTVs[c];
		for (int i=0; i<camDirCount[cam.type]; i++) {			
			int[][] backup = new int[N][M];
			for (int x=0; x<N; x++) backup[x] = map[x].clone();
			
			
			for (int j=0; j<4; j++) {	
				if (camDir[cam.type][j] == 1) {
					watch(cam.row, cam.col, (i+j)%4);
				}
			}
			
			dfs(c+1);
			map = backup;
		}
		
	}
	
	static void watch(int row, int col, int dir) {
		int r = row;
		int c = col;
		
		while(true) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if (!inRange(nr, nc)) break;
			if (map[nr][nc] == 6) break;
			
			if (map[nr][nc] == 0) map[nr][nc] = 9;
			
			r = nr;
			c = nc;
		}
	}
}
