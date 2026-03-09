import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int V, farthestNode, maxDist;
	
	static List<Edge>[] adj;
	
	static class Edge {
		int to, w;
		Edge(int to, int w) {this.to=to; this.w=w;}
	}
	
	static void dfs(int u, int p, int dist) {
		boolean hasChild = false;
		
		for (Edge e : adj[u]) {
			int v = e.to;
			if (v==p) continue;
			
			dfs(v,u,dist+e.w);
			hasChild=true;
		}
		
		if(!hasChild) {
			if (maxDist < dist) {
				maxDist = dist;
				farthestNode = u;
			}
			return;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		V = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[V+1];
		for (int i=1; i<=V; i++) adj[i] = new ArrayList<>();
		
		for (int i=1; i<=V; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			
			while(true) {
				int v = Integer.parseInt(st.nextToken());
				if (v==-1) break;
				int w = Integer.parseInt(st.nextToken());
				
				adj[u].add(new Edge(v, w));
			}
		}
		
		dfs(1, 0, 0);
		dfs(farthestNode, 0, 0);
		
		System.out.println(maxDist);
	}
}
