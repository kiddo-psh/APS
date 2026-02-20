import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, t, answer;
	static int[][] cheese;
	static boolean[][] visited;
	static boolean[][] temp;
	static Queue<int[]> q = new LinkedList<>();
	
	static void eat(int t) {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (cheese[i][j]<=t) {
					visited[i][j] = true;
				}
			}
		}
	}
	
	static int count() {
		for(int i=0; i<N; i++) temp[i] = visited[i].clone();
		
		int cnt = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (temp[i][j]) continue;
				bfs(i, j);
				cnt++;
			}
		}
		
		return cnt;
	}
	
	static void bfs(int r, int c) {
		temp[r][c] = true;
		q.clear();
		q.offer(new int[] {r,c});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cr = cur[0];
			int cc = cur[1];
			
			for (int d=0; d<4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				
				if (!inRange(nr,nc)) continue;
				if (temp[nr][nc]) continue;
				
				temp[nr][nc] = true;
				q.offer(new int[] {nr,nc});
			}
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
