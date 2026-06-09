import java.util.*;

class Solution {

    static final int INF = 1_000_000_000;

    int h, n, m, k;

    char[][] map;
    int[][] panels;

    int er, ec;

    int[] dr = {1,-1,0,0};
    int[] dc = {0,0,1,-1};

    public int solution(int h, String[] grid,
                        int[][] panels,
                        int[][] seqs) {

        this.h = h;
        this.panels = panels;

        n = grid.length;
        m = grid[0].length();
        k = panels.length;

        map = new char[n][m];

        for(int r=0;r<n;r++){
            map[r] = grid[r].toCharArray();

            for(int c=0;c<m;c++){
                if(map[r][c]=='@'){
                    er=r;
                    ec=c;
                }
            }
        }

        int[][] panelDist = buildPanelDistance();

        int[] pre = new int[k];

        for(int[] s : seqs){
            int a = s[0]-1;
            int b = s[1]-1;

            pre[b] |= (1<<a);
        }

        int[] startDist = new int[k];

        for(int i=0;i<k;i++){
            startDist[i] = panelDist[0][i];
        }

        int MAX = 1<<k;

        int[][] dp = new int[MAX][k];

        for(int i=0;i<MAX;i++){
            Arrays.fill(dp[i], INF);
        }

        // 시작 위치 = 1번 패널
        for(int next=0;next<k;next++){

            if(pre[next] != 0) continue;

            int mask = 1<<next;

            dp[mask][next] = startDist[next];
        }

        for(int mask=0;mask<MAX;mask++){

            for(int last=0;last<k;last++){

                if(dp[mask][last]==INF) continue;

                for(int next=0;next<k;next++){

                    if((mask&(1<<next))!=0) continue;

                    if((mask & pre[next]) != pre[next])
                        continue;

                    int nextMask = mask | (1<<next);

                    dp[nextMask][next] =
                        Math.min(
                            dp[nextMask][next],
                            dp[mask][last]
                            + panelDist[last][next]
                        );
                }
            }
        }

        int ans = INF;

        int full = MAX-1;

        for(int last=0;last<k;last++){
            ans = Math.min(ans, dp[full][last]);
        }

        return ans;
    }

    int[][] buildPanelDistance(){

        int[][] dist = new int[k][k];

        int[][] bfsElev = bfs(er, ec);

        int[] toElev = new int[k];

        for(int i=0;i<k;i++){

            int r = panels[i][1]-1;
            int c = panels[i][2]-1;

            toElev[i] = bfsElev[r][c];
        }

        for(int i=0;i<k;i++){

            int r1 = panels[i][1]-1;
            int c1 = panels[i][2]-1;

            int[][] dmap = bfs(r1,c1);

            for(int j=0;j<k;j++){

                if(i==j) continue;

                int r2 = panels[j][1]-1;
                int c2 = panels[j][2]-1;

                int sameFloor = dmap[r2][c2];

                int diffFloor =
                    toElev[i]
                    + Math.abs(
                        panels[i][0]
                        - panels[j][0]
                    )
                    + toElev[j];

                if(panels[i][0]==panels[j][0]) {
                    dist[i][j] = sameFloor;
                } else {
                    dist[i][j] = diffFloor;
                }
            }
        }

        return dist;
    }

    int[][] bfs(int sr,int sc){

        int[][] dist = new int[n][m];

        for(int i=0;i<n;i++){
            Arrays.fill(dist[i], -1);
        }

        Queue<int[]> q = new ArrayDeque<>();

        dist[sr][sc]=0;
        q.offer(new int[]{sr,sc});

        while(!q.isEmpty()){

            int[] cur=q.poll();

            int r=cur[0];
            int c=cur[1];

            for(int d=0;d<4;d++){

                int nr=r+dr[d];
                int nc=c+dc[d];

                if(nr<0||nr>=n||nc<0||nc>=m)
                    continue;

                if(map[nr][nc]=='#')
                    continue;

                if(dist[nr][nc]!=-1)
                    continue;

                dist[nr][nc]=dist[r][c]+1;

                q.offer(new int[]{nr,nc});
            }
        }

        return dist;
    }
}