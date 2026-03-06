import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] num, lazy;
	static Count[] tree;
	
	static class Count {
		int count0, count1;
		public Count(int count0, int count1) {
			this.count0 = count0;
			this.count1 = count1;
		}
	}
	
	static void build(int node, int start, int end) {
		if (start == end) {
			tree[node] = new Count(1,0);
			return;
		}
		
		int mid = (start+end)/2;
		build(node*2, start, mid);
		build(node*2+1, mid+1, end);
		
		int count = tree[node*2].count0 + tree[node*2+1].count0;
		tree[node] = new Count(count, 0);
	}
	
	static void push(int node, int start, int end) {
		if (lazy[node] == 0) return;
		
		int val = lazy[node];
		
		if (val%2==1) {
			int temp = tree[node].count0;
			tree[node].count0 = tree[node].count1;
			tree[node].count1 = temp;
		}
		
		if (start != end) {
			lazy[node*2]+=val;
			lazy[node*2+1]+=val;
		}
		
		lazy[node] = 0;
	}
	
	static void update(int node, int start, int end, int l, int r) {
		push(node, start, end);
		
		if (r<start || l>end) return;
		if (l <= start && r >= end) {
			lazy[node] += 1;
			push(node, start, end);
			return;
		}
		
		int mid = (start+end)/2;
		update(node*2, start, mid, l, r);
		update(node*2+1, mid+1, end, l, r);
		
		tree[node].count0 = tree[node*2].count0 + tree[node*2+1].count0;
		tree[node].count1 = tree[node*2].count1 + tree[node*2+1].count1;
	}
	
	static int query(int node, int start, int end, int l, int r) {
		push(node, start, end);
		
		if (r < start || l > end) return 0;
		if (l <= start && r >= end) return tree[node].count1;
		
		int mid = (start+end)/2;
		return query(node*2, start, mid, l, r) + query(node*2+1, mid+1, end, l, r);
	}
	
	public static void main(String[] args) throws IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		num = new int[N+1];
		tree = new Count[4*N];
		lazy = new int[4*N];
		
		build(1,1,N);
		
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			if(q==0) {
				update(1,1,N,l,r);
			} else {
				output.append(query(1, 1, N, l, r)).append("\n");
			}
		}
		
		System.out.println(output);
		br.close();
	}
}
