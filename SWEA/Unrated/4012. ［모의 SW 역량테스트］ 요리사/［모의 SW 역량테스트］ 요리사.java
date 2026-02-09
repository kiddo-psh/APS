import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    static int N, result;
    static int[][] synergy;
    static int[] comb;
     
    static void dfs(int index, int count) {
        if (count == N/2) {
            result = Math.min(result, getDiff());
            return;
        }
        if (index == N-1) return;
         
        dfs (index+1, count);
        comb[index+1] = 1;
        dfs (index+1, count+1);
        comb[index+1] = 0;
    }
     
    static int getDiff() {
        int aSum = 0;
        int bSum = 0;
        for (int i=0; i<N-1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (comb[i]==1 && comb[j]==1) {
                    aSum += synergy[i][j] + synergy[j][i];
                }
                if (comb[i]==0 && comb[j]==0) {
                    bSum += synergy[i][j] + synergy[j][i];
                }
            }
        }
        return Math.abs(aSum - bSum);
    }
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        StringBuilder output = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());
            synergy = new int[N][N];
            for (int i=0; i<N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j=0; j<N; j++) {
                    synergy[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            comb = new int[N];
            result = Integer.MAX_VALUE;
            dfs(-1, 0);
             
            output.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(output);
    }
}