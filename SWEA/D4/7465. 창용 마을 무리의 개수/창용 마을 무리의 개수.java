import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, M;
	static int[] parent, rank;
	
	static int find(int x) {
		if(parent[x]==x) return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int a, int b) {
		a = find(a); b = find(b);
		if (a==b) return;
		if(rank[a]<rank[b]) {int t=a; a=b; b=t;}
		
		parent[b] = a;
		if(rank[a]==rank[b]) rank[a]++;
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
				parent[i]=i;
			
			while(M-->0) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				union(a,b);
			}
			
			int answer = 0;
			for (int i=1; i<=N; i++) {
				if (parent[i]==i) answer++;
			}
			
			output.append("#").append(tc).append(" ").append(answer).append("\n");
		}	
		
		System.out.println(output);
		br.close();
	}
}
