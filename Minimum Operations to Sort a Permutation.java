class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int pos0 = -1;
        
        // Find the position of element 0
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                pos0 = i;
                break;
            }
        }
        
        // Check if the array is in increasing cyclic order
        boolean isIncreasing = true;
        for (int i = 0; i < n; i++) {
            if (nums[(pos0 + i) % n] != i) {
                isIncreasing = false;
                break;
            }
        }
        
        if (isIncreasing) {
            return Math.min(pos0, 2 + n - pos0);
        }
        
        // Check if the array is in decreasing cyclic order
        boolean isDecreasing = true;
        for (int i = 0; i < n; i++) {
            if (nums[(pos0 - i + n) % n] != i) {
                isDecreasing = false;
                break;
            }
        }
        
        if (isDecreasing) {
            return Math.min(n - pos0, pos0 + 2);
        }
        
        // If neither cyclic order matches, sorting is impossible
        return -1;
    }
}
