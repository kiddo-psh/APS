import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Solution {
    static int[][] m = new int[5][8];
    static int[] visited = new int[5];
    static int tc;
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        StringBuilder output = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (tc=1; tc<=T; tc++) {
            int K = Integer.parseInt(br.readLine());
            StringTokenizer st;
            for (int i=1; i<=4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 8; j++) {
                    m[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            for (int i=0; i<K; i++) {
                st = new StringTokenizer(br.readLine());
                int magnetNum = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                Arrays.fill(visited, 0);
                rotate(magnetNum, dir);
            }
 
            int result = calc();
            output.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(output);
    }
     
    static void rotate(int num, int dir) {
        visited[num] = tc;
         
        if (        
                num > 1 && 
                visited[num-1] != tc && 
                m[num][6] != m[num-1][2]) { // 왼쪽 자석과 극이 다를 때
            rotate(num-1, dir*-1);
        }
        if (        
                num != 4 && 
                visited[num+1] != tc && 
                m[num][2] != m[num+1][6]) { // 오른쪽 자석과 극이 다를 때
            rotate(num+1, dir*-1);
        }
         
        if (dir==1) {
            int end = m[num][7];
            for (int i=7; i>0; i--) {
                m[num][i] = m[num][i-1];
            }
            m[num][0] = end;
        } else if (dir==-1) {
            int front = m[num][0];
            for (int i=0; i<7; i++) {
                m[num][i] = m[num][i+1];
            }
            m[num][7] = front;
        }
    }
     
    static int calc() {
        int sum = 0;
        for (int i=1; i<=4; i++) {
            if (m[i][0]==1)
                sum += Math.pow(2, i-1);
        }
        return sum;
    }
}