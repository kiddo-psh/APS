import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int V, E;
	static int[] parent, rank;
	static Edge[] edges;
	
	static class Edge implements Comparable<Edge> {
		int from, to, w;
		Edge(int from, int to, int w) {this.from=from; this.to=to; this.w=w;}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	static int find(int x) {
		if (x==parent[x]) return x;
		return parent[x] = find(parent[x]);
	}
	
	static boolean union(int a, int b) {
		a = find(a); b = find(b);
		if (a==b) return false;
		
		if (rank[a] < rank[b]) {int t=a; a=b; b=t;}
		
		parent[b] = a;
		if (rank[a] == rank[b]) rank[a]++;
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		StringTokenizer st; 
		
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			rank = new int[V+1];
			edges = new Edge[E];
			parent = new int[V+1];
			for (int i=1; i<=V; i++) parent[i] = i;			
			
			for (int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				
				edges[i] = new Edge(u,v,w);
			}
			
			Arrays.sort(edges);
			
			int connected = 0;
			long answer = 0;
			for (int i=0; i<E; i++) {
				Edge e = edges[i];
				
				if (union(e.from, e.to)) {
					connected++;
					answer += (long)e.w;
				}
				
				if (connected == V-1) break;
			}
			
			output.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.print(output);
		br.close();
	}
}
