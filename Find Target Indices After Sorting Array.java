import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int smallerCount = 0;
        int equalCount = 0;
        
        // Count numbers smaller than and equal to the target
        for (int num : nums) {
            if (num < target) {
                smallerCount++;
            } else if (num == target) {
                equalCount++;
            }
        }
        
        // Populate the resulting indices sequentially
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < equalCount; i++) {
            result.add(smallerCount + i);
        }
        
        return result;
    }
}
