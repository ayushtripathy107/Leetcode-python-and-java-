class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int sum = 0;
        
        // Loop through the array using 1-based indexing logic
        for (int i = 1; i <= n; i++) {
            // Check if i divides n
            if (n % i == 0) {
                // Since nums is 0-indexed in code, 
                // the 1-indexed position i corresponds to index i - 1
                int val = nums[i - 1];
                sum += val * val;
            }
        }
        
        return sum;
    }
}
