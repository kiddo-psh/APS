import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String bomb = br.readLine();
		int bombLength = bomb.length();
		Stack<Character> stack = new Stack<>();
		
		for (int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			stack.push(c);
			if (c == bomb.charAt(bombLength-1) && stack.size() >= bombLength) {
				StringBuilder sb = new StringBuilder();
				for (int j=0; j<bombLength; j++) {
					sb.append(stack.pop());
				}
				
				if (!sb.reverse().toString().equals(bomb)) {
					for(int j=0; j<bombLength; j++) {
						stack.push(sb.charAt(j));
					}
				}
			}
		}
		
		if (stack.isEmpty()) System.out.println("FRULA");
		else {
			StringBuilder output = new StringBuilder();
			while (!stack.isEmpty()) {
				output.append(stack.pop());
			}
			
			System.out.println(output.reverse());
		}
	}
}
