import java.util.Scanner;

/*
 * 
 * 메모리: 101,548 kb
 * 실행시간: 646 ms
 * 
 * 
 */

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc=1; tc<=T; tc++) {
			int N = sc.nextInt();
			int l = sc.nextInt();
			int r;

			int left = 0; int right = 0;
			int result = 0;
			for (int i=0; i<N-1; i++) {
				r = sc.nextInt();
				if (l < r) {
					if (right != 0) {
						result += left*right;
						left = 0; right = 0;
					} 
					left++;
				} else {
					right++;
				}
				l = r;
			}
			
			result += left*right;
			
			System.out.println("#" + tc + " " + result);
		}
	}
}
