import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        if (N < 10) {
            System.out.println(0);
            return;
        }

        final long INF = 1_000_000_000_000L;
        final int MOD  = 1_000_000_000;
        int full = (1<<10)-1;
        long[][][] dp = new long[N+1][10][1<<10]; // dp[len][last][mask]
        for (int i=1; i<10; i++) dp[1][i][1<<i] = 1;

        for (int len=1; len<N; len++) {
            for (int last=0; last<10; last++) {
                for (int mask=0; mask<(1<<10); mask++) {
                    if ((mask & (1<<last)) == 0) continue;

                    if (last > 0) {
                        dp[len+1][last-1][mask | (1<<(last-1))] = (
                                dp[len+1][last-1][mask | (1<<(last-1))] +
                                dp[len][last][mask]) % MOD;
                    }

                    if (last < 9) {
                        dp[len+1][last+1][mask | (1<<(last+1))] = (
                                dp[len+1][last+1][mask | (1<<(last+1))] +
                                dp[len][last][mask]) % MOD;
                    }
                }
            }
        }

        long answer = 0;
        for (int i=0; i<10; i++) {
            answer = (answer + dp[N][i][full]) % MOD;
        }

        System.out.println(answer);
        sc.close();
    }
}
