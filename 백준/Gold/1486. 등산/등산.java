import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int M;
	static int T;
	static int D;
	static int[][] map;
	static boolean[][] visited;
	static int[][] distance1;	
	static int[][] distance2;	// 호텔로 다시 돌아오는 distance 배열
	static int dr[] = { 1, -1, 0, 0 };
	static int dc[] = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		distance1 = new int[N][M];
		distance2 = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				
				char c = str.charAt(j);
				
				if (c >= 'A' && c <= 'Z') {
					map[i][j] = (int) c - 'A';
				}
				
				if (c >= 'a' && c <= 'z') {
					map[i][j] = (int) c - 'a' + 26;
				}
				
				distance1[i][j] = 1000001;
				distance2[i][j] = 1000001;
				
			}
		}
		
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		
//		System.out.println();
		
		dijkstra1(0, 0);
		visited = new boolean[N][M];
		dijkstra2(0, 0);
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print((distance1[i][j]) + " ");
//			}
//			System.out.println();
//		}
//		
//		System.out.println();
		
		int max = map[0][0];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int time = distance1[i][j] + distance2[i][j];
				if (time > D || time > 1000000) continue;
				max = Math.max(max, map[i][j]);
			}
		}
		
		System.out.println(max);
		
		
	}
	
	static void dijkstra1(int r, int c) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(r, c, 0));
		distance1[r][c] = 0;
		
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			if (visited[cur.r][cur.c]) continue;
			visited[cur.r][cur.c] = true;
			
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				
				int diff = Math.abs(map[cur.r][cur.c] - map[nr][nc]);
				if (diff > T) continue;
				
				if (map[cur.r][cur.c] >= map[nr][nc]) {
					if (distance1[nr][nc] > distance1[cur.r][cur.c] + 1) {
						distance1[nr][nc] = distance1[cur.r][cur.c] + 1;
						pq.add(new Edge(nr, nc, distance1[nr][nc]));
					}
					
				} else {
					int time = diff * diff;
					if (distance1[nr][nc] > distance1[cur.r][cur.c] + time) {
						distance1[nr][nc] = distance1[cur.r][cur.c] + time;
						pq.add(new Edge(nr, nc, distance1[nr][nc]));
					}
					
				}
			}
			
		}
	}
	
	static void dijkstra2(int r, int c) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(r, c, 0));
		distance2[r][c] = 0;
		
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			if (visited[cur.r][cur.c]) continue;
			visited[cur.r][cur.c] = true;
			
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				
				int diff = Math.abs(map[cur.r][cur.c] - map[nr][nc]);
				if (diff > T) continue;
				
				if (map[cur.r][cur.c] <= map[nr][nc]) {
					if (distance2[nr][nc] > distance2[cur.r][cur.c] + 1) {
						distance2[nr][nc] = distance2[cur.r][cur.c] + 1;
						pq.add(new Edge(nr, nc, distance2[nr][nc]));
					}
					
				} else {
					int time = diff * diff;
					if (distance2[nr][nc] > distance2[cur.r][cur.c] + time) {
						distance2[nr][nc] = distance2[cur.r][cur.c] + time;
						pq.add(new Edge(nr, nc, distance2[nr][nc]));
					}
					
				}
			}
			
		}
	}
	
	// 9 -> 18 -> 27
	
	static class Edge implements Comparable<Edge> {
		int r;
		int c;
		int t;
		
		public Edge(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}
		
		@Override
		public int compareTo(Edge e) {
			return this.t - e.t;
		}
	}

}
