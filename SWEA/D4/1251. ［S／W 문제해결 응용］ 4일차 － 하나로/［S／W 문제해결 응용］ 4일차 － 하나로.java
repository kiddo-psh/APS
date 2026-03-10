import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static double E;
	static int[] x, y;
	
	static int[] parent, rank;
	
	static class Edge implements Comparable<Edge> {
		int from, to;
		double cost;
		
		Edge(int from, int to, double cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.cost, o.cost);
		}
	}
	
	static List<Edge> edges = new ArrayList<>();
	
	static void makeEdges() {
		for (int i=0; i<N-1; i++) {
			for (int j=i+1; j<N; j++) {
				edges.add(new Edge(i, j, getCost(y[i], x[i], y[j], x[j])));
			}
		}
	}
	
	static int find(int x) {
		if(x==parent[x]) return x;
		
		return parent[x] = find(parent[x]);
	}
	
	static boolean union(int a, int b) {
		a = find(a); b = find(b);
		
		if (a==b) return false;
		
		if (rank[a]<rank[b]) {int t=a; a=b; b=t;}
		
		parent[b] = a;
		if (rank[a] == rank[b]) rank[a]++;
		
		return true;
	}
	
	static double getCost(int sy, int sx, int ey, int ex) {
		double a = (double)(sy-ey);
		double b = (double)(sx-ex);
		return  E*(a*a+b*b);
	}
	
	public static void main(String[] args) throws IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			input(br);
			makeEdges();
			Collections.sort(edges);
			
			double d = 0;
			int cnt = 0;
			for (Edge e : edges) {
				if (union(e.from, e.to)) {
					cnt++;
					d += e.cost;
					
					if (cnt == N-1) break;
				}
			}
			long answer = Math.round(d);
			output.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(output);
		br.close();
	}
	
	static void input(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		parent = new int[N];
		for (int i=0; i<N; i++) parent[i] = i;
		
		edges.clear();
		
		x = new int[N];
		y = new int[N];
		rank = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			x[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			y[i] = Integer.parseInt(st.nextToken());
		}
		
		E = Double.parseDouble(br.readLine());
	}
}
