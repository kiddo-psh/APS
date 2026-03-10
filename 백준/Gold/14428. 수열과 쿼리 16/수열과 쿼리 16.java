import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] num, tree;
	static final int INF = Integer.MAX_VALUE;
	
	static int better(int a, int b) {
		if (a==-1) return b;
		if (b==-1) return a;
		if (num[a] > num[b]) return b;
		if (num[a] < num[b]) return a;
		return Math.min(a, b);
	}
	
	static void build(int node, int start, int end) {
		if (start == end) {
			tree[node] = start;
			return;
		}
		
		int mid = (start+end)/2;
		build(node*2, start, mid);
		build(node*2+1, mid+1, end);
		
		tree[node] = better(tree[node*2], tree[node*2+1]);
	}
	
	static void update(int node, int start, int end, int target) {
		if (target < start || end < target) return;
		if (start==end) {
			tree[node] = start;
			return;
		}
		
		int mid = (start+end)/2;
		update(node*2, start, mid, target);
		update(node*2+1, mid+1, end, target);
		
		tree[node] = better(tree[node*2], tree[node*2+1]);
	}
	
	static int getMin(int node, int start, int end, int left, int right) {
		if (right < start || end < left) return -1;
		if (left <= start && end <= right) {
			return tree[node];
		}
		
		int mid = (start+end)/2;
		int l = getMin(node*2, start, mid, left, right);
		int r = getMin(node*2+1, mid+1, end, left, right);
		
		return better(l,r);
	}
	
	public static void main(String[] args) throws IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		num = new int[N+1];
		tree = new int[4*N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		build(1,1,N);
		
		M = Integer.parseInt(br.readLine());
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			int Q = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			
			if (Q==1) {
				num[L] = R;
				update(1,1,N,L);
			} else {
				output.append(getMin(1,1,N,L,R)).append("\n");
			}
		}
		System.out.println(output);
		br.close();
	}
}
