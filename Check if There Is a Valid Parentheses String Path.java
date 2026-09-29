class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // A valid path length is always odd (m + n - 1 must be even for balanced brackets)
        if ((m + n) % 2 == 0) return false;
        
        // If the start isn't '(' or the end isn't ')', it can't be valid
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') return false;
        
        // visited[r][c][bal] tracks if we've visited grid[r][c] with the current balance
        boolean[][][] visited = new boolean[m][n][(m + n) / 2 + 1];
        
        return dfs(grid, 0, 0, 0, visited);
    }
    
    private boolean dfs(char[][] grid, int r, int c, int bal, boolean[][][] visited) {
        // Update balance based on the current cell
        if (grid[r][c] == '(') {
            bal++;
        } else {
            bal--;
        }
        
        // If balance goes negative, or exceeds the maximum possible open brackets remaining
        if (bal < 0 || bal >= visited[0][0].length) return false;
        
        // If we reached the destination, check if all brackets are balanced
        if (r == grid.length - 1 && c == grid[0].length - 1) {
            return bal == 0;
        }
        
        // If this state has already been processed, return false
        if (visited[r][c][bal]) return false;
        visited[r][c][bal] = true;
        
        // Move right
        if (c + 1 < grid[0].length && dfs(grid, r, c + 1, bal, visited)) {
            return true;
        }
        
        // Move down
        if (r + 1 < grid.length && dfs(grid, r + 1, c, bal, visited)) {
            return true;
        }
        
        return false;
    }
}
