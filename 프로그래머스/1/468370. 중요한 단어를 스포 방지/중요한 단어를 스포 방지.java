import java.util.*;

class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
        
        Set<String> normal = new HashSet<>();
        Set<String> spoiler = new HashSet<>();
        
        int idx = 0;
        
        for (String word : message.split(" ")) {
            int start = idx;
            int end = start + word.length() - 1;
            idx = end + 2;
            
            boolean isSpoiler = false;
            
            for (int[] range : spoiler_ranges) {
                if (start <= range[1] && range[0] <= end) {
                    isSpoiler = true;
                    break;
                }
            }
            
            if (isSpoiler) {
                spoiler.add(word);
            } else {
                normal.add(word);
            }
        }   
        
        spoiler.removeAll(normal);
        return spoiler.size();
    }
}