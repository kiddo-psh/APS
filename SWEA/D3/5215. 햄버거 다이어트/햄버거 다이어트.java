import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, L, result;
	
	static int[] score = new int[21];
	static int[] calories = new int[21];
	
	static void dfs(int index, int s, int c) {
		if (c > L) return;
		if (index == N) {
			result = Math.max(result, s);
			return;
		}
		
		dfs(index+1, s+score[index], c+calories[index]);
		dfs(index+1, s, c);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				score[i] = Integer.parseInt(st.nextToken());
				calories[i] = Integer.parseInt(st.nextToken());
			}
			
			result = 0;
			dfs(0, 0, 0);
			
			output.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(output);
	}
}
