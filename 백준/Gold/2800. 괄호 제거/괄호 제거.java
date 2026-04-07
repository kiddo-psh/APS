import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.next();

        Stack<Integer> stack = new Stack<>();
        List<int[]> pair = new ArrayList<>();
        for (int i=0; i<S.length(); i++) {
            if (S.charAt(i) == '(') {
                stack.push(i);
            } else if (S.charAt(i) == ')') {
                int l = stack.pop();
                pair.add(new int[]{l, i});
            }
        }

        TreeSet<String> result = new TreeSet<>();
        int m = pair.size();

        for (int MASK=1; MASK<(1<<m); MASK++) {
            boolean[] removed = new boolean[S.length()];

            for (int i=0; i<m; i++) {
                if ((MASK & (1<<i)) != 0) {
                    int[] p = pair.get(i);
                    removed[p[0]] = true;
                    removed[p[1]] = true;
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i=0; i<S.length(); i++) {
                if (!removed[i]) sb.append(S.charAt(i));
            }

            result.add(sb.toString());
        }

        StringBuilder output = new StringBuilder();
        for (String str : result) {
            output.append(str).append("\n");
        }
        System.out.print(output);
        sc.close();
    }
}
