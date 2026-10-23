class Solution {
    public int sortPermutation(int[] nums) {
        int ans = -1; // Initialize with -1 (all bits set to 1)
        
        for (int i = 0; i < nums.length; i++) {
            // If the element is out of place, it must participate in swaps
            if (nums[i] != i) {
                if (ans == -1) {
                    ans = nums[i];
                } else {
                    ans &= nums[i];
                }
            }
        }
        
        // If the array is already sorted, return 0 as specified by the problem
        return ans == -1 ? 0 : ans;
    }
}
