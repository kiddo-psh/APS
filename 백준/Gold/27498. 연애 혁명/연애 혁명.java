import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	
	static int[] parent, rank;
	
	static int find (int x) {
		if(x==parent[x]) return x;
		return parent[x] = find(parent[x]);
	}
	
	static boolean union (int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a==b) return false;
		
		if (rank[a] < rank[b]) {int t=a; a=b; b=t;}
		
		parent[b] = a;
		if(rank[a]==rank[b]) rank[a]++;
		
		return true;
	}
	
	static class Edge implements Comparable<Edge>{
		int from, to, cost;
		Edge(int from, int to, int cost) {
			this.from=from;
			this.to=to;
			this.cost=cost;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(o.cost, this.cost);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		for (int i=1; i<=N; i++) parent[i] = i;
		rank = new int[N+1];
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int ans = 0;
		int total = 0;
		
		while (M-->0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			total+=c;
			
			if (d==1) {
				union(a,b);
				ans += c;
			} else {
				pq.add(new Edge(a, b, c));
			}
		}
		
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			int a = e.from;
			int b = e.to;
			
			if (union(a,b)) {
				ans += e.cost;
			}
		}
		
		System.out.println(total-ans);
		
		br.close();
	}
}
