import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {
	static int N, K;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			int len = N/4;
			char[] nums = new char[N+N/4];
			TreeSet<Integer> numSet = new TreeSet<>();
			
			String S = br.readLine();
			for (int i=0; i<N; i++) {
				nums[i] = S.charAt(i);
			}
			for (int i=N; i<N+N/4; i++) {
				nums[i] = nums[i-N];
			}
			
			StringBuilder sb = new StringBuilder();
			for (int i=0; i<len; i++) {
				for (int j=i; j<i+N; j++) {
					 if (sb.length()==len) {
						 int x = Integer.parseInt(sb.toString(), 16);
						 numSet.add(x);
						 sb.delete(0, sb.length());
					 }
					 sb.append(nums[j]);
				}
			}
			int x = Integer.parseInt(sb.toString(), 16);
			numSet.add(x);
			
			Iterator<Integer> it = numSet.descendingIterator();
			int val = -1;
			
			for (int i=0; i<K; i++) {
				val = it.next();
			}
			
			output.append("#").append(tc).append(" ").append(val).append("\n");
		}
		System.out.println(output);
		br.close();
	}
}
