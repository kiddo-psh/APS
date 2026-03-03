import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 탐험가와 독가스가 움직일 수 있는 곳에 시간을 표시해둔다.
 * 맵의 가장자리를 순회하며 탐험가가 먼저 도착할 수 있는 곳을 찾는다.
 * 찾으면 탈출 가능 시간을 갱신한다.
 * 못 찾을 경우, 독가스와 탐험가가 움직일 수 있는 공간이 겹친다면 zombie
 * 아니라면 cannot escape
 */
public class Solution {
	static int N, M;
	static int[][] map, dist, menDist;
	static final int[] dr = {-1,1,0,0};
	static final int[] dc = {0,0,-1,1};
	static boolean inRange(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
	
	static class Point {
		int r, c;
		public Point (int r, int c) {this.r=r; this.c=c;}
	}
	
	static Point men;
	static Queue<Point> q = new ArrayDeque<>();
	static Queue<Point> q2 = new ArrayDeque<>();
	
	static void makeDist() { // 독가스가 퍼지는 시간 기록
		while(!q.isEmpty()) {
			Point cur = q.poll();
			int r = cur.r, c = cur.c;
			
			for (int i=0; i<4; i++) {
				int nr = r + dr[i], nc = c + dc[i];
				
				if (!inRange(nr, nc)) continue;
				if (dist[nr][nc] >= 0) continue;
				if (map[nr][nc] == 1) continue;
				
				dist[nr][nc] = dist[r][c] + 1;
				q.offer(new Point(nr, nc));
			}
		}
	}
	
	static void makeMenDist() { // 탐험가가 갈수있는 경로에 시간 기록 
		while(!q2.isEmpty()) {
			Point cur = q2.poll();
			int r = cur.r, c = cur.c;
			
			for (int i=0; i<4; i++) {
				int nr = r + dr[i], nc = c + dc[i];
				
				if (!inRange(nr,nc)) continue;
				if (menDist[nr][nc] >= 0) continue;
				if (map[nr][nc] == 1) continue;
				
				menDist[nr][nc] = menDist[r][c] + 1;
				q2.offer(new Point(nr,nc));
			}
		}
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			dist = new int[N][M];
			menDist = new int[N][M];
			
			for (int i=0; i<N; i++) {
				Arrays.fill(dist[i], -1);
				Arrays.fill(menDist[i], -1);
			}
            
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j]==2) {
						q.offer(new Point(i,j));
						dist[i][j] = 0;
					}
					if (map[i][j]==3) {
						q2.offer(new Point(i,j));
						menDist[i][j] = 0;
					}
				}
			}
			makeDist();
			makeMenDist();
			
			// 가장자리 순회 
			output.append("#").append(tc).append(" ");
			int answer = Integer.MAX_VALUE;
			for (int i=0; i<N; i++) {
				if (menDist[i][0] != -1) { // 가장자리 도달 가능
					if ((menDist[i][0] < dist[i][0]) || dist[i][0] == -1) { // 탈출 가능
						answer = Math.min(answer, menDist[i][0]);
					}
				} if (menDist[i][M-1] != -1) { // 탈출 가능
					if ((menDist[i][M-1] < dist[i][M-1]) || dist[i][M-1] == -1) { // 탈출 가능
						answer = Math.min(answer, menDist[i][M-1]);
					}
				}
			}
			for (int j=0; j<M; j++) {
				if (menDist[0][j] != -1) { // 가장자리 도달 가능
					if ((menDist[0][j] < dist[0][j]) || dist[0][j]== -1) { // 탈출 가능
						answer = Math.min(answer, menDist[0][j]);
					}
				} if (menDist[N-1][j] != -1) { // 탈출 가능
					if ((menDist[N-1][j] < dist[N-1][j]) || dist[N-1][j]== -1) { // 탈출 가능
						answer = Math.min(answer, menDist[N-1][j]);
					}
				}
			}
			
			if (answer != Integer.MAX_VALUE) { // 탈출가능
				output.append(answer+1).append("\n");
			} else { // 탈출 불가능
				boolean flag = false;
				for (int i=0; i<N; i++) {
					for (int j=0; j<M; j++) {
						if (menDist[i][j]!=-1 && dist[i][j]!=-1) { // 독가스 닿음
							flag = true;
							break;
						}
					}
				}
				if (flag) {
					output.append("ZOMBIE").append("\n");
				} else {
					output.append("CANNOT ESCAPE").append("\n");
				}
			}
			
		}
		System.out.println(output);
		br.close();
	}

}
