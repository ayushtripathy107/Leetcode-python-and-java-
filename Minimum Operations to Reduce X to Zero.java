class Solution {
    public int minOperations(int[] nums, int x) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        // The target sum for the middle subarray
        int target = totalSum - x;
        
        // If target is 0, we need to remove all elements
        if (target == 0) return nums.length;
        // If target is negative, it's impossible to reduce x to 0
        if (target < 0) return -1;
        
        int maxLength = -1;
        int currentSum = 0;
        int left = 0;
        
        // Sliding window to find the longest subarray summing to target
        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];
            
            // Shrink window if the current sum exceeds target
            while (currentSum > target && left <= right) {
                currentSum -= nums[left];
                left++;
            }
            
            // Check if we found a valid window
            if (currentSum == target) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }
        
        // If maxLength wasn't updated, a valid subarray doesn't exist
        return maxLength == -1 ? -1 : nums.length - maxLength;
    }
}
