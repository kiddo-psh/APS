import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
	
	static class Node implements Comparable<Node>{
		int r, c, dist;
		Node (int r, int c, int dist) {
			this.r=r; this.c=c; this.dist=dist;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);
		}
	}
	
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	static int[][] graph, dist;
	static int N;
	static final int INF = 1_000_000_000;
	
	static void dijkstra() {
		dist = new int[N][N];
		for (int i=0; i<N; i++) Arrays.fill(dist[i], INF);
		
		dist[0][0] = 0;
		pq.offer(new Node(0,0,0));
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (cur.dist > dist[cur.r][cur.c]) continue;
			
			for (int dir=0; dir<4; dir++) {
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];
				
				if (!inRange(nr, nc)) continue;
				if (dist[nr][nc] <= cur.dist + graph[nr][nc]) continue;
				
				dist[nr][nc] = cur.dist + graph[nr][nc];
				pq.offer(new Node(nr, nc, dist[nr][nc]));
			}
		}
	}
	
	static final int[] dr = {-1,1,0,0};
	static final int[] dc = {0,0,-1,1};
	
	static boolean inRange(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			graph = new int[N][N];
			
			for (int i=0; i<N; i++) {
				String S = br.readLine();
				for (int j=0; j<N; j++) {
					graph[i][j] = S.charAt(j)-'0';
				}
			}
			
			dijkstra();
			output.append("#").append(tc).append(" ").append(dist[N-1][N-1]).append("\n");
		}
		System.out.println(output);
		br.close();
	}
}
