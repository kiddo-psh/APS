import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, k;
	static final int ROOT = 1;
	
	static class Edge {
		int to,w;
		Edge(int to, int w) {this.to=to; this.w=w;}
	}
	
	static class Node implements Comparable<Node> {
		int v, dist;
		Node (int v, int dist) {this.v=v; this.dist=dist;}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);
		}
	}
	
	static void dijkstra(int start) {
		dist[start].offer(0);
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int u = cur.v;
			
			for (Edge e : adj[u]) {
				int v = e.to;
				
				int nextDist = cur.dist + e.w;
				
				if (dist[v].size() < k) {
					dist[v].add(nextDist);
					pq.offer(new Node(v, nextDist));
					
				} else if (dist[v].peek() > nextDist) {
					dist[v].poll();
					dist[v].add(nextDist);
					pq.offer(new Node(v, nextDist));
				}
			}
		}
	}
	
	static List<Edge>[] adj;
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	static PriorityQueue<Integer>[] dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[n+1];
		for (int i=1; i<=n; i++) adj[i] = new ArrayList<>();
		
		dist = new PriorityQueue[n+1];
		for (int i=1; i<=n; i++) dist[i] = new PriorityQueue<>(Collections.reverseOrder());
		
		while(m-->0) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			adj[a].add(new Edge(b,c));
		}
		
		dijkstra(ROOT);
		
		StringBuilder output = new StringBuilder();
		for (int i=1; i<=n; i++) {
			if (dist[i].size() < k) output.append(-1).append("\n");
			else output.append(dist[i].peek()).append("\n");
		}
		System.out.println(output);
		br.close();
	}
}
