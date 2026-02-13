import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	
	static long N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			N = Long.parseLong(br.readLine());
			int count = 0;
			long next;
			long rtN = (long)Math.sqrt(N);
			
			if (N == rtN*rtN) next = N;
			else next = (rtN+1)*(rtN+1);
			
			while (N != 2) {
				count += next - N + 1;
				
				N = (long)Math.sqrt(next);
				rtN = (long)Math.sqrt(N);
				
				if (N == rtN*rtN) next = N;
				else next = (rtN+1)*(rtN+1);
			}
			output.append("#").append(tc).append(" ")
			.append(count).append("\n");
		}
		System.out.println(output);
	}
}
