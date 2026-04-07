import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N;
    static List<int[]> stairsList = new ArrayList<>();
    static List<int[]> peopleList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc=1; tc<=T; tc++) {
            stairsList.clear();
            peopleList.clear();

            N = Integer.parseInt(br.readLine());
            for (int r=0; r<N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c=0; c<N; c++) {
                    int x = Integer.parseInt(st.nextToken());
                    if (x==1) {
                        peopleList.add(new int[]{r,c});
                    } else if (x>=2) {
                        stairsList.add(new int[]{r,c,x});
                    }
                }
            }

            int answer = Integer.MAX_VALUE;
            int p = peopleList.size();
            for (int MASK=0; MASK<(1<<p); MASK++) {
                List<Integer> group0 = new ArrayList<>();
                List<Integer> group1 = new ArrayList<>();

                for (int i=0; i<p; i++) {
                    if ((MASK & (1<<i)) != 0) group0.add(i);
                    else group1.add(i);
                }

                int time0 = simulation(0, group0);
                int time1 = simulation(1, group1);

                answer = Math.min(answer, Math.max(time0, time1));
            }

            output.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(output);
        br.close();
    }

    static int simulation(int idx, List<Integer> group) {
        if (group.isEmpty()) return 0;
        int[] stair = stairsList.get(idx);
        int K = stair[2];

        int[] arrive = new int[group.size()];
        for (int i=0; i<group.size(); i++) {
            int[] p = peopleList.get(group.get(i));
            int dist = getDist(stair, p);
            arrive[i] = dist;
        }
        Arrays.sort(arrive);
        Queue<Integer> q = new ArrayDeque<>();

        for (int ETA : arrive) {
            if (q.size()<3) {
                q.offer(ETA + K + 1);
            } else {
                int wait = q.poll();
                q.offer(Math.max(wait,ETA+1) + K);
            }
        }
        int result = 0;
        while (!q.isEmpty()) result = q.poll();
        return result;
    }

    static int getDist(int[] s, int[] p) {
        return Math.abs(s[0]-p[0]) + Math.abs(s[1]-p[1]);
    }
}
