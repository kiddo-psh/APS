import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C, M, answer;
	
	static Shark[] sharks;
	static int[][] board;
	
	static class Shark {
		int r, c, v, dir, size;
		boolean alive = true;
		
		Shark(int r, int c, int v, int dir, int size) {
			this.r = r;
			this.c = c;
			this.v = v;
			this.dir = dir;
			this.size = size;
		}
		
		void move() {
			int dist = this.v;
			
			if (dir == 1 || dir == 2) dist %= 2 * (R - 1);
			else dist %= 2 * (C - 1);
			
			while (dist-- > 0) {
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				
				if (!inRange(nr, nc)) {
					dir = (dir<3) ? 3-dir : 7-dir;
					nr = r + dr[dir];
					nc = c + dc[dir];
				}
				r = nr;
				c = nc;
			}
		}
	}
	
	static void fishing(int col) {
		for (int row = 0; row < R; row++) {
			int idx = board[row][col];
			if (idx != 0 && sharks[idx].alive) {
				sharks[idx].alive = false;
				board[row][col] = 0;
				answer += sharks[idx].size;
				return;
			}
		}
	}
	
	static void moving() {
		int[][] next = new int[R][C];
		
		for (int i = 1; i <= M; i++) {
			Shark s = sharks[i];
			
			if (!s.alive) continue;
			
			s.move();
			
			if (next[s.r][s.c] == 0) {
				next[s.r][s.c] = i;
			} else {
				Shark s2 = sharks[next[s.r][s.c]];
				
				if (s2.size >= s.size) {
					s.alive = false;
				} else {
					s2.alive = false;
					next[s.r][s.c] = i;
				}
			}
		}
		
		board = next;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sharks = new Shark[M + 1];
		board = new int[R][C];
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			Shark newShark = new Shark(r, c, s, d, z);
			sharks[i] = newShark;
			board[r][c] = i;
		}
		
		for (int fisher = 0; fisher < C; fisher++) {
			fishing(fisher);
			moving();
		}
		
		System.out.println(answer);
		br.close();
	}
	
	static final int[] dr = {0, -1, 1, 0, 0};
	static final int[] dc = {0, 0, 0, 1, -1};
	
	static boolean inRange(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
}