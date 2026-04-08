import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] A, B, lis, idx, prev;
    static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        A = new int[N];
        B = new int[N];

        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            A[i] = a;
            map.put(a, b);
        }

        Arrays.sort(A);
        for (int i=0; i<N; i++) {
            B[i] = map.get(A[i]);
        }

        lis = new int[N];
        idx = new int[N];
        prev=  new int[N];
        Arrays.fill(prev, -1);

        int size = 0;
        for (int i=0; i<N; i++) {
            int val = B[i];
            int pos = lowerbound(0, size, val);

            lis[pos] = val;
            idx[pos] = i;
            prev[i] = (pos > 0) ? idx[pos-1] : -1;

            if (pos == size) size++;
        }

        boolean[] remove = new boolean[N];
        int cur = idx[size-1];
        while (cur != -1) {
            remove[cur] = true;
            cur = prev[cur];
        }

        output.append(N-size).append("\n");
        for (int i=0; i<N; i++) {
            if (!remove[i]) output.append(A[i]).append("\n");
        }

        System.out.println(output);
        br.close();
    }

    static int lowerbound(int left, int right, int target) {
        while (left < right) {
            int mid = (left+right)/2;
            if (lis[mid] >= target) {
                right = mid;
            } else {
                left = mid+1;
            }
        }
        return left;
    }
}
