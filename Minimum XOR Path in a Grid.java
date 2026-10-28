class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // dp[i][j][k] stores whether a path to (i, j) can have a bitwise XOR sum equal to k
        // Since grid elements are up to 10 bits, the maximum XOR value is 1023
        boolean[][][] dp = new boolean[m][n][1024];
        
        // Base case: Starting at the top-left cell
        dp[0][0][grid[0][0]] = true;
        
        // Fill the DP table row by row, column by column
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 1024; k++) {
                    if (!dp[i][j][k]) continue;
                    
                    // Move right
                    if (j + 1 < n) {
                        dp[i][j + 1][k ^ grid[i][j + 1]] = true;
                    }
                    
                    // Move down
                    if (i + 1 < m) {
                        dp[i + 1][j][k ^ grid[i + 1][j]] = true;
                    }
                }
            }
        }
        
        // Find the minimum XOR value that is reachable at the bottom-right corner
        for (int k = 0; k < 1024; k++) {
            if (dp[m - 1][n - 1][k]) {
                return k;
            }
        }
        
        return -1;
    }
}
