import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static long[] num;
	static long[] tree;
	
	static long init(int start, int end, int node) {
		if (start == end) return tree[node] = num[start];
		int mid = (start + end) / 2;
		
		return tree[node] = init(start, mid, node*2) + init(mid+1, end, node*2+1);
	}
	
	static long sum(int start, int end, int node, int left, int right) {
		if (left > end || right < start) return 0;
		if (left <= start && right >= end) return tree[node];
		
		int mid = (start + end) / 2;
		return sum(start, mid, node*2, left, right) + sum(mid+1, end, node*2+1, left, right);
	}
	
	static void change(int start, int end, int node, int index, long diff) {
		if (index < start || index > end) return;
		
		tree[node] += diff;
		if (start == end) return;
		int mid = (start + end) / 2;
		change(start, mid, node*2, index, diff);
		change(mid+1, end, node*2+1, index, diff);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		num = new long[N];
		tree = new long[4*N];
		
		for (int i=0; i<N; i++) {
			num[i] = Long.parseLong(br.readLine());
		}
		
		init(0, N-1, 1);
		
		for (int i=0; i<M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			
			if (a == 1) {
				int idx = b - 1;
				long diff = c - num[idx];
				num[idx] = c;
				change(0, N-1, 1, idx, diff);
			} else {
				int left = b - 1;
				int right = (int)c - 1;
				System.out.println(sum(0, N-1, 1, left, right));
			}
		}
	}
}
