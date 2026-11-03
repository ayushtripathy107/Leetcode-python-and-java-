class Solution {
    public int minMoves(int[] nums) {
        int maxVal = nums[0];
        // Find the maximum element in the array
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }
        
        int moves = 0;
        // Calculate the total moves required
        for (int num : nums) {
            moves += (maxVal - num);
        }
        
        return moves;
    }
}
