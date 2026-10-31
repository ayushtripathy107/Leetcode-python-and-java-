import java.util.Arrays;

class Solution {
    private int m, n;
    private int[][] grid;
    private int[][] dp;
    private int[][][] memoSim; // Stores simulated landing cells to avoid re-simulation
    private final int MOD = 1000000007;

    public int uniquePaths(int[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        
        // Base case: if the grid is 1x1, there is exactly 1 path
        if (m == 1 && n == 1) return 1;
        
        // Initialize DP table for path counts
        this.dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        
        // Initialize simulation memoization table
        // dir: 0 = Right, 1 = Down
        this.memoSim = new int[m][n][2];
        for (int[][] mat : memoSim) {
            for (int[] row : mat) {
                Arrays.fill(row, -2);
            }
        }
        
        return getPaths(0, 0);
    }
    
    private int getPaths(int r, int c) {
        // Reached the destination cell
        if (r == m - 1 && c == n - 1) return 1;
        if (dp[r][c] != -1) return dp[r][c];
        
        long totalPaths = 0;
        
        // Choice 1: Attempt to move Right
        int targetRight = simulate(r, c + 1, 0);
        if (targetRight != -1) {
            int nextRow = targetRight / n;
            int nextCol = targetRight % n;
            totalPaths = (totalPaths + getPaths(nextRow, nextCol)) % MOD;
        }
        
        // Choice 2: Attempt to move Down
        int targetDown = simulate(r + 1, c, 1);
        if (targetDown != -1) {
            int nextRow = targetDown / n;
            int nextCol = targetDown % n;
            totalPaths = (totalPaths + getPaths(nextRow, nextCol)) % MOD;
        }
        
        dp[r][c] = (int) totalPaths;
        return dp[r][c];
    }
    
    /**
     * Simulates the path of the robot when entering a cell until it lands on an empty cell,
     * reaches the destination, or goes out of bounds.
     * @return encoded coordinate (r * n + c) if valid, or -1 if out of bounds.
     */
    private int simulate(int r, int c, int dir) {
        if (r >= m || c >= n) return -1;
        
        // Termination condition: destination reached or empty cell found
        if ((r == m - 1 && c == n - 1) || grid[r][c] == 0) {
            return r * n + c;
        }
        
        if (memoSim[r][c][dir] != -2) return memoSim[r][c][dir];
        
        int landingCell;
        if (dir == 0) { 
            // Moving Right into a mirror -> reflected Down
            landingCell = simulate(r + 1, c, 1);
        } else { 
            // Moving Down into a mirror -> reflected Right
            landingCell = simulate(r, c + 1, 0);
        }
        
        memoSim[r][c][dir] = landingCell;
        return landingCell;
    }
}
