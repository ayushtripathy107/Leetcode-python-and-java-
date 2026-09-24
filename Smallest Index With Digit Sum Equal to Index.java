class Solution {
    public int smallestIndex(int[] nums) {
        // Iterate through the array sequentially to find the smallest index first
        for (int i = 0; i < nums.length; i++) {
            if (getDigitSum(nums[i]) == i) {
                return i;
            }
        }
        return -1;
    }

    // Helper method to calculate the sum of digits of a number
    private int getDigitSum(int num) {
        int sum = 0;
        // Handle negative numbers if applicable, though array elements are typically non-negative here
        num = Math.abs(num); 
        
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
