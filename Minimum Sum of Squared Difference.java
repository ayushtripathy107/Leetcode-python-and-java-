class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalK = (long) k1 + k2;
        
        // Find the maximum possible difference to set the bucket size
        int maxDiff = 0;
        for (int i = 0; i < n; i++) {
            maxDiff = Math.max(maxDiff, Math.abs(nums1[i] - nums2[i]));
        }
        
        // Base case: if max difference is 0, sum of squares is already 0
        if (maxDiff == 0) {
            return 0;
        }
        
        // Frequency array to store count of each absolute difference
        int[] counts = new int[maxDiff + 1];
        for (int i = 0; i < n; i++) {
            counts[Math.abs(nums1[i] - nums2[i])]++;
        }
        
        // Greedily reduce the largest differences from top to bottom
        for (int d = maxDiff; d > 0; d--) {
            if (counts[d] > 0) {
                // Determine how many elements we can reduce from 'd' to 'd - 1'
                long operationsNeeded = counts[d];
                long operationsUsed = Math.min(totalK, operationsNeeded);
                
                counts[d] -= operationsUsed;
                counts[d - 1] += operationsUsed;
                totalK -= operationsUsed;
                
                if (totalK == 0) {
                    break;
                }
            }
        }
        
        // Calculate the final sum of squared differences
        long minSumSquare = 0;
        for (int d = 1; d <= maxDiff; d++) {
            if (counts[d] > 0) {
                minSumSquare += (long) counts[d] * d * d;
            }
        }
        
        return minSumSquare;
    }
}
