import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String T = sc.nextLine();
        String P = sc.nextLine();

        int[] pi = makePi(P);
        int cnt = 0;
        List<Integer> idx = new ArrayList<>();

        int j =0;
        for (int i=0; i<T.length(); i++) {

            while (j > 0 && T.charAt(i) != P.charAt(j)) {
                j = pi[j-1];
            }

            if (T.charAt(i) == P.charAt(j)) {
                if (j == P.length()-1) { // 매칭성공
                    j = pi[j];
                    cnt++;
                    idx.add(i-P.length()+2);
                } else {
                    j++;
                }
            }
        }

        System.out.println(cnt);
        for (int x : idx) {
            System.out.print(x + " ");
        }
    }

    static int[] makePi(String P) {
        int m = P.length();
        int[] pi = new int[m];

        int j = 0;
        for (int i=1; i<m; i++) {
            while (j>0 && P.charAt(i)!=P.charAt(j)) {
                j = pi[j-1];
            }

            if (P.charAt(i) == P.charAt(j)) {
                pi[i] = ++j;
            }
        }
        return pi;
    }
}
