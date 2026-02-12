import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 
 * 
 * 
 * 
 * 
 */

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] trees = new int[N];
			int maxH = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				maxH = Math.max(trees[i], maxH);
			}
			
			int even = 0, odd = 0;
			for (int h : trees) {
				even += (maxH-h)/2;
				odd += (maxH-h)%2;
			}
			
			if (even > odd) {
				int move = (even-odd)/3;
				even -= move;
				odd += move*2;
				
				if (even-odd == 2) {
					even--;
					odd+=2;
				}
			}
			
			int result = 0;
			if (even < odd) result = odd*2-1;
			else if (even > odd) result = even*2;
			else result = even+odd;
			
			output.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(output);
	}
}
