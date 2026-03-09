import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, M;
	static int[] parent, rank;
	
	static int find(int a) {
		if (parent[a] == a) return a;
		
		return parent[a] = find(parent[a]);
	}
	
	static void union(int a, int b) {
		a = find(a); b = find(b);
		if (a==b) return;
		if (rank[a]<rank[b]) {int t=a; a=b; b=t;}
		
		parent[b] = a;
		if (rank[a]==rank[b]) rank[a]++;
	}
	
	static int check(int a, int b) {
		int pa = find(a), pb = find(b);
		if (pa==pb) return 1;
		return 0;
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
			
			rank = new int[N+1];
			parent = new int[N+1];
			for (int i=1; i<=N; i++)
				parent[i] = i;
			
			output.append("#").append(tc).append(" ");
			while(M-->0) {
				st = new StringTokenizer(br.readLine());
				int q = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if (q==0) {
					union(a,b);
				} else {
					output.append(check(a,b));
				}
			}
			output.append("\n");
		}
		System.out.println(output);
		br.close();
	}
}
