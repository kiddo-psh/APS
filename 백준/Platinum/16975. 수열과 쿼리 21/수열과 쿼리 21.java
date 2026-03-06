import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] num;
	static long[] tree, lazy;
	
	static void build(int node, int start, int end) {
		if (start==end) {
			tree[node] = (long)num[start];
			return;
		}
		
		int mid = (start+end)>>1;
		build(node*2, start, mid);
		build(node*2+1, mid+1, end);
		
		tree[node] = tree[node*2] + tree[node*2+1];
	}
	
	static void push(int node, int start, int end) {
		if (lazy[node]==0) return;
		
		long val = lazy[node];
		tree[node] += val * (end-start+1);
		
		if (start!=end) {
			lazy[node*2] += val;
			lazy[node*2+1] += val;
		}
		
		lazy[node] = 0;
	}
	
	static void update(int node, int start, int end, int l, int r, long value) {
		push(node, start, end); // 이전에 미뤄놨던 값 갱신
		
		if (r<start || l>end) return;
		if (l<=start && r>=end) { // 구간 포함할 때
			lazy[node] += value; // 갱신할 값 넣고
			push(node, start, end); // 바로 갱신 + 자식갱신미루기
			return;
		}
		
		int mid = (start+end)>>1;
		update(node*2, start, mid, l, r, value);
		update(node*2+1, mid+1, end, l, r, value);
		
		tree[node] = tree[node*2] + tree[node*2+1];
	}
	
	static long query(int node, int start, int end, int l, int r) {
		push(node, start, end);
		
		if (r<start || l>end) return 0;
		if (l<=start && r>=end) return tree[node];
		
		int mid = (start+end)>>1;
		return query(node*2,start,mid,l,r) + query(node*2+1,mid+1,end,l,r);
	}
	
	public static void main(String[] args) throws IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		num = new int[N+1];
		tree = new long[4*N];
		lazy = new long[4*N];
		
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		build(1, 1, N);
		
		M = Integer.parseInt(br.readLine());
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			
			if (q==1) {
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				update(1,1,N,i,j,k);
			} else {
				int x = Integer.parseInt(st.nextToken());
				output.append(query(1,1,N,x,x)).append("\n");
			}
		}
		System.out.println(output);
		br.close();
	}
}
