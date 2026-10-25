class Solution {
    public int closedIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Step 1: Remove all islands connected to the boundary
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // If it's on the border and it's land (0)
                if ((i == 0 || i == rows - 1 || j == 0 || j == cols - 1) && grid[i][j] == 0) {
                    dfs(grid, i, j);
                }
            }
        }
        
        // Step 2: Count the remaining isolated/closed islands
        int closedIslandsCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    closedIslandsCount++;
                    dfs(grid, i, j); // Sink this closed island
                }
            }
        }
        
        return closedIslandsCount;
    }
    
    private void dfs(int[][] grid, int r, int c) {
        // Boundary conditions and water check
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 1) {
            return;
        }
        
        // Mark the current land cell as visited by turning it to water (1)
        grid[r][c] = 1;
        
        // Traverse all 4 neighbors
        dfs(grid, r + 1, c); // Down
        dfs(grid, r - 1, c); // Up
        dfs(grid, r, c + 1); // Right
        dfs(grid, r, c - 1); // Left
    }
}
