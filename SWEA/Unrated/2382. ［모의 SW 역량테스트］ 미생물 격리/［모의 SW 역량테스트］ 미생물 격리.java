import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, K;
	
	static int[][] sum, maxCnt, maxDir;
	static List<Micron> list = new ArrayList<>();
	
	static class Micron {
		int r, c, num, dir;
		
		Micron(int r, int c, int num, int dir) {
			this.r=r; this.c=c; this.num=num; this.dir=dir;
		}
		
		void move() {
			r += dr[dir];
			c += dc[dir];
			
			if (inRed(r, c)) {
				dir = (dir<=2) ? 3-dir : 7-dir;
				num /= 2;
			}
		}
	}
	
	static int simulation() {
		while(M-->0 && list.size()>0) {
			clear();
			
			for (Micron m : list) {
				m.move();
				
				sum[m.r][m.c] += m.num;
				if (maxCnt[m.r][m.c] < m.num) {
					maxCnt[m.r][m.c] = m.num;
					maxDir[m.r][m.c] = m.dir; 
				}
			}
			
			List<Micron> next = new ArrayList<>();
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (sum[i][j] == 0) continue;
					next.add(new Micron(i, j, sum[i][j], maxDir[i][j]));
				}
			}
			
			list = next;
		}
		
		int answer = 0;
		for (Micron m : list) {
			answer += m.num;
		}
		
		return answer;
	}
	
	static void clear() {
		for (int i=0; i<N; i++) {
			Arrays.fill(sum[i], 0);
			Arrays.fill(maxCnt[i], 0);
			Arrays.fill(maxDir[i], 0);
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			sum = new int[N][N];
			maxCnt = new int[N][N];
			maxDir = new int[N][N];
			list.clear();
			
			for (int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int R = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				int NUM = Integer.parseInt(st.nextToken());
				int DIR = Integer.parseInt(st.nextToken());
				
				list.add(new Micron(R, C, NUM, DIR));
			}
			
			output.append("#").append(tc).append(" ").append(simulation()).append("\n");
		}
		System.out.println(output);
		br.close();
	}
	
	static final int[] dr = {0,-1,1,0,0}; // 0상하좌우
	static final int[] dc = {0,0,0,-1,1};
	
	static boolean inRed(int r, int c) {
		return r==0 || r==N-1 || c==0 || c==N-1;
	}
}
