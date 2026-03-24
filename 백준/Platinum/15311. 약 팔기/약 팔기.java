import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] ans = new int[2000];
		for (int i=0; i<1000; i++) {
			ans[i] = 1;
		}
		for (int i=1000; i<2000; i++) {
			ans[i] = 1000;
		}
		System.out.println(2000);
		for (int x : ans) {
			System.out.print(x + " ");
		}
	}
}
