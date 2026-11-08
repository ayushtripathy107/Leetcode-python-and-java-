class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[] rowCount = new int[m];
        int[] colCount = new int[n];
        
        // Step 1: Count the total number of servers in each row and column
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    rowCount[r]++;
                    colCount[c]++;
                }
            }
        }
        
        int communicableServers = 0;
        
        // Step 2: Identify servers that can communicate
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    // A server can communicate if its row or column has > 1 server
                    if (rowCount[r] > 1 || colCount[c] > 1) {
                        communicableServers++;
                    }
                }
            }
        }
        
        return communicableServers;
    }
}
