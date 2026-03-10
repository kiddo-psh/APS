import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static long[] h;
	static Stack<Integer> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder output = new StringBuilder();
		
		while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) break;

            long[] h = new long[n + 1]; // 마지막에 0 추가

            for (int i = 0; i < n; i++) {
                h[i] = Long.parseLong(st.nextToken());
            }

            Stack<Integer> stack = new Stack<>();
            long max = 0;

            for (int i = 0; i <= n; i++) {

                while (!stack.isEmpty() && h[stack.peek()] > h[i]) {

                    long height = h[stack.pop()];

                    int left = stack.isEmpty() ? -1 : stack.peek();

                    long width = i - left - 1;

                    max = Math.max(max, height * width);
                }

                stack.push(i);
            }
            output.append(max).append("\n");
        }
		System.out.println(output);
		br.close();
	}
}
