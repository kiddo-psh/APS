import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int W,H;
	static char[][] map;
	static int[][][] reflect;
	static final int[] dr = {-1,1,0,0};
	static final int[] dc = {0,0,-1,1};
	
	static boolean inRange(int r, int c) {
		return r>=0 && r<H && c>=0 && c<W;
	}
	
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	static class Node implements Comparable<Node> {
		int r, c, dir, reflected;
		Node (int r, int c, int dir, int reflected) {
			this.r = r; this.c = c; 
			this.dir=dir; this.reflected = reflected;
		}
		public int compareTo(Node o) {
			return this.reflected - o.reflected;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new char[H][W];
		reflect = new int[H][W][4];
		
		Node start = new Node(-1,-1, -1, -1);
		Node end = new Node(-1,-1, -1, -1);
		
		boolean flag = false;
		
		for (int i=0; i<H; i++) {
			String s = br.readLine();
			for (int j=0; j<W; j++) {
				map[i][j] = s.charAt(j);
				
				for (int d=0; d<4; d++) 
					reflect[i][j][d] = Integer.MAX_VALUE;
				
				if (map[i][j]=='C' && !flag) {
					start.r = i;
					start.c = j;
					flag = true;
				} else if (map[i][j]=='C' && flag) {
					end.r = i;
					end.c = j;
				}
			}
		}
		
		pq.offer(start);
		for (int d=0; d<4; d++) reflect[start.r][start.c][d] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int cr = cur.r, cc = cur.c;
			
			for (int d=0; d<4; d++) {
				int nr = cr+dr[d], nc = cc+dc[d];
				int nReflect = (cur.dir != d) ? cur.reflected+1 : cur.reflected;
				
				if (!inRange(nr,nc)) continue;
				if (map[nr][nc] == '*') continue;
				if (reflect[nr][nc][d] <= nReflect) continue;
				
				reflect[nr][nc][d] = nReflect;
				Node next = new Node(nr, nc, d, nReflect);
				pq.offer(next);
			}
		}
		int answer = Integer.MAX_VALUE;
		for (int d=0; d<4; d++) {
			answer = Math.min(reflect[end.r][end.c][d], answer);
		}
		System.out.println(answer);
		br.close();
	}
}
