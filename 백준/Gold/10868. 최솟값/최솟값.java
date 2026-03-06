import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] num, tree;
	
	static void build(int node, int start, int end) {
		if (start==end) {
			tree[node] = num[start];
			return;
		}
		
		int mid = (start+end)/2;
		build(node*2, start, mid);
		build(node*2+1, mid+1, end);
		
		tree[node] = Math.min(tree[node*2], tree[node*2+1]);
	}
	
	static int query(int node, int start, int end, int l, int r) {
		if (r < start || end < l) return Integer.MAX_VALUE;
		if (l <= start && end <= r) {
			return tree[node];
		}
		
		int mid = (start+end)/2;
		return Math.min(query(node*2,start,mid,l,r), query(node*2+1,mid+1,end,l,r));
	}
	 
	public static void main(String[] args) throws IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		num = new int[N+1];
		tree = new int[4*N];
		
		for (int i=1; i<=N; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		
		build(1,1,N);
		
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			output.append(query(1,1,N,l,r)).append("\n");
		}
		
		System.out.println(output);
		br.close();
	}
}
