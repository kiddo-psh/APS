import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static final int INF = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        StringBuilder output = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[][] dist = new int[N][N];
            for (int r=0; r<N; r++) {
                for (int c=0; c<N; c++) {
                    int d = Integer.parseInt(st.nextToken());
                    if (r==c) dist[r][c] = 0;
                    else if (d==0) dist[r][c] = INF;
                    else dist[r][c] = d;
                }
            }

            for (int k=0; k<N; k++) {
                for (int i=0; i<N; i++) {
                    for (int j=0; j<N; j++) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }

            int answer = INF;
            for (int i=0; i<N; i++) {
                int cc = 0;
                for (int j=0; j<N; j++) {
                    cc += dist[i][j];
                }
                answer = Math.min(answer, cc);
            }

            output.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.print(output);
        br.close();
    }
}
