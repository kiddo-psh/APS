import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] rooms = new int[1000*1000+1];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					int num = Integer.parseInt(st.nextToken());
					rooms[num] = i*10000 + j;
				}
			}
			
			int len = 1;
			int maxLen = 1;
			int minNum = Integer.MAX_VALUE;
			
			for (int i=1; i<N*N; i++) {
				int r = rooms[i]/10000;
				int c = rooms[i]%10000;
				
				int nr = rooms[i+1]/10000;
				int nc = rooms[i+1]%10000;
				
				if (Math.abs(nr-r) + Math.abs(nc-c) == 1) {
					len++;
				} else {
					len = 1;
				}
				
				if (maxLen < len) {
					maxLen = len;
					minNum = i+2-len; 
				}
			}
			
			output.append("#").append(tc).append(" ").
			append(minNum).append(" ").append(maxLen).append("\n");
			
		}
		System.out.println(output);
	}
}
