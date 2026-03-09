import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	
	static final int[] dr = {-1,-1,-1,0,0,1,1,1};
	static final int[] dc = {-1,0,1,-1,1,-1,0,1};
	
	static boolean inRange(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
	
	static int count(int r, int c) {
		int cnt = 0;
		for (int d=0; d<8; d++) {
			int nr = r+dr[d], nc = c+dc[d];
			
			if (!inRange(nr,nc)) continue;
			if (map[nr][nc]==-1) cnt++;
		}
		return cnt;
	}
	
	static void click(int r, int c) {
		for (int d=0; d<8; d++) {
			int nr = r+dr[d], nc = c+dc[d];
			
			if (!inRange(nr,nc)) continue;
			if (visited[nr][nc]) continue;
			
			visited[nr][nc] = true;
			if (map[nr][nc]==0) click(nr,nc);
		}
	}
	
	public static void main(String[] args) throws IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			
			for (int i=0; i<N; i++) {
				String S = br.readLine();
				for (int j=0; j<N; j++) {
					if (S.charAt(j)=='*') {
						map[i][j] = -1;
						visited[i][j] = true;
					}
					else map[i][j] = -2;
				}
			}
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (map[i][j]==-2) {
						map[i][j] = count(i,j);
					}
				}
			}
			int answer = 0;
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (map[i][j]==0 && !visited[i][j]) {
						visited[i][j] = true;
						click(i, j);
						answer++;
					}
				}	
			}
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (!visited[i][j]) answer++;
				}
			}
			
			output.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(output);
		br.close();
	}

}
