import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] parent;
	static int[][] arr;
	static PriorityQueue<Edge> pq;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// a ==> 97
		// z ==> 122
		// A ==> 65
		// Z ==> 90
		
		pq = new PriorityQueue<>();
		
		parent = new int[N];
		for (int i = 0; i < N; i++) parent[i] = i;
		
		int total = 0;
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				
				char c = str.charAt(j);
				
				if (c >= 97 && c <= 122) {
					int w = (int) c - 96;
					total += w;
					arr[i][j] = w;
				}
				
				if (c >= 65 && c <= 90) {
					int w = ((int) c - 65) + 27;
					total += w;
					arr[i][j] = w;
				}
			}
		}
		
		
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (i == j) continue;
				if (arr[i][j] == 0) continue;
				pq.add(new Edge(i, j, arr[i][j]));
			}
		}
		
//		System.out.println(pq);
		
		int cnt = 0;
		int cost = 0;
		while (!pq.isEmpty()) {
			
			Edge e = pq.poll();
			
//			System.out.println(find(e.from));
//			System.out.println(find(e.to));
			if (find(e.from) != find(e.to)) {
//				System.out.println(e.from + ", " + e.to);
				union(e.from, e.to);
				cnt++;
				cost += e.w;
			}
			
			if (cnt == N - 1) break;
			
		}
		
//		for (int i = 0; i < parent.length; i++) System.out.println(parent[i]);
		
		if (cnt == N - 1) System.out.println(total - cost);
		else System.out.println(-1);
		
	
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a != b) parent[b] = a;
	}
	
	static int find(int n) {
		if (parent[n] == n) return n;
		else {
			return parent[n] = find(parent[n]);
		}
	}
	
	
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int w;
		
		public Edge(int from, int to, int w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
		
		@Override
		public String toString() {
			return "Edge [from= " + from + ", to= " + to + ", w= " + w + "]";
		}
	}

}
