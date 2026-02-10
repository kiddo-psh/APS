import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.BreakIterator;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] pass = new int[4];
	static int[] plan = new int[13];
	static int[] dp = new int[13];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			// 입력부
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=0; i<4; i++) {
				pass[i] = Integer.parseInt(st.nextToken()); 
			}
			st = new StringTokenizer(br.readLine());
			for (int i=1; i<=12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
				dp[i] = pass[3]; // 1년권 가격으로 초기화
			}
			
			for (int i=0; i<12; i++) {
				// 일일권/한달권 비교 
				dp[i+1] = Math.min(dp[i+1], 
						Math.min(dp[i] + plan[i+1]*pass[0], dp[i] + pass[1]));
				
				// 3개월권을 사는 경우
				if (i+3 <= 12) {
					dp[i+3] = Math.min(dp[i]+pass[2], dp[i+3]);
				}
			}
			
			output.append("#").append(tc).append(" ").append(dp[12]).append("\n");
		}
		System.out.println(output);
	}
}
