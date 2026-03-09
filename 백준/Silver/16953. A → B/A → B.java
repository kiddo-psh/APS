import java.util.Scanner;

public class Main {
	static long a, b;
	static int answer = Integer.MAX_VALUE;
	
	static void dfs(long num, int count) {
		if (num > b) return;
		if (count >= answer) return;
		if (num == b) {
			answer = Math.min(answer, count);
			return;
		}
		
		dfs(num*10+1, count+1);
		dfs(num*2, count+1);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		a = sc.nextLong(); b = sc.nextLong();
		
		dfs(a, 1);
		if(answer==Integer.MAX_VALUE) answer=-1;
		System.out.println(answer);
	}
}
