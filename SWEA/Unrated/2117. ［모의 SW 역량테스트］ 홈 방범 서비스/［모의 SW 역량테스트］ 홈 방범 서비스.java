import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
/*
# 메모리: 117,252 kb
# 실행시간: 1,422 ms
 */
public class Solution {
    static int N, M, count;
    static int[][] town, dist;
    static Queue<int[]> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        StringBuilder output = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            town = new int[N][N];
            dist = new int[N][N];

            for (int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<N; j++) {
                    town[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            count = 0;

            for (int r=0; r<N; r++) {
                for (int c=0; c<N; c++) {
                    for (int k=1; k<=N+2; k++) {
                        int cnt = 0;
                        for (int i=0; i<N; i++) {
                            for (int j=0; j<N; j++) {
                                if (town[i][j]==1 && Math.abs(i-r)+Math.abs(j-c) < k) {
                                    cnt++;
                                }
                            }
                        }
                        int cost = k*k + (k-1)*(k-1);
                        if (cnt*M - cost >= 0) count = Math.max(count, cnt);
                    }
                }
            }

            output.append("#").append(tc).append(" ").append(count).append("\n");
        }

        System.out.print(output);
        br.close();
    }
}
