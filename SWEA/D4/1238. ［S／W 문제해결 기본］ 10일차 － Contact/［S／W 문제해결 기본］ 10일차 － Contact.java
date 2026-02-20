import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, S, answer;
	
	static class V {
		int v, depth;
		public V (int v, int depth) {this.v=v; this.depth=depth;}
	}
	
	@SuppressWarnings("unchecked")
	static List<Integer>[] list = new ArrayList[101];
	static {
		for (int i=0; i<=100; i++) list[i] = new ArrayList<>();
	}
	static Queue<V> q = new LinkedList<>();
	static boolean[] visited = new boolean[101];
	
	static int bfs() {
		q.clear();
		q.offer(new V(S, 0));
		int maxDepth = 0;
		int maxNum = 0;
		
		while(!q.isEmpty()) {
			V cur = q.poll();
			if (maxDepth < cur.depth) {
				maxDepth = cur.depth;
				maxNum = cur.v;
			} else if (maxDepth == cur.depth) {
				if (maxNum < cur.v) {
					maxNum = cur.v;
				}
			}
			
			for (int next : list[cur.v]) {
				if (visited[next]) continue;
				visited[next] = true;
				q.offer(new V(next, cur.depth+1));
			}
		}
		
		return maxNum;
	}
	
	public static void main(String[] args) throws IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc=1; tc<=10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken());
			for (int i=0; i<=100; i++) list[i].clear();
			
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N/2; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list[a].add(b);
			}
			Arrays.fill(visited, false);
			
			output.append("#").append(tc).append(" ").append(bfs()).append("\n");
		}
		System.out.println(output);
	}
}
