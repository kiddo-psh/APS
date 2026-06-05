import java.util.*;

class Solution {

    static final int INF = 1_000_000_000;

    public int[] solution(int m, int n, int h, int w, int[][] drops) {

        int[][] time = new int[m][n];

        for (int r = 0; r < m; r++) {
            Arrays.fill(time[r], INF);
        }

        for (int i = 0; i < drops.length; i++) {
            int r = drops[i][0];
            int c = drops[i][1];
            time[r][c] = i + 1;
        }

        int cols = n - w + 1;

        // 1. 행 방향 슬라이딩 최소값
        int[][] rowMin = new int[m][cols];

        for (int r = 0; r < m; r++) {
            rowMin[r] = slidingMin(time[r], w);
        }

        int rows = m - h + 1;

        int bestValue = -1;
        int bestR = 0;
        int bestC = 0;

int[][] windowMin = new int[rows][cols];

// 세로 슬라이딩 최소값
for (int c = 0; c < cols; c++) {

    Deque<Integer> dq = new ArrayDeque<>();

    for (int r = 0; r < m; r++) {

        while (!dq.isEmpty() &&
               dq.peekFirst() <= r - h)
            dq.pollFirst();

        while (!dq.isEmpty() &&
               rowMin[dq.peekLast()][c] >= rowMin[r][c])
            dq.pollLast();

        dq.addLast(r);

        if (r >= h - 1) {
            int topRow = r - h + 1;
            windowMin[topRow][c] =
                    rowMin[dq.peekFirst()][c];
        }
    }
}
        for (int r = 0; r < rows; r++) {
    for (int c = 0; c < cols; c++) {

        if (windowMin[r][c] > bestValue) {
            bestValue = windowMin[r][c];
            bestR = r;
            bestC = c;
        }
    }
}

        return new int[]{bestR, bestC};
    }

    private int[] slidingMin(int[] arr, int k) {

        int n = arr.length;
        int[] res = new int[n - k + 1];

        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {

            while (!dq.isEmpty() &&
                   dq.peekFirst() <= i - k)
                dq.pollFirst();

            while (!dq.isEmpty() &&
                   arr[dq.peekLast()] >= arr[i])
                dq.pollLast();

            dq.addLast(i);

            if (i >= k - 1) {
                res[i - k + 1] = arr[dq.peekFirst()];
            }
        }

        return res;
    }
}