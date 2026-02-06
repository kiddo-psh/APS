import java.util.*;
import java.io.*;

public class Solution {
	static int[][] ladder = new int[100][100];
	static int[][] visited = new int[100][100];
	static final int[] dr = {0, 0, -1}; // 좌, 우, 하
	static final int[] dc = {-1, 1, 0};
	static int T;
	
	static int arriveIdx(int c) {
		int r = 98;
		visited[r][c] = T;
		while (r > 0) {
			for (int i=0; i<3; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if (!inRange(nr, nc)) continue;
				if (visited[nr][nc] == T) continue;
				if (nr == 0) return nc;
				
				if (ladder[nr][nc] == 1) {
					visited[nr][nc] = T;
					r = nr;	c = nc;
					break;					
				} 
			}
		}
		return -1;
	}
	
	static boolean inRange(int r, int c) {
		return r>=0 && r<100 && c>=0 && c<100;
	}
	
	public static void main(String[] args) throws IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int tc=1; tc<=10; tc++) {
			T = Integer.parseInt(br.readLine()); // 테케번호
			for (int i=0; i<100; i++) {
				st = new StringTokenizer(br.readLine()); // 100 줄
				for (int j=0; j<100; j++) { // 100 숫자
					ladder[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			output.append("#").append(tc).append(" ");
			for (int i=0; i<100; i++) {
				if(ladder[99][i] == 2) {
					output.append(arriveIdx(i)).append("\n");
					break;
				}
			}
			
		}
		System.out.println(output);
	}
}
