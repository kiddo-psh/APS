import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static class Trie {
		public class Node {
			Node[] child = new Node[26];
			boolean isEnd;
		}
		
		private final Node root = new Node();
		
		public void insert(String word) {
			Node cur = root;
			
			for (int i=0; i<word.length(); i++) {
				int idx = word.charAt(i)-'a';
				
				if (cur.child[idx] == null) {
					cur.child[idx] = new Node();
				}
				
				cur = cur.child[idx];
			}
			
			cur.isEnd = true;
		}
		
		
		public boolean check(String word, Set<String> nicknameSet) {
			Node cur = root;
			
			for (int i=0; i<word.length(); i++) {
				int idx = word.charAt(i)-'a';
				
				if (cur.child[idx]==null) return false;
				
				cur = cur.child[idx];
				
				if (cur.isEnd) {
					String suffix = word.substring(i+1);
					if (nicknameSet.contains(suffix)) return true;
				}
			}
			
			return false;
		}
	}

	
	public static void main(String[] args) throws IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		Trie color = new Trie();
		Set<String> nickname = new HashSet();
		
		while(C-->0) {
			color.insert(br.readLine());
		}
		while(N-->0) {
			nickname.add(br.readLine());
		}
		
		int Q = Integer.parseInt(br.readLine());
		while(Q-->0) {
			String teamName = br.readLine();

			if (color.check(teamName, nickname)) output.append("Yes").append("\n");
			else output.append("No").append("\n");
		}
		System.out.println(output);
		br.close();
	}
}
