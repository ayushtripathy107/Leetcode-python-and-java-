class Solution {
    public int minMoves(int[] nums) {
        // Find the minimum value in the array
        int min = nums[0];
        long sum = 0;
        
        for (int num : nums) {
            if (num < min) {
                min = num;
            }
            sum += num;
        }
        
        // Calculate the total moves using the derived formula
        return (int) (sum - (long) nums.length * min);
    }
}
