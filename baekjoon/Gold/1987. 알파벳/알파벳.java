import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 메모리: 14,888 kb
 * 실행시간: 852 ms
 */
public class Main {
	static int R, C, answer;
	static char[][] board;
	
	static void dfs(int r, int c, int count, int used) {
		answer = Math.max(count, answer);
		
		for (int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (!inRange(nr, nc)) continue;
			if ((used & 1<<board[nr][nc]-'A') != 0) continue;
			
			dfs (nr, nc, count+1, used | 1<<board[nr][nc]-'A');
		}
	}
	
	static final int[] dr = {-1,1,0,0};
	static final int[] dc = {0,0,-1,1};
	
	static boolean inRange(int r, int c) {
		return r>=0 && r<R && c>=0 && c<C;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		
		for (int i=0; i<R; i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		dfs(0, 0, 1, 1<<board[0][0]-'A');
		
		System.out.println(answer);
	}
}
