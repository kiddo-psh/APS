import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] trees;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		trees = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(upperbound());
		
		br.close();
	}
	
	static int upperbound() {
		int left = 0;
		int right = 1_000_000_000;
		int maxHeight = -1;
		
		while (left<=right) {
			int mid = (left+right)/2;
			if (check(mid)) {
				left = mid + 1;
				maxHeight = Math.max(maxHeight, mid);
			} else {
				right = mid - 1;
			}
		}
		
		return maxHeight;
	}
	
	static boolean check(int h) {
		long sum = 0;
		for (int i=0; i<N; i++) {
			sum += trees[i]>h ? (long)(trees[i] - h) : 0;
		}
		return sum >= M;
	}
}
