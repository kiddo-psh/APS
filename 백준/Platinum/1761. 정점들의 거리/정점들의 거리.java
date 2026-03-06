import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, LOG;
	static final int ROOT = 1;

	static List<Edge>[] adj;
	static int[][] parent;
	static int[] depth, dist;
	
	static class Edge{
		int to, w;
		public Edge(int to, int w) {this.to=to;this.w=w;}
	}
	
	static void dfs(int u, int p) {
		parent[u][0] = p;
		
		for (Edge e : adj[u]) {
			int v = e.to;
			if (v==p) continue;
			
			dist[v] = dist[u] + e.w;
			depth[v] = depth[u]+1;
			dfs(v,u);
		}
	}

	static void fillParent() {
		for (int j=1; j<LOG; j++) {
			for (int i=1; i<=N; i++) {
				parent[i][j] = parent[parent[i][j-1]][j-1];
			}
		}
	}
	
	static int getLCA(int u, int v) {
		if (depth[u] < depth[v]) {
			int tmp = u;
			u = v;
			v = tmp;
		}
		
		int diff = depth[u] - depth[v];
		for (int j=0; j<LOG; j++) {
			if ((diff & 1<<j)!=0) {
				u = parent[u][j];
			}
		}
		
		if (u==v) return u;
		
		for (int j=LOG-1; j>=0; j--) {
			if (parent[u][j] != parent[v][j]) {
				u = parent[u][j];
				v = parent[v][j];
			}
		}
		
		return parent[u][0];
	}
	
	static int getDist(int u, int v) {
		return dist[u] + dist[v] - 2*dist[getLCA(u, v)];
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N+1];
		for (int i=1; i<=N; i++) adj[i] = new ArrayList<>();
		
		LOG = (int)(Math.log(N)/Math.log(2))+1;
		depth = new int[N+1];
		dist = new int[N+1];
		parent = new int[N+1][LOG];
		
		for (int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adj[u].add(new Edge(v,w));
			adj[v].add(new Edge(u,w));
		}
		
		dist[ROOT] = 0;
		depth[ROOT] = 0;
		dfs(ROOT, 0);
		fillParent();
		
		M = Integer.parseInt(br.readLine());
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			output.append(getDist(u,v)).append("\n");
		}
		System.out.println(output);
		br.close();
	}
}
