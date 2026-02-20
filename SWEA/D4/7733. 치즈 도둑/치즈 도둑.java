import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 메모리: 96,064 kb
 * 실행시간: 292 ms
 */
public class Solution {
	static int N, t, answer;
	static int[][] cheese;
	static boolean[][] visited;
	static boolean[][] temp;
	
	// t일차 치즈 먹기
	static void eat(int t) {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (cheese[i][j]<=t) {
					visited[i][j] = true;
				}
			}
		}
	}
	
	// 치즈 덩어리 수 세기
	static int count() {
		for(int i=0; i<N; i++) temp[i] = visited[i].clone();
		
		int cnt = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (temp[i][j]) continue;
				dfs(i, j);
				cnt++;
			}
		}
		
		return cnt;
	}
	
	// 치즈 덩어리 방문 체크 
	static void dfs(int r, int c) {
		temp[r][c] = true;
		
		for (int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (!inRange(nr, nc)) continue;
			if (temp[nr][nc]) continue;
			
			dfs(nr, nc);
		}
	}
	
	static boolean inRange(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
	
	static final int[] dr = {-1,1,0,0};
	static final int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			cheese = new int[N][N];
			visited = new boolean[N][N];
			temp = new boolean[N][N];
			
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					cheese[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			t=0;
			answer=1;
			while (++t <= 100) {
				eat(t);
				answer = Math.max(answer, count());
			}
			
			output.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(output);
	}
}
