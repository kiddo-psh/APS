import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int change, answer;
    static int[] num, best;

    static void dfs(int count) {
        if (count == change) {
            answer = Math.max(answer, arrayToInt(num));
            return;
        }

        // num이 best와 완전히 같으면(= 이미 최댓값 상태) 남은 횟수 처리
        int diffIdx = -1;
        for (int i = 0; i < num.length; i++) {
            if (num[i] != best[i]) { diffIdx = i; break; }
        }

        if (diffIdx == -1) {
            int remain = change - count;
            // 중복 숫자가 있거나 remain이 짝수면 값 유지 가능
            if (hasDuplicate(num) || remain % 2 == 0) {
                answer = Math.max(answer, arrayToInt(num));
            } else {
                // 중복 없고 remain 홀수면 마지막 두 자리 한번 교환
                swap(num, num.length - 1, num.length - 2);
                answer = Math.max(answer, arrayToInt(num));
                swap(num, num.length - 1, num.length - 2); // 원복
            }
            return;
        }

        int i = diffIdx;
        boolean moved = false;

        // best[i]와 같은 숫자를 뒤에서 찾아 swap
        for (int j = i + 1; j < num.length; j++) {
            if (num[j] == best[i]) {
                swap(num, i, j);
                dfs(count + 1);
                swap(num, i, j); // 원복
                moved = true;
            }
        }

    }

    static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    static boolean hasDuplicate(int[] arr) {
        boolean[] seen = new boolean[10];
        for (int x : arr) {
            if (seen[x]) return true;
            seen[x] = true;
        }
        return false;
    }

    static int arrayToInt(int[] arr) {
        int val = 0;
        for (int x : arr) val = val * 10 + x;
        return val;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        StringBuilder output = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char[] cArr = st.nextToken().toCharArray();
            change = Integer.parseInt(st.nextToken());

            num = new int[cArr.length];
            for (int i = 0; i < cArr.length; i++) num[i] = cArr[i] - '0';

            best = num.clone();
            Arrays.sort(best);
            // 내림차순
            for (int i = 0; i < best.length / 2; i++) {
                int temp = best[i];
                best[i] = best[best.length - 1 - i];
                best[best.length - 1 - i] = temp;
            }

            answer = 0;
            dfs(0);

            output.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(output);
    }
}