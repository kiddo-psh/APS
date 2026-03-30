import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	
	static class Edge {
		int to, w;
		Edge(int to, int w) {this.to=to; this.w=w;}
	}
	
	static int[] depth;
	static long[] dist;
	static int[][] parent;
	static int[][] minEdge, maxEdge;
	static int LOG;
	static final int ROOT = 1;
	
	static List<Edge>[] adj;
	
	static void dfs(int u, int p) {
		parent[u][0] = p;
		
		for (Edge e : adj[u]) {
			int v = e.to;
			if (v==p) continue;
			
			depth[v] = depth[u]+1;
			dist[v] = dist[u] + e.w;
			
			minEdge[v][0] = e.w;
			maxEdge[v][0] = e.w;
			
			dfs(v, u);
		}
	}
	
	static void fillParent() {
		for (int j=1; j<LOG; j++) {
			for (int i=1; i<=N; i++) {
				parent[i][j] = parent[parent[i][j-1]][j-1];
				minEdge[i][j] = Math.min(minEdge[i][j-1], minEdge[parent[i][j-1]][j-1]);
				maxEdge[i][j] = Math.max(maxEdge[i][j-1], maxEdge[parent[i][j-1]][j-1]);
			}
		}
	}
	
	static int[] query(int u, int v) {
		int min = Integer.MAX_VALUE;
		int max = 0;
		
		if (depth[u] < depth[v]) {
			int t=u; u=v; v=t;
		}
		
		int diff = depth[u] - depth[v];
		for (int j=0; j<LOG; j++) {
			if ((diff & (1<<j)) != 0) {
				min = Math.min(min, minEdge[u][j]);
				max = Math.max(max, maxEdge[u][j]);
				u = parent[u][j];
			}
		}
		
		if (u==v) return new int[] {min, max};
		
		for (int j=LOG-1; j>=0; j--) {
			if (parent[u][j] != parent[v][j]) {
				min = Math.min(min, minEdge[u][j]);
				min = Math.min(min, minEdge[v][j]);
				
				max = Math.max(max, maxEdge[u][j]);
				max = Math.max(max, maxEdge[v][j]);
				
				u = parent[u][j];
				v = parent[v][j];
			}
		}
		
		min = Math.min(min, minEdge[u][0]);
		min = Math.min(min, minEdge[v][0]);
		
		max = Math.max(max, maxEdge[u][0]);
		max = Math.max(max, maxEdge[v][0]);
		
		return new int[] {min, max};
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder output = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		LOG = (int)(Math.log(N+1)/Math.log(2))+1;
		
		adj = new ArrayList[N+1];
		for (int i=1; i<=N; i++) adj[i] = new ArrayList<>();
		
		depth = new int[N+1];
		dist = new long[N+1];
		parent = new int[N+1][LOG];
		minEdge = new int[N+1][LOG];
		maxEdge = new int[N+1][LOG];
		
		dist[ROOT] = 0;
		depth[ROOT] = 1;
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			adj[A].add(new Edge(B,C));
			adj[B].add(new Edge(A,C));
		}
		
		dfs(ROOT, 0);
		fillParent();
		
		K = Integer.parseInt(br.readLine());
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int D = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			int[] ans = query(D, E);
			
			output.append(ans[0]).append(" ").append(ans[1]).append("\n");
		}
		System.out.println(output);
		br.close();
	}
}
