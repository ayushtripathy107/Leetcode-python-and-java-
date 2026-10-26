class Solution {
    public int countIslands(int[][] grid, int k) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int eligibleIslandsCount = 0;
        
        // Traverse every cell in the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If the cell value is greater than 0, it's a new unvisited island
                if (grid[i][j] > 0) {
                    // Calculate the total sum of this island using DFS
                    long islandSum = dfs(grid, i, j, m, n);
                    
                    // Check if the total value is divisible by k
                    if (islandSum % k == 0) {
                        eligibleIslandsCount++;
                    }
                }
            }
        }
        
        return eligibleIslandsCount;
    }
    
    private long dfs(int[][] grid, int r, int c, int m, int n) {
        // Base cases: out of bounds or water cell (<= 0)
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] <= 0) {
            return 0;
        }
        
        // Add current cell value to the sum
        long currentVal = grid[r][c];
        
        // Mark the current cell as visited by changing its value to 0
        grid[r][c] = 0;
        
        // Recursively visit all 4 directions (Up, Down, Left, Right)
        currentVal += dfs(grid, r - 1, c, m, n); // Up
        currentVal += dfs(grid, r + 1, c, m, n); // Down
        currentVal += dfs(grid, r, c - 1, m, n); // Left
        currentVal += dfs(grid, r, c + 1, m, n); // Right
        
        return currentVal;
    }
}
