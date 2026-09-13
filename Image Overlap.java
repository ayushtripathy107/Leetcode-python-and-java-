import java.util.*;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> list1 = new ArrayList<>();
        List<int[]> list2 = new ArrayList<>();

        // 1. Gather coordinates of all 1s in both images
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) list1.add(new int[]{r, c});
                if (img2[r][c] == 1) list2.add(new int[]{r, c});
            }
        }

        // 2. Count the frequency of each translation vector
        Map<String, Integer> counts = new HashMap<>();
        int maxOverlap = 0;

        for (int[] p1 : list1) {
            for (int[] p2 : list2) {
                int dr = p2[0] - p1[0];
                int dc = p2[1] - p1[1];
                
                // Represent the 2D offset vector as a unique string key
                String key = dr + "," + dc; 
                counts.put(key, counts.getOrDefault(key, 0) + 1);
                
                maxOverlap = Math.max(maxOverlap, counts.get(key));
            }
        }

        return maxOverlap;
    }
}
