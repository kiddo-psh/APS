import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, X;
	static int[][] grid;
	static boolean[] checked;
	
	static int checkRow() {
		int cnt = 0;
		
		for (int r=0; r<N; r++) {
			Arrays.fill(checked, false);
			int prev = grid[r][0];
			boolean can = true;
			
			for (int c=1; c<N; c++) {
				prev = grid[r][c-1];
				if (prev == grid[r][c]) continue;
				
				int diff = prev - grid[r][c];
				if (Math.abs(diff) >= 2) {can=false; break;}
				
				if (diff == -1) { // 앞 x개 확인
					
					boolean canEmplace = true;
					
					for (int i=1; i<=X; i++) {
						if (c-i < 0) {
							canEmplace = false;
							can = false;
							break;
						}
						
						if (grid[r][c-i] != prev || checked[c-i]) {
							canEmplace = false;
							can = false;
							break;
						}
					}
					if (!can) break;
					if (canEmplace) {
						for (int i=1; i<=X; i++) {
							checked[c-i] = true;
						}
					}
					
				}
				else if (diff == 1) { // 뒤 x개 확인
					
					boolean canEmplace = true; // 경사로 설치가능여부
					
					for (int i=0; i<X; i++) {
						if (c+i >= N) {
							canEmplace = false;
							can = false; 
							break;
						}
						if(grid[r][c+i] != grid[r][c] || checked[c+i]) {
							canEmplace = false;
							can = false; 
							break;
						}
					}
					if (!can) break;
					if (canEmplace) {
						for (int i=0; i<X; i++) {
							checked[c+i] = true;
						}
					}
				}
			}
			
			if(can) {cnt++; }
		}
		
		return cnt;
	}
	
	static void rotate() {
		int[][] temp = new int[N][N];
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				temp[i][j] = grid[N-j-1][i];
			}
		}
		
		grid = temp;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			grid = new int[N][N];
			checked = new boolean[N];
			
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int answer = 0;
			
			answer += checkRow();
			rotate();
			answer += checkRow();
			
			output.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(output);
		br.close();
	}
}
