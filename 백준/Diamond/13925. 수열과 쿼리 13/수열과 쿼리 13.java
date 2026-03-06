import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static final int MOD = 1_000_000_007;
	static int[] num;
	static long[] tree, lazyMul, lazyAdd;
	
	static void build(int node, int start, int end) {
		if (start==end) {
			tree[node] = num[start];
			return;
		}
		
		int mid = (start+end)/2;
		build(node*2, start, mid);
		build(node*2+1, mid+1, end);
		
		tree[node] = (tree[node*2] + tree[node*2+1])%MOD;
	}
	
	static void push(int node, int start, int end) { // 노드의 lazy값을 처리함. 자식노드로 lazy값 넘김 
		if (lazyMul[node]==1 && lazyAdd[node]==0) return;
		
		long mul = lazyMul[node];
		long add = lazyAdd[node];
		int len = end-start+1;
		
		tree[node] = (tree[node] * mul % MOD + add * len % MOD) % MOD;
		if (start!=end) {
			int left = node*2;
			int right = node*2+1;
			
			lazyMul[left] = (mul * lazyMul[left])%MOD;
			lazyAdd[left] = (mul * lazyAdd[left] + add)%MOD;
			
			lazyMul[right] = (mul * lazyMul[right])%MOD;
			lazyAdd[right] = (mul * lazyAdd[right] + add)%MOD;
		}
		
		lazyMul[node] = 1;
		lazyAdd[node] = 0;
	}
	
	static void update(int node, int start, int end, int l, int r, long v, int q) {
		push (node, start, end);
		
		if (r<start || l>end) return;
		if (l <= start && end <= r) {
			if(q==1) {
				lazyAdd[node] = (lazyAdd[node]+v)%MOD;
			} else if (q==2) {
				lazyMul[node] = (lazyMul[node]*v)%MOD;
				lazyAdd[node] = (lazyAdd[node]*v)%MOD;
			} else if (q==3) {
				lazyMul[node] = 0;
				lazyAdd[node] = v;
			}
			push(node,start,end);
			return;
		}
		
		int mid = (start+end)/2;
		update(node*2, start, mid, l, r, v, q);
		update(node*2+1, mid+1, end, l, r, v, q);
		
		tree[node] = (tree[node*2] + tree[node*2+1])%MOD;
	}
	
	static long query(int node, int start, int end, int l, int r) {
		push(node, start, end);
		
		if (r < start || end < l) return 0;
		if (l <= start && end <= r) {
			return tree[node];
		}
		
		int mid = (start+end)/2;
		return (query(node*2, start, mid, l, r) + query(node*2+1, mid+1, end, l, r))%MOD;
	}
	
	public static void main(String[] args) throws IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		num = new int[N+1];
		tree = new long[4*N];
		lazyMul = new long[4*N];
		Arrays.fill(lazyMul, 1);
		lazyAdd = new long[4*N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		build(1,1,N);
		
		M = Integer.parseInt(br.readLine());
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if (q==4) {
				output.append(query(1,1,N,x,y)).append("\n");
				
			} else {
				int v = Integer.parseInt(st.nextToken());
				update(1,1,N,x,y,v,q);
			}
		}
		System.out.println(output);
		br.close();
	}
}
