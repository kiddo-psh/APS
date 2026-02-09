import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 
 * 메모리 28,868 kb
 * 실행시간 235 ms
 * 
 * 
 */

public class Solution {
	static int N, result;
	static int maxVal, minVal;
	static int[] operator = new int[4];
	static int[] nums;
	
	static void dfs(int index, int num) {
		if (index==N-1) {
			maxVal = Math.max(maxVal, num);
			minVal = Math.min(minVal, num);
			return;
		}
		
		for (int i=0; i<4; i++) {
			if (operator[i] > 0) {
				operator[i]--;
				
				if (i==0)
					dfs(index+1, num+nums[index+1]);
				else if (i==1)
					dfs(index+1, num-nums[index+1]);
				else if (i==2)
					dfs(index+1, num*nums[index+1]);
				else if (i==3)
					dfs(index+1, num/nums[index+1]);
				
				operator[i]++;
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=0; i<4; i++) {
				operator[i] = Integer.parseInt(st.nextToken());
			}
			
			nums = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			maxVal = Integer.MIN_VALUE;
			minVal = Integer.MAX_VALUE;
			dfs(0, nums[0]);
			
			result = maxVal - minVal;
			
			output.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(output);
	}
}
