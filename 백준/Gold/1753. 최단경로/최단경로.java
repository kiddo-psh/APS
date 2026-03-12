import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	static class Edge {
		int to, w;
		Edge(int to, int w) {
			this.to=to; this.w=w;
		}
	}
	
	static int V, E, ROOT;
	static List<Edge>[] adj;
	static int[] minDist;
	static boolean[] visited;
	
	static final int INF = Integer.MAX_VALUE;
	
	static void dijkstra() {
		visited = new boolean[V+1];
		
		minDist = new int[V+1];
		Arrays.fill(minDist, INF);
		
		minDist[ROOT] = 0;
		
		for (int i=0; i<V; i++) {
			int u = -1;
			int min = INF;
			
			for (int j=1; j<=V; j++) {
				if (visited[j]) continue;
				if (minDist[j] < min) {
					min = minDist[j];
					u = j;
				}
			}
			
			if (u == -1) break;
			visited[u] = true;
			
			for (Edge e : adj[u]) {
				int v = e.to;
				if (minDist[v] > minDist[u] + e.w) {
					minDist[v] = minDist[u] + e.w;
				}
			}
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[V+1];
		for (int i=1; i<=V; i++) adj[i] = new ArrayList<>();
		
		ROOT = Integer.parseInt(br.readLine());
		
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adj[u].add(new Edge(v,w));
		}
		
		dijkstra();
		
		for (int i=1; i<=V; i++) {
			output.append(minDist[i]==INF? "INF" : minDist[i]).append("\n");
		}
		System.out.println(output);
		br.close();
	}
}
