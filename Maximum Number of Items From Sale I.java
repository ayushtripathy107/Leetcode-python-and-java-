class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] free = new int[n];
        
        // 1. Precompute how many free items each item i grants on its first purchase
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    free[i]++;
                }
            }
        }
        
        int[] dp = new int[budget + 1];
        
        // 2. Run combined 0/1 + Unbounded Knapsack DP
        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int firstCopyGain = 1 + free[i];
            
            // 0/1 Knapsack (backwards): Buy the 1st copy to unlock free items
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + firstCopyGain);
            }
            
            // Unbounded Knapsack (forwards): Buy additional copies for +1 each
            for (int b = price; b <= budget; b++) {
                dp[b] = Math.max(dp[b], dp[b - price] + 1);
            }
        }
        
        return dp[budget];
    }
}
