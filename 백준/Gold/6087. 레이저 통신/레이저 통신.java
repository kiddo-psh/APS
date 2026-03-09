import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
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
	
	static Deque<Node> dq = new ArrayDeque<>();
	
	static class Node {
		int r, c, dir;
		Node (int r, int c, int dir) {
			this.r = r; 
			this.c = c; 
			this.dir=dir; 
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new char[H][W];
		reflect = new int[H][W][4];
		
		Node end = new Node(-1,-1, -1);
		
		boolean flag = false;
		
		for (int i=0; i<H; i++) {
			String s = br.readLine();
			for (int j=0; j<W; j++) {
				map[i][j] = s.charAt(j);
				
				for (int d=0; d<4; d++) 
					reflect[i][j][d] = Integer.MAX_VALUE;
				
				if (map[i][j]=='C' && !flag) {
					for (int d=0; d<4; d++) {
						dq.offer(new Node(i,j,d));
						reflect[i][j][d] = 0;
					}
					flag = true;
				} else if (map[i][j]=='C' && flag) {
					end.r = i;
					end.c = j;
				}
			}
		}
		
		while(!dq.isEmpty()) {
			Node cur = dq.poll();
			
			for (int d=0; d<4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if (!inRange(nr,nc)) continue;
				if (map[nr][nc] == '*') continue;
				
				int cost = reflect[cur.r][cur.c][cur.dir];
				if (cur.dir != d) cost++;
				
				if (reflect[nr][nc][d] <= cost) continue;
				
				reflect[nr][nc][d] = cost;
				
				if (cur.dir == d) {
					dq.addFirst(new Node(nr,nc,d));
				} else {
					dq.addLast(new Node(nr,nc,d));
				}
			}
		}
		int answer = Integer.MAX_VALUE;
		for (int d=0; d<4; d++) {
			answer = Math.min(answer, reflect[end.r][end.c][d]);
		}
		
		System.out.println(answer);
		br.close();
	}
}
