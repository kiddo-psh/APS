import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int R, C, answer;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int r=0; r<R; r++) {
            map[r] = br.readLine().toCharArray();
        }

        answer = 0;
        for (int r=0; r<R; r++) {
            if (dfs(r,0)) answer++;
        }

        System.out.print(answer);
        br.close();
    }

    static boolean dfs(int r, int c) {
        if (c == C-1) return true;

        for (int d=0; d<3; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr<0 || nr>=R || nc>=C) continue;
            if (map[nr][nc] != '.') continue;

            map[nr][nc] = 'x'; // 방문처리 (파이프 설치)

            if (dfs(nr, nc)) return true;
        }

        return false;
    }

    static int[] dr = {-1, 0, 1};
    static int[] dc = {1, 1, 1};

    static boolean isValid(int r, int c) {
        return r>=0 && r< R && c >= 0 && c < C && (map[r][c] == '.');
    }
}
