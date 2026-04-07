import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] stairs = new int[N];

        for (int i=0; i<N; i++) {
            stairs[i] = sc.nextInt();
        }

        int[][] dp = new int[N][2];
        dp[0][0] = 0;
        dp[0][1] = stairs[0];
        
        if (N==1) {
            System.out.println(dp[0][1]);
            return;
        }
        
        dp[1][0] = dp[0][1] + stairs[1];
        dp[1][1] = stairs[1];

        for (int i=2; i<N; i++) {
            int prev2 = Math.max(dp[i-2][0], dp[i-2][1]);
            dp[i][0] = dp[i-1][1] + stairs[i];
            dp[i][1] = prev2 + stairs[i];
        }

        System.out.println(Math.max(dp[N-1][0], dp[N-1][1]));
    }
}
