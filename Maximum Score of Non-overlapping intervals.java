import java.util.*;

class Solution {
    // Helper class to hold interval info alongside its original index
    private static class Interval {
        int l, r, weight, id;
        Interval(int l, int r, int weight, int id) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.id = id;
        }
    }

    // Helper class to store the DP results
    private static class Element {
        long weight;
        int[] indices;

        Element(long weight, int[] indices) {
            this.weight = weight;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervalsList) {
        int n = intervalsList.size();
        Interval[] intervals = new Interval[n];
        
        for (int i = 0; i < n; i++) {
            List<Integer> inter = intervalsList.get(i);
            intervals[i] = new Interval(inter.get(0), inter.get(1), inter.get(2), i);
        }

        // Sort by start time 'l' to facilitate suffix DP and binary search
        Arrays.sort(intervals, (a, b) -> Integer.compare(a.l, b.l));

        // Find the next non-overlapping interval index for each interval
        int[] nextIdx = new int[n];
        for (int i = 0; i < n; i++) {
            int target = intervals[i].r;
            int low = i + 1, high = n, ans = n;
            while (low <= high && low < n) {
                int mid = low + (high - low) / 2;
                if (intervals[mid].l > target) {
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            nextIdx[i] = ans;
        }

        // dp[i][k] stores the optimal choice from suffix i choosing at most k intervals
        Element[][] dp = new Element[n + 1][5];

        // Initialize base cases for out-of-bounds suffix
        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new Element(0, new int[0]);
        }
        // Initialize base case for choosing 0 intervals
        for (int i = 0; i <= n; i++) {
            dp[i][0] = new Element(0, new int[0]);
        }

        // Fill DP table from bottom to top (suffix approach)
        for (int i = n - 1; i >= 0; i--) {
            for (int k = 1; k <= 4; k++) {
                // Option 1: Skip the current interval
                Element skip = dp[i + 1][k];

                // Option 2: Take the current interval
                int next = nextIdx[i];
                Element nextDp = dp[next][k - 1];
                long takeWeight = intervals[i].weight + nextDp.weight;
                
                // Construct the combined index array for the 'take' option
                int[] takeIndices = new int[nextDp.indices.length + 1];
                takeIndices[0] = intervals[i].id;
                System.arraycopy(nextDp.indices, 0, takeIndices, 1, nextDp.indices.length);
                
                // Sort to ensure lexicographical comparisons work accurately
                Arrays.sort(takeIndices); 
                Element take = new Element(takeWeight, takeIndices);

                // Select the better choice between skip and take
                if (take.weight > skip.weight) {
                    dp[i][k] = take;
                } else if (skip.weight > take.weight) {
                    dp[i][k] = skip;
                } else {
                    // Tie-breaker: choose lexicographically smaller indices array
                    if (compareArrays(take.indices, skip.indices) < 0) {
                        dp[i][k] = take;
                    } else {
                        dp[i][k] = skip;
                    }
                }
            }
        }

        return dp[0][4].indices;
    }

    // Helper method to compare two arrays lexicographically
    private int compareArrays(int[] a, int[] b) {
        int len = Math.min(a.length, b.length);
        for (int i = 0; i < len; i++) {
            if (a[i] != b[i]) {
                return Integer.compare(a[i], b[i]);
            }
        }
        return Integer.compare(a.length, b.length);
    }
}
