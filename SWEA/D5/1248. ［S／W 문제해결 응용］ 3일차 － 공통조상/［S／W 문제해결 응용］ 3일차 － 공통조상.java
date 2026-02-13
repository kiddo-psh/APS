import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int V, E, v1, v2, lcp, size;
	static int[] parent, child;
	static final int PACK = 50_000;
	
	static void find(int v1, int v2) {
		int temp = v2;
		if (v1==1) return;
		
		while (temp != 1) {
			if (v1 == temp) {
				lcp = v1;
				return;
			}
			
			temp = parent[temp];
		}
		
		find(parent[v1], v2);
	}
	
	static int getSize(int v) {
		if (child[v] > PACK) {
			return 2 + getSize(child[v]/PACK) + getSize(child[v]%PACK);
		} else if (child[v] > 0) {
			return 1 + getSize(child[v]);
		} else {
			return 0;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			
			parent = new int[V+1];
			child = new int[V+1];
			
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<E; i++) {
				int p = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				parent[c] = p;
				if (child[p] != 0) {
					child[p] += c*PACK;
				} else child[p] = c;
			}
			
			lcp = 1;
			find(v1, v2);
			size = getSize(lcp)+1;
			
			output.append("#").append(tc).append(" ")
			.append(lcp).append(" ").append(size).append("\n");
		}
		System.out.println(output);
	}
}
