import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	
	static int N, result;
	static int[] queenCol; // queenCol[i] = i번째 행의 퀸이 놓인 열 번호
	
	static void dfs(int row) {
		if (row==N) {
			result++;
			return;
		}
		
		for (int col=0; col<N; col++) {
			queenCol[row] = col;
			
			if (isValid(row)) {
				dfs (row+1);
			}
		}
	}
	
	static boolean isValid(int row) {
		for (int i=0; i<row; i++) {
			// 열 체크
			if (queenCol[i] == queenCol[row]) {
				return false;
			}
			
			// 대각선 체크
			if(Math.abs(queenCol[i]-queenCol[row])==Math.abs(i-row)) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			queenCol = new int[N];
			result = 0;
			
			dfs(0);
			
			output.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(output);
	}
}
