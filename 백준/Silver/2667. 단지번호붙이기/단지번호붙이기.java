import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static boolean visited[][];
	static int[][] map;
	static int N, count;
	
	static final int[] dr = {-1,1,0,0};
	static final int[] dc = {0,0,-1,1};
	
	static void dfs(int r, int c) {
		visited[r][c] = true;
		count++;
		
		for (int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (!inRange(nr, nc)) continue;
			if (visited[nr][nc]) continue;
			if (map[nr][nc]==0) continue;
			
			dfs(nr, nc);
		}
	}
	
	static boolean inRange(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// 초기화부
		map = new int[N][N];
		visited = new boolean[N][N];
		
		// 입력부 
		for (int i=0; i<N; i++) {
			String s = br.readLine();
			for (int j=0; j<N; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		// 단지별 집 수 저장할 리스트
		List<Integer> answer = new ArrayList<>();
		
		int num = 0; // 단지수
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j]==1 && !visited[i][j]) {
					count = 0; // 단지 내 집 수
					dfs(i,j);
					answer.add(count);
					num++;
				}
			}
		}
		System.out.println(num);
		
		answer
		.stream()
		.mapToInt(i -> i)
		.sorted()
		.forEach(System.out::println);
	}
}
