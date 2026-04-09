import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static class Node {
        TreeMap<String, Node> child = new TreeMap<>();
    }

    static Node root = new Node();
    static StringBuilder sb = new StringBuilder();

    static void insert(String[] foods) {
        Node cur = root;
        for (String food : foods) {
            cur.child.putIfAbsent(food, new Node());
            cur = cur.child.get(food);
        }
    }

    static void dfs(Node node, int depth) {
        for (String food : node.child.keySet()) {
            for (int i=0; i<depth; i++) {
                sb.append("--");
            }
            sb.append(food).append("\n");
            dfs(node.child.get(food), depth+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());

            String[] foods = new String[K];
            for (int j=0; j<K; j++) {
                foods[j] = st.nextToken();
            }

            insert(foods);
        }

        dfs(root, 0);
        System.out.println(sb);

        br.close();
    }
}
