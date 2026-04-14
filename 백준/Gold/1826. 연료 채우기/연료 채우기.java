import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static int N, L, P;
    static class Station {
        int dist, fuel;

        Station(int dist, int fuel) {
            this.dist = dist;
            this.fuel = fuel;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        PriorityQueue<Station> stations = new PriorityQueue<>((a,b) -> Integer.compare(a.dist, b.dist));
        PriorityQueue<Station> fuels = new PriorityQueue<>((a,b) -> Integer.compare(b.fuel, a.fuel));

        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            stations.add(new Station(a,b));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken()); // 마을까지 거리
        P = Integer.parseInt(st.nextToken()); //남은 연료

        int cnt = 0;
        while(P < L) {

            while (!stations.isEmpty() && stations.peek().dist <= P) {
                fuels.offer(stations.poll());
            }

            if (fuels.isEmpty()) {
                System.out.print(-1);
                return;
            }
            P += fuels.poll().fuel;
            cnt++;
        }

        System.out.print(cnt);
        br.close();
    }
}
