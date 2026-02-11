import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] farm = new int[N][N];
			for (int i=0; i<N; i++) {
				String S = br.readLine();
				for (int j=0; j<N; j++) {
					farm[i][j] = S.charAt(j)-'0';
				}
			}
			
			int t = N/2;
			int sum = 0;
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (Math.abs(i-t) + Math.abs(j-t) <= t) {
						sum += farm[i][j];
					}
				}
			} 
			
			output.append("#").append(tc).append(" ").append(sum).append("\n");
		}
		System.out.println(output);
	}
}
