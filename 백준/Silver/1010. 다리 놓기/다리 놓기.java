import java.util.Scanner;

public class Main {

    static long mCn(int m, int n) {
        long a = 1;
        long r = 1;
        for (int i=0; i<n; i++) {
            a *= m--;
            a /= r++;
        }

        return a;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-->0) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            
            System.out.println(mCn(M,N));
        }
    }
}
