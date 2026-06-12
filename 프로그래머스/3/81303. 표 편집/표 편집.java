import java.util.*;

class Solution {
    public static class Node{
        int idx, prev, next;
        Node (int idx, int prev, int next) {
            this.idx = idx;
            this.prev = prev;
            this.next = next;
        }
    }
    
    public String solution(int n, int k, String[] cmd) {
        int[] prev = new int[n];
        int[] next = new int[n];
        char[] origin = new char[n];
        Stack<Node> history = new Stack<>();
        
        Arrays.fill(origin, 'O');
        
        for (int i=0; i<n; i++) {
            next[i] = i+1;
            prev[i] = i-1;
        }
        next[n-1]=-1;
        
        int cur = k;
        
        for (String c : cmd) {
            String[] command = c.split(" ");
            if (command[0].equals("U")) {
                int x = Integer.parseInt(command[1]);
                for (int i=0; i<x; i++) {
                    if (cur == -1) break;
                    cur = prev[cur];
                }
            } else if (command[0].equals("D")) {
                int x = Integer.parseInt(command[1]);
                for (int i=0; i<x; i++) {
                    if (cur == -1) break;
                    cur = next[cur];
                }
            } else if (command[0].equals("C")) {
                history.push(new Node(cur, prev[cur], next[cur]));
                origin[cur] = 'X';
                
                if(next[cur]!=-1)
                    prev[next[cur]] = prev[cur];
                
                if(prev[cur]!=-1)
                    next[prev[cur]] = next[cur];
                
                if(next[cur]!=-1) cur = next[cur];
                else cur = prev[cur];
                
            } else {
                Node node = history.pop();
                if (node.prev!=-1)
                    next[node.prev] = node.idx;
                
                if (node.next!=-1)
                    prev[node.next] = node.idx;
                
                prev[node.idx] = node.prev;
                next[node.idx] = node.next;
                
                origin[node.idx] = 'O';
            }
        }
        
        return new String(origin);
    }
}