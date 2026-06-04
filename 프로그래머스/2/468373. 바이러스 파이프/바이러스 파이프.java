import java.util.*;

class Solution {
    private List<int[]>[] graph;
    private Map<String, Integer> memo;

    public int solution(int n, int infection, int[][] edges, int k) {
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
            graph[edge[1]].add(new int[]{edge[0], edge[2]});
        }

        memo = new HashMap<>();
        boolean[] initial = new boolean[n + 1];
        initial[infection] = true;
        return dfs(initial, k, n);
    }

    private int dfs(boolean[] infected, int remaining, int n) {
        int count = countInfected(infected);
        if (remaining == 0) return count;

        String key = makeKey(infected, remaining);
        if (memo.containsKey(key)) return memo.get(key);

        int best = count;

        for (int pipeType = 1; pipeType <= 3; pipeType++) {
            boolean[] newInfected = spread(infected, pipeType, n);
            if (countInfected(newInfected) == count) continue; // 전파 없으면 skip

            int result = dfs(newInfected, remaining - 1, n);
            best = Math.max(best, result);
        }

        memo.put(key, best);
        return best;
    }

    private boolean[] spread(boolean[] infected, int pipeType, int n) {
        boolean[] newInfected = Arrays.copyOf(infected, n + 1);
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (infected[i]) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int[] edge : graph[node]) {
                int neighbor = edge[0], type = edge[1];
                if (type == pipeType && !newInfected[neighbor]) {
                    newInfected[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        return newInfected;
    }

    private int countInfected(boolean[] infected) {
        int cnt = 0;
        for (boolean b : infected) if (b) cnt++;
        return cnt;
    }

    private String makeKey(boolean[] infected, int remaining) {
        StringBuilder sb = new StringBuilder();
        for (boolean b : infected) sb.append(b ? '1' : '0');
        sb.append('-').append(remaining);
        return sb.toString();
    }
}