import java.util.Arrays;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        
        // minLenSoFar[i] stores the minimum length of a sub-array with sum == target 
        // within the prefix subarray arr[0...i].
        int[] minLenSoFar = new int[n];
        Arrays.fill(minLenSoFar, Integer.MAX_VALUE);
        
        int left = 0;
        int currentSum = 0;
        int minTotalSumOfLengths = Integer.MAX_VALUE;
        int currentMinLen = Integer.MAX_VALUE;
        
        for (int right = 0; right < n; right++) {
            currentSum += arr[right];
            
            // Shrink the window from the left if the sum exceeds the target
            while (currentSum > target) {
                currentSum -= arr[left];
                left++;
            }
            
            // If we found a valid sub-array summing up to target
            if (currentSum == target) {
                int currentSubarrayLen = right - left + 1;
                
                // Check if there is a valid non-overlapping sub-array to the left
                if (left > 0 && minLenSoFar[left - 1] != Integer.MAX_VALUE) {
                    minTotalSumOfLengths = Math.min(minTotalSumOfLengths, currentSubarrayLen + minLenSoFar[left - 1]);
                }
                
                // Update the minimum length seen up to the current window
                currentMinLen = Math.min(currentMinLen, currentSubarrayLen);
            }
            
            // Store the best history up to index 'right'
            minLenSoFar[right] = currentMinLen;
        }
        
        // Return -1 if no two non-overlapping sub-arrays are found
        return minTotalSumOfLengths == Integer.MAX_VALUE ? -1 : minTotalSumOfLengths;
    }
}
