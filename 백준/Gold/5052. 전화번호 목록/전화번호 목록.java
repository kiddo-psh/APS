import java.io.*;

public class Main {
	static class Trie {
		class Node {
			Node[] child = new Node[10];
			boolean isEnd = false;
		}
		
		final Node root = new Node();
		
		boolean insert(String word) {
			Node cur = root;
			
			for (int i=0; i<word.length(); i++) {
				int idx = word.charAt(i) - '0';
				
				if (cur.child[idx] == null) cur.child[idx] = new Node();
				
				cur = cur.child[idx];
				if (cur.isEnd) return false;
			}
			
			for (int i=0; i<10; i++) {
				if (cur.child[i] != null) return false;
			}
			
			cur.isEnd = true;
			return true;
		}
	}
	
	public static void main(String[] args) throws IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			int n = Integer.parseInt(br.readLine());
			
			Trie trie = new Trie();
			boolean corr = true;
			for (int i=0; i<n; i++) {
				String word = br.readLine();
				if (!trie.insert(word)) {
					corr = false;
				}
			}
			if (corr) output.append("YES").append("\n");
			else output.append("NO").append("\n");
		}
		
		System.out.println(output);
		br.close();
	}
}
