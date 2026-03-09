import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, E, v1, v2;
	static int[] dist;
	static List<Edge>[] adj;
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	static class Edge {
		int to,w;
		Edge(int to, int w) {this.to=to; this.w=w;}
	}
	
	static class Node implements Comparable<Node> {
		int v,dist;
		Node(int v, int dist) {this.v=v; this.dist=dist;}
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);
		}
	}
	
	static int[] dijkstra(int start) {
		int[] dist = new int[N+1];
		
		pq.clear();
		pq.offer(new Node(start, 0));
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if (cur.dist > dist[cur.v]) continue;
			
			for (Edge e : adj[cur.v]) {
				int next = e.to;
				int nextDist = cur.dist + e.w;
				
				if (dist[next] <= nextDist) continue;
				
				dist[next] = nextDist;
				pq.offer(new Node(next, nextDist));
			}
		}
		
		return dist;
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N+1];
		for (int i=1; i<=N; i++) adj[i] = new ArrayList<>();
		dist = new int[N+1];
		
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adj[a].add(new Edge(b,w));
			adj[b].add(new Edge(a,w));
		}
		
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		int[] dist = dijkstra(1);
		int[] distV1 = dijkstra(v1);
		int[] distV2 = dijkstra(v2);
		
		long path1 = Long.MAX_VALUE;
		long path2 = Long.MAX_VALUE;

		if (dist[v1] != Integer.MAX_VALUE && distV1[v2] != Integer.MAX_VALUE && distV2[N] != Integer.MAX_VALUE) {
		    path1 = (long) dist[v1] + distV1[v2] + distV2[N];
		}

		if (dist[v2] != Integer.MAX_VALUE && distV2[v1] != Integer.MAX_VALUE && distV1[N] != Integer.MAX_VALUE) {
		    path2 = (long) dist[v2] + distV2[v1] + distV1[N];
		}

		long answer = Math.min(path1, path2);

		System.out.println(answer == Long.MAX_VALUE ? -1 : answer);
		
		br.close();
	}
}
