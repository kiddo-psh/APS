import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    static int N, X, M, result;
    static int[] r = new int[10];
    static int[] l = new int[10];
    static int[] s = new int[10];
    static int[] cage = new int[6];
    static int[] answer;
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        StringBuilder output = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
             
            for (int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                l[i] = Integer.parseInt(st.nextToken())-1;
                r[i] = Integer.parseInt(st.nextToken())-1;
                s[i] = Integer.parseInt(st.nextToken());
            }
            result = -1;
            dfs(0);
             
            output.append("#").append(tc).append(" ");
            if (result == -1) output.append(-1).append("\n");
            else {
                for (int i=0; i<N; i++) {
                    output.append(answer[i]).append(" ");
                }
                output.append("\n");
            }
        }
        System.out.println(output);
    }
     
    static void dfs(int index) {
        if (index==N) {
            if (check()) {
                int sum = 0;
                for (int i=0; i<N; i++) {
                    sum += cage[i];
                }
                if (result < sum) {
                    result = sum;
                    answer = cage.clone();
                }
            }
            return;
        }
         
        for (int i=0; i<=X; i++) {
            cage[index] = i;
            dfs(index+1);
        }
    }
     
    static boolean check() {
        for (int i=0; i<M; i++) {
            int left = l[i];
            int right = r[i];
            int sum = 0;
            for (int j=left; j<=right; j++) {
                sum += cage[j];
            }
            if (sum != s[i]) return false;
        }
        return true;
    }
}