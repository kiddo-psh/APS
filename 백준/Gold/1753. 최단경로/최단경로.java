import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Edge implements Comparable<Edge>{
		int to, w;
		Edge(int to, int w) {this.to=to; this.w=w;}
		
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
	
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	
	static int V, E, ROOT;
	static int[] dist;
	static List<Edge>[] adj;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		dist = new int[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
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
		
		dist[ROOT] = 0;
		pq.offer(new Edge(ROOT, 0));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			int u = cur.to;
			
			for (Edge next : adj[u]) {
				int v = next.to;
				int cost = cur.w + next.w;
				
				if (dist[v] <= cost) continue;
				
				dist[v] = cost;
				pq.offer(new Edge(v, cost));
			}
		}
		StringBuilder output = new StringBuilder();
		for (int i=1; i<=V; i++) {
			output.append((dist[i]==Integer.MAX_VALUE ? "INF" : dist[i])).append("\n");
		}
		System.out.println(output);
		br.close();
	}
}
