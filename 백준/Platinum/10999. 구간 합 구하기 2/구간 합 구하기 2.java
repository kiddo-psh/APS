import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,M,K;
	static long[] num, tree, lazy;
	
	static void build(int node, int start, int end) {
		if (start==end) {
			tree[node] = num[start];
			return;
		}
		
		int mid = (start+end)/2;
		build(node*2, start, mid);
		build(node*2+1, mid+1, end);
		
		tree[node] = tree[node*2] + tree[node*2+1];
	}
	
	static void push(int node, int start, int end) {
		if (lazy[node]==0) return;
		
		long val = lazy[node];
		tree[node] += val*(end-start+1);
		
		if (start!=end) {
			lazy[node*2] += val;
			lazy[node*2+1] += val;
		}
		
		lazy[node] = 0;
	}
	
	static void update(int node, int start, int end, int l, int r, long val) {
		push(node, start, end);
		
		if (r<start || l>end) return;
		if (l<=start && r>=end) {
			lazy[node] += val;
			push(node, start, end);
			return;
		}
		
		int mid = (start+end)/2;
		update(node*2, start, mid, l, r, val);
		update(node*2+1, mid+1, end, l, r, val);
		
		tree[node] = tree[node*2] + tree[node*2+1];
	}
	
	static long query(int node, int start, int end, int l, int r) {
		push(node, start, end);
		
		if (r<start || l>end) return 0;
		if (l<=start && r>=end) {
			return tree[node];
		}
		
		int mid = (start+end)/2;
		return query(node*2,start,mid,l,r) + query(node*2+1,mid+1,end,l,r);	
	}
	
	
	public static void main(String[] args) throws IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		num = new long[N+1];
		tree = new long[4*N];
		lazy = new long[4*N];
		
		for (int i=1; i<=N; i++) {
			num[i] = Long.parseLong(br.readLine());
		}
		
		build(1,1,N);
		
		for (int i=0; i<M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			
			if (q==1) { // 업데이트
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				long val = Long.parseLong(st.nextToken());
				update(1,1,N,l,r,val);
				
			} else { // 구간합
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				output.append(query(1,1,N,l,r)).append("\n");
			}
		}
		System.out.println(output);
		br.close();
	}
}
