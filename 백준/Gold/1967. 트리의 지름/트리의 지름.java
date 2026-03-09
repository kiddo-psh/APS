import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, farthestNode, maxDist;
	static List<Edge>[] adj;
	
	static class Edge{
		int to, w;
		Edge(int to, int w) {this.to=to; this.w=w;}
	}
	
	static void dfs(int u, int p, int dist) {
		if (maxDist <= dist) {
			maxDist = dist;
			farthestNode = u;
		}
		
		for (Edge e : adj[u]) {
			int v = e.to;
			if (v==p) continue;
			
			dfs(v,u,dist+e.w);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[N+1];
		for (int i=1; i<=N; i++) adj[i] = new ArrayList<>();
		
		for (int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adj[u].add(new Edge(v, w));
			adj[v].add(new Edge(u, w));
		}
		
		dfs(1,0,0);
		dfs(farthestNode,0,0);
		
		System.out.println(maxDist);
		br.close();
	}
}
