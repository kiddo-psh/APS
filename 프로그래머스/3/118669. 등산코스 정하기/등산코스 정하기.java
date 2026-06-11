import java.util.*;

class Solution {
    static int INF = 1_000_000_000;
    
    public static class Road implements Comparable<Road> {
        int dest, dist;
        Road(int dest, int dist) {
            this.dest = dest;
            this.dist = dist;
        }
        
        public int compareTo(Road o) {
            if (this.dist == o.dist) return Integer.compare(this.dest, o.dest);
            return Integer.compare(this.dist, o.dist);
        }
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        Set<Integer> gateSet = new HashSet<>();
        for (int gate : gates) gateSet.add(gate);
        Set<Integer> summitSet = new HashSet<>();
        for (int summit : summits) summitSet.add(summit);
        
        List<int[]>[] graph = new ArrayList[n+1];
        for (int i=0; i<=n; i++) graph[i] = new ArrayList<>();
        for (int[] path : paths) {
            int u = path[0];
            int v = path[1];
            int w = path[2];
            
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }
        
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        
        PriorityQueue<Road> pq = new PriorityQueue<>();
        for (int g : gates) {
            pq.offer(new Road(g, 0));
        }
        
        while(!pq.isEmpty()) {
            Road r = pq.poll();
            int cur = r.dest;
            int curDist = r.dist;
            
            if (summitSet.contains(cur)) continue;
            if (curDist > dist[cur]) continue;
            
            for (int[] nextArr : graph[cur]) {
                int next = nextArr[0];
                int nextDist = Math.max(nextArr[1], curDist);
                
                if (gateSet.contains(next)) continue;
                if (nextDist < dist[next]) {
                    dist[next] = nextDist;
                    pq.offer(new Road(next, nextDist));
                }
            }
        }
        
        Arrays.sort(summits);
        int ans = 0;
        int min = INF;
        
        for (int summit : summits) {
            if (dist[summit] < min) {
                min = dist[summit];
                ans = summit;
            }
        }
        return new int[] {ans, min};
    }
}