import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pair {
        int l, r;
        Pair(int l, int r) {
            this.l=l; this.r=r;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Pair> list = new ArrayList<>();
        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());

            int l = Math.min(h, o);
            int r = Math.max(h, o);

            list.add(new Pair(l,r));
        }
        int d = Integer.parseInt(br.readLine());

        list.sort((a, b) -> Integer.compare(a.r, b.r));

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int answer = 0;
        for (Pair p : list) {
            pq.offer(p.l);
            int start = p.r - d;

            while (!pq.isEmpty() && pq.peek()<start) {
                pq.poll();
            }

            answer = Math.max(answer, pq.size());
        }

        System.out.println(answer);

        br.close();
    }
}
