import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        
        // 1. Precompute proper divisors for all lengths from 2 to n
        List<Integer>[] divisors = new ArrayList[n + 1];
        for (int len = 2; len <= n; len++) {
            divisors[len] = new ArrayList<>();
            for (int d = 1; d < len; d++) {
                if (len % d == 0) {
                    divisors[len].add(d);
                }
            }
        }
        
        // 2. Precompute cost[i][j]: min changes to make s[i..j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                cost[i][j] = getSemiPalindromeCost(s, i, j, divisors[j - i + 1]);
            }
        }
        
        // 3. DP: dp[parts][end] = min changes to split s[0..end-1] into 'parts' substrings
        int INF = 1_000_000;
        int[][] dp = new int[k + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;
        
        for (int parts = 1; parts <= k; parts++) {
            // Each partition needs at least length 2, so start at parts * 2
            for (int end = parts * 2; end <= n; end++) {
                // Try every valid split point 'prevEnd' for the previous (parts - 1) substrings
                for (int prevEnd = (parts - 1) * 2; prevEnd <= end - 2; prevEnd++) {
                    if (dp[parts - 1][prevEnd] != INF) {
                        dp[parts][end] = Math.min(
                            dp[parts][end],
                            dp[parts - 1][prevEnd] + cost[prevEnd][end - 1]
                        );
                    }
                }
            }
        }
        
        return dp[k][n];
    }
    
    private int getSemiPalindromeCost(String s, int start, int end, List<Integer> divs) {
        int len = end - start + 1;
        int minChanges = Integer.MAX_VALUE;
        
        for (int d : divs) {
            int changes = 0;
            // Check each of the 'd' modulo groups
            for (int offset = 0; offset < d; offset++) {
                int left = start + offset;
                int right = end - (d - 1 - offset);
                while (left < right) {
                    if (s.charAt(left) != s.charAt(right)) {
                        changes++;
                    }
                    left += d;
                    right -= d;
                }
            }
            minChanges = Math.min(minChanges, changes);
        }
        
        return minChanges;
    }
}
