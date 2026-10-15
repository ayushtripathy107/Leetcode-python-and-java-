import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int totalSum = 0;
        int n = nums.size();
        
        // Iterate through all possible starting points of a subarray
        for (int i = 0; i < n; i++) {
            Set<Integer> distinctElements = new HashSet<>();
            
            // Expand the subarray to the right
            for (int j = i; j < n; j++) {
                distinctElements.add(nums.get(j));
                
                // Get the count of distinct elements in the current subarray nums[i...j]
                int distinctCount = distinctElements.size();
                
                // Add the square of the distinct count to our total
                totalSum += distinctCount * distinctCount;
            }
        }
        
        return totalSum;
    }
}
