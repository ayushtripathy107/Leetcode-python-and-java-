class Solution {
    public long[] resultArray(int[] nums, int k) {
        // Result array of size k to store the total counts for each remainder x
        long[] result = new long[k];
        
        // dp[r] stores the number of active subarrays ending at the previous position 
        // whose product modulo k equals r
        long[] dp = new long[k];
        
        for (int num : nums) {
            long[] nextDp = new long[k];
            int currentMod = num % k;
            
            // 1. Start a brand new subarray consisting of only the current number
            nextDp[currentMod]++;
            
            // 2. Extend existing subarrays from the previous step
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int nextMod = (r * currentMod) % k;
                    nextDp[nextMod] += dp[r];
                }
            }
            
            // Accumulate counts from the current step into the global result
            for (int r = 0; r < k; r++) {
                result[r] += nextDp[r];
            }
            
            // Move to the next element
            dp = nextDp;
        }
        
        return result;
    }
}
