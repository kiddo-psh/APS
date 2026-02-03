import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			// 입력부
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[] snacks = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				snacks[i] = Integer.parseInt(st.nextToken());
			}
			
			// 정렬
			Arrays.sort(snacks);
			
			// 투포인터
			int head = 0, tail = N-1;
			int answer = -1;
			while (head < tail) {
				int sum = snacks[head] + snacks[tail];
				if (sum > M) {
					tail--;
				} else if (sum < M) {
					answer = Math.max(answer, sum);
					head++;
				} else {
					answer = sum;
					break;
				}
			}
			
			// 출력값 세팅
			output.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.print(output);
	}
}
