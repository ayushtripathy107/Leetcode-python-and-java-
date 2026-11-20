import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        // Sort the array to process elements in non-decreasing order
        Arrays.sort(nums);
        
        // Map to store the max length of a consecutive sequence ending at key 'x'
        Map<Integer, Integer> dp = new HashMap<>();
        int maxLen = 0;
        
        for (int num : nums) {
            // Option 1: Increment num by 1 (extends sequence ending at num)
            int lenPlusOne = dp.getOrDefault(num, 0) + 1;
            dp.put(num + 1, lenPlusOne);
            
            // Option 2: Keep num unchanged (extends sequence ending at num - 1)
            int lenUnchanged = dp.getOrDefault(num - 1, 0) + 1;
            dp.put(num, lenUnchanged);
            
            // Update the global maximum length found so far
            maxLen = Math.max(maxLen, Math.max(lenPlusOne, lenUnchanged));
        }
        
        return maxLen;
    }
}
