import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    static int N;
    static long[] arr;
    static HashSet<Long>[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());
        arr = new long[N];

        for (int i=0; i<N; i++) {
            arr[i] = sc.nextLong();
        }

        dp = new HashSet[1 << N];
        int full = (1<<N) - 1;
        build(full);

        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        for (long v : dp[full]) {
            if (isPrime(v)) {
                min = Math.min(min, v);
                max = Math.max(max, v);
            }
        }
        if (min == Long.MAX_VALUE) {
            System.out.println(-1);
        }
        else {
            System.out.println(min);
            System.out.println(max);
        }
    }

    static boolean isPrime(long x) {
        if (x < 2) return false;
        if (x == 2) return true;
        if (x % 2 == 0) return false;
        for (long i=3; i*i<=x; i+=2) {
            if (x % i == 0) return false;
        }
        return true;
    }

    static void build(int mask) {
        if (dp[mask] != null) return;
        dp[mask] = new HashSet<>();

        if ((mask & (mask-1)) == 0) {
            int idx = Integer.numberOfTrailingZeros(mask);
            dp[mask].add(arr[idx]);
            return;
        }

        for (int sub = (mask-1) & mask; sub > 0; sub = (sub-1) & mask) {
            int other = mask ^ sub;
            if (other == 0) continue;

            build(other);
            build(sub);

            for (long x : dp[sub]) {
                for (long y : dp[other]) {
                    dp[mask].add(x+y);
                    dp[mask].add(x-y);
                    dp[mask].add(y-x);
                    dp[mask].add(x*y);

                    if (x > 0 && y > 0) {
                        dp[mask].add(x/y);
                        dp[mask].add(y/x);
                    }
                }
            }
        }
    }
}
