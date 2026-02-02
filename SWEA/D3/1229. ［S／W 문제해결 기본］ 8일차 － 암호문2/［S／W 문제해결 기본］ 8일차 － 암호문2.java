
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 
 * 메모리: 25,344 kb / 실행시간: 90 ms
 * 
 * */
//
public class Solution {
	static List<String> code = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				String s = st.nextToken();
				code.add(s);
			}
			int Op = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < Op; i++) {
				if (st.nextToken().charAt(0) == 'I') {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					for (int j=0; j<y; j++) {
						String s = st.nextToken();
						code.add(x+j, s);
					}
				}
				else {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					for (int j=0; j<y; j++) {
						code.remove(x);
					}
				}
			}
			output.append("#").append(tc).append(" ");
			for (int i=0; i<10; i++) {
				output.append(code.get(i)).append(" ");
			}
			output.append("\n");
			code.clear();
		}
		System.out.println(output);
	}
}
