import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[] corridor = new int[201];
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			Arrays.fill(corridor, 0);
			
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (a>b) {int temp = a; a = b; b = temp;}
				
				int start = (a+1)/2;
				int end = (b+1)/2;
				
				for (int j=start; j<=end; j++) {
					corridor[j]++;
				}
			}
			int answer = 0;
			for (int i=0; i<201; i++) {
				answer = Math.max(answer, corridor[i]);
			}
			
			output.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(output);
	} 
}
