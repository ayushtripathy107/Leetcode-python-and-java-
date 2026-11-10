class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp[i] stores the minimum cost to reach step i
        int[] dp = new int[n + 1];
        
        // Initialize dp array with a high value except the starting step
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        
        // Compute the minimum cost for each step
        for (int i = 1; i <= n; i++) {
            // Check previous steps: i-1, i-2, i-3
            for (int jump = 1; jump <= 3; jump++) {
                if (i - jump >= 0) {
                    int prevStep = i - jump;
                    int transitionCost = costs[i - 1] + (jump * jump);
                    dp[i] = Math.min(dp[i], dp[prevStep] + transitionCost);
                }
            }
        }
        
        return dp[n];
    }
}
