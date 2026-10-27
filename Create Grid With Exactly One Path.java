class Solution {
    public String[] createGrid(int m, int n) {
        // Initialize the grid completely filled with obstacles '#'
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = '#';
            }
        }
        
        // Step 1: Open the entire first row
        for (int j = 0; j < n; j++) {
            grid[0][j] = '.';
        }
        
        // Step 2: Open the entire last column
        for (int i = 0; i < m; i++) {
            grid[i][n - 1] = '.';
        }
        
        // Convert the character grid into an array of Strings
        String[] result = new String[m];
        for (int i = 0; i < m; i++) {
            result[i] = new String(grid[i]);
        }
        
        return result;
    }
}
