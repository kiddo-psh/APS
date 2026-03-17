import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, t, s, g, h, W;
	static List<Edge>[] adj;
	static List<Integer>[] parent;
	static List<Integer> answer = new ArrayList<>();
	static Queue<Integer> q = new ArrayDeque<>();
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	static class Edge{
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
	
	static int[] dijkstra(int start) {
		pq.clear();
		pq.offer(new Node(start, 0));
		
		int[] dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int u = cur.v;
			
			if (cur.dist > dist[u]) continue;
			
			for (Edge e : adj[u]) {
				int v = e.to;
				
				if (dist[v] > dist[u] + e.w) {
					parent[v].clear();
					parent[v].add(u);
					
					dist[v] = dist[u] + e.w;
					pq.offer(new Node(v, dist[v]));
				} 
				else if (dist[v] == dist[u] + e.w) {
					parent[v].add(u);
				}
			}
		}
		
		return dist;
	}
	
	public static void main(String[] args) throws IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			adj = new ArrayList[n+1];
			for (int i=1; i<=n; i++) adj[i] = new ArrayList<>();
			
			parent = new ArrayList[n+1];
			for (int i=1; i<=n; i++) parent[i] = new ArrayList<>();
			
			for (int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				adj[a].add(new Edge(b, d));
				adj[b].add(new Edge(a, d));
				
				if ((a==g && b==h) || (a==h && b==g)) W = d;
			}
			
			int[] distS = dijkstra(s);
			int[] distG = dijkstra(g);
			int[] distH = dijkstra(h);
			
			answer.clear();
			
			for (int i=0; i<t; i++) {
				int x = Integer.parseInt(br.readLine());
				if ((distS[x] == distS[g] + W + distH[x]) || (distS[x] == distS[h] + W + distG[x])) {
					answer.add(x);
				}
			}
			
			Collections.sort(answer);
			for (int x : answer) {
				output.append(x).append(" ");
			}
			output.append("\n");
		}
		System.out.println(output);
		
		br.close();
	}
}
