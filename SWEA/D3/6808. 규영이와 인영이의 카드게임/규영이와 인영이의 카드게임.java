import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int[] deck1 = new int[9];
	static int[] deck2 = new int[9];
	static int[] cards = new int[19];
	static int[] used = new int[9]; 
	
	static int tc;
	static int winCount;
	static int loseCount;
	
	// 1-규영 / 2-인영 
	static void dfs(int num, int point1, int point2) {
		if (num == 9) {
			if (point1 > point2) winCount++;
			else if (point1 < point2) loseCount++;
			return;
		}
		
		for (int i=0; i<9; i++) {
			if (used[i] == tc) continue;
			
			used[i] = tc;
			int getPoint = deck1[num] + deck2[i];
			if (deck1[num] > deck2[i]) {
				dfs (num+1, point1 + getPoint, point2);
			} else {
				dfs (num+1, point1, point2 + getPoint);
			}
			
			used[i] = 0;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			Arrays.fill(cards, 1);
			for (int i=0; i<9; i++) {
				deck1[i] = Integer.parseInt(st.nextToken());
				cards[deck1[i]] = 0;
			}
			
			int idx=0;
			for (int i=1; i<19; i++) {
				if(cards[i]>0) {
					deck2[idx++] = i;
				}
			}
			winCount = 0; loseCount = 0;
			dfs(0, 0, 0);
			
			output.append("#").append(tc).append(" ")
			.append(winCount).append(" ").append(loseCount).append("\n");
		}
		System.out.println(output);
	}
}
