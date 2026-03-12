import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static char[][] grid = new char[4][4];
	static Set<String> set = new HashSet<>();

	static void dfs(int r, int c, int count, StringBuilder sb) {
		sb.append(grid[r][c]);
		if (count==6) {
			set.add(sb.toString());
			sb.deleteCharAt(sb.length()-1);
			return;
		}
		
		for (int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if(!inRange(nr,nc)) continue;
			
			dfs(nr,nc,count+1,sb);
		}
		sb.deleteCharAt(sb.length()-1);
	}
	
	static final int[] dr = {-1,1,0,0};
	static final int[] dc = {0,0,-1,1};
	
	static boolean inRange(int r, int c) {
		return r>=0 && r<4 && c>=0 && c<4;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			set.clear();
			
			for (int i=0; i<4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j=0; j<4; j++) {
					grid[i][j] = st.nextToken().charAt(0);
				}
			}
		
			for (int i=0; i<4; i++) {
				for (int j=0; j<4; j++) {
					StringBuilder sb = new StringBuilder();
					dfs(i,j,0,sb);
				}
			}
			
			output.append("#").append(tc).append(" ").append(set.size()).append("\n");
		}
		System.out.println(output);
		br.close();
	}
}
