import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int D,W,K;
    static int[][] flim;
    static int count;

    public static void main(String[] args) throws IOException {
        StringBuilder output = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            flim = new int[D][W];

            for (int i=0; i<D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<W; j++) {
                    flim[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            output.append("#").append(tc).append(" ");
            if (K==1) {
                output.append(0).append("\n");
                continue;
            }

            count = Integer.MAX_VALUE;
            dfs(0,0);
            output.append(count).append("\n");
        }

        System.out.print(output);
        br.close();
    }

    private static void dfs(int row, int used) {
        if (used >= count) return;

        if (row == D) {
            if (check()) count = used;
            return;
        }

        int[] temp = flim[row].clone();

        // 1) 그대로
        dfs(row + 1, used);

        // 2) A 주입
        Arrays.fill(flim[row], 0);
        dfs(row + 1, used + 1);

        // 3) B 주입
        Arrays.fill(flim[row], 1);
        dfs(row + 1, used + 1);

        // 복구
        flim[row] = temp;
    }

    private static boolean check() {
        for (int col = 0; col < W; col++) {

            int prev = flim[0][col];
            int cnt = 1;
            boolean flag = false;

            for (int row = 1; row < D; row++) {

                if (prev == flim[row][col]) {
                    cnt++;
                    if (cnt >= K) {
                        flag = true;
                        break;
                    }
                } else {
                    prev = flim[row][col];
                    cnt = 1;
                }
            }

            if (!flag) return false;
        }
        return true;
    }
}
