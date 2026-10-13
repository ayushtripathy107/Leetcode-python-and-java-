import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];
        
        // Step 1: Compute squares of all elements
        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }
        
        // Step 2: Sort the squares in ascending order
        Arrays.sort(squares);
        
        long maxScore = 0;
        int negCount = n / 2; // Number of elements that will be subtracted
        
        // Step 3: Subtract the smallest squares
        for (int i = 0; i < negCount; i++) {
            maxScore -= squares[i];
        }
        
        // Step 4: Add the largest squares
        for (int i = negCount; i < n; i++) {
            maxScore += squares[i];
        }
        
        return maxScore;
    }
}
