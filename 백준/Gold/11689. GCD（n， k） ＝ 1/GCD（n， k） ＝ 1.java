import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();

        long answer = n;

        for (long p = 2; p * p <= n; p++) {
            if (n % p == 0) {
                answer = answer / p * (p - 1);

                while (n % p == 0) {
                    n /= p;
                }
            }
        }

        if (n > 1) { // 마지막에 남은 소인수 처리
            answer = answer / n * (n - 1);
        }

        System.out.println(answer);
    }
}