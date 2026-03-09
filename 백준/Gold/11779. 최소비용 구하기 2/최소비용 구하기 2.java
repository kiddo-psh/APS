import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	
	static List<Edge>[] adj;
	static int[] dist, parent;
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	static class Edge {
		int to, w;
		Edge(int to, int w) {this.to=to; this.w=w;}
	}
	
	static class Node implements Comparable<Node>{
		int v, dist;
		Node(int v, int dist) {this.v=v; this.dist=dist;}
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		dist = new int[N+1];
		parent = new int[N+1];
		adj = new ArrayList[N+1];
		for (int i=1; i<=N; i++) adj[i] = new ArrayList<>();
		
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adj[u].add(new Edge(v,w));
		}
		
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		pq.offer(new Node(S,0));
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[S] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (cur.dist > dist[cur.v]) continue;
			if (cur.v == D) break;
			
			for (Edge e : adj[cur.v]) {
				int next = e.to;
				int nextDist = cur.dist + e.w;
				
				if (nextDist >= dist[next]) continue;
				
				dist[next] = nextDist;
				parent[next] = cur.v;
				pq.offer(new Node(next, nextDist));
			}
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		Stack<Integer> stack = new Stack<>();
		q.offer(D);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			stack.push(cur);
			
			if (parent[cur] == 0) continue;
			
			q.offer(parent[cur]);
		}
		
		StringBuilder output = new StringBuilder();
		output.append(dist[D]).append("\n")
		.append(stack.size()).append("\n");
		
		while(!stack.isEmpty()) {
			output.append(stack.pop()).append(" ");
		}
		
		System.out.println(output);
		br.close();
		
	}
}
