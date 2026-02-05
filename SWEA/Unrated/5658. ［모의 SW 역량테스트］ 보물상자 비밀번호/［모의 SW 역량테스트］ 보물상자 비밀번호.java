import java.io.*;
import java.util.*;

/*
 * 
 * 메모리: 26,368 kb / 실행시간: 89 ms
 * 
 * */

public class Solution {
	static int N, K;
	static int[] nums = new int[28]; // 만들 수 있는 숫자 넣을 배열
	static int numsIndex = 0; // 배열 인덱스
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder output = new StringBuilder();
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			StringBuilder input = new StringBuilder(br.readLine());
			input.append(input.substring(0, N/4));
			
			// 배열 초기화
			Arrays.fill(nums, 0);
			numsIndex = 0;
			
			for (int i=0; i<N; i++) {
				int num = 0;
				for (int j=i; j<i+N/4; j++) {
					int n = Character.isDigit(input.charAt(j)) ? input.charAt(j)-'0' : input.charAt(j)-'A'+10;
					num = num*16 + n;
				}
				nums[numsIndex++] = num;
			}
			
			int result = 0;
			Arrays.sort(nums);
			
			int order = 0;
			for (int i=27; i>=0; i--) {
				if (i!=27 && nums[i]==nums[i+1]) continue; // 중복제거
				order++;
				if (order==K) result = nums[i];
			}
			
			output.append("#").append(tc).append(" ")
			.append(result).append("\n");
		}
		System.out.println(output);
	}
}
