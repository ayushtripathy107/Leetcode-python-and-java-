import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        // Since nums[i] <= 10^9, 31 bits are sufficient
        int[] bitCounts = new int[31];
        
        // Count occurrences of 1s at each bit position
        for (int num : nums) {
            for (int i = 0; i < 31; i++) {
                if (((num >> i) & 1) == 1) {
                    bitCounts[i]++;
                }
            }
        }
        
        long totalSumOfSquares = 0;
        long MOD = 1_000_000_007;
        
        // Greedily form the top k largest numbers
        for (int i = 0; i < k; i++) {
            long currentNum = 0;
            for (int bit = 0; bit < 31; bit++) {
                if (bitCounts[bit] > 0) {
                    currentNum |= (1L << bit);
                    bitCounts[bit]--; // Consume one bit
                }
            }
            // Add the square of the current number to the total sum
            totalSumOfSquares = (totalSumOfSquares + (currentNum * currentNum) % MOD) % MOD;
        }
        
        return (int) totalSumOfSquares;
    }
}
