import java.util.Arrays;

class Solution {
    public int minMoves2(int[] nums) {
        // 1. Sort the array to find the median
        Arrays.sort(nums);
        
        // 2. Pick the median element
        int median = nums[nums.length / 2];
        
        // 3. Sum the absolute differences
        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        
        return moves;
    }
}
