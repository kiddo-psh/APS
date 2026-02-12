import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, tc;
	static int len, maxLen, roomNum, minNum;
	
	static final int[] dr = {-1,1,0,0};
	static final int[] dc = {0,0,-1,1};
	
	static boolean inRange (int r, int c) { 
		return r>=0 && r<N && c>=0 && c<N;
	}
	
	static int[][] rooms = new int[1001][1001];
	static int[][] visited = new int[1001][1001];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					rooms[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			maxLen = 0;
			minNum = Integer.MAX_VALUE;
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					
					if (visited[i][j]==tc) continue;
					
					roomNum = rooms[i][j];
					len = 0;
					
					dfs(i,j);
					
					if (maxLen == len) {
						minNum = Math.min(roomNum, minNum);
					}
					else if (maxLen < len) {
						maxLen = len;
						minNum = roomNum;
					}
				}
			}
			
			output.append("#").append(tc).append(" ").
			append(minNum).append(" ").append(maxLen).append("\n");
			
		}
		System.out.println(output);
	}
	
	static void dfs (int r, int c) {
		len++;
		visited[r][c] = tc;
		roomNum = Math.min(roomNum, rooms[r][c]);
		
		for (int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (!inRange(nr, nc)) continue;
			if (visited[nr][nc]==tc) continue;
			
			if (Math.abs(rooms[nr][nc]-rooms[r][c])==1) {
				dfs(nr, nc);
			}
		}
	}
}
