import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] lis = new int[N];
        int size = 0;
        for (int i=0; i<N; i++) {
            int val = arr[i];
            int pos = lowerbound(lis, 0, size, val);

            if (size == pos) size++;

            lis[pos] = val;
        }

        System.out.println(size);
        br.close();
    }

    static int lowerbound(int[] lis, int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (lis[mid] >= target) {
                right = mid;
            } else {
                left = mid+1;
            }
        }
        return left;
    }
}
