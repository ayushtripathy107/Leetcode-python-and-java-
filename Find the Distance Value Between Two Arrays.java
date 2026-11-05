import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        // Step 1: Sort arr2 to enable binary search
        Arrays.sort(arr2);
        int distanceValue = 0;
        
        // Step 2: Check each element in arr1
        for (int x : arr1) {
            if (isValid(arr2, x, d)) {
                distanceValue++;
            }
        }
        
        return distanceValue;
    }
    
    // Helper function using binary search to check for violating elements
    private boolean isValid(int[] arr2, int target, int d) {
        int low = 0;
        int high = arr2.length - 1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            // Check if the current element is within the invalid range [target - d, target + d]
            if (arr2[mid] >= target - d && arr2[mid] <= target + d) {
                return false; // Found a value too close, so x is invalid
            } 
            // If the current element is too small, search the right half
            else if (arr2[mid] < target - d) {
                low = mid + 1;
            } 
            // If the current element is too large, search the left half
            else {
                high = mid - 1;
            }
        }
        return true; // No violating elements found in arr2
    }
}
