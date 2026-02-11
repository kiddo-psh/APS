import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc=1; tc<=T; tc++) {
			int N = sc.nextInt();
			int[] mountains = new int[N];
			for (int i=0; i<N; i++) {
				mountains[i] = sc.nextInt();
			}
			
			int left = 0; int right = 0;
			int result = 0;
			
			for (int i=0; i<N-1; i++) {
				if (mountains[i] < mountains[i+1]) {
					if (right != 0) {
						result += left*right;
						left = 0; right = 0;
					} 
					left++;
				} 
				else {
					right++;
				}
			}
			
			result += left*right;
			
			System.out.println("#" + tc + " " + result);
		}
	}
}
