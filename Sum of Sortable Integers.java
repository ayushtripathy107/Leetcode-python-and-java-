import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;
        
        // Find all divisors of n up to sqrt(n)
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
                if (i * i != n) {
                    divisors.add(n / i);
                }
            }
        }
        
        // Validate each divisor k
        for (int k : divisors) {
            if (isValid(nums, n, k)) {
                ans += k;
            }
        }
        
        return ans;
    }
    
    private boolean isValid(int[] nums, int n, int k) {
        int prevMax = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i += k) {
            int currentMin = Integer.MAX_VALUE;
            int currentMax = Integer.MIN_VALUE;
            int drops = 0;
            
            for (int j = 0; j < k; j++) {
                int idx = i + j;
                int val = nums[idx];
                
                if (val < currentMin) currentMin = val;
                if (val > currentMax) currentMax = val;
                
                // Circular drop check within the current block
                int nextIdx = i + (j + 1) % k;
                if (nums[idx] > nums[nextIdx]) {
                    drops++;
                }
            }
            
            // Condition 1: A block can be sorted via cyclic rotation iff drops <= 1
            if (drops > 1) {
                return false;
            }
            
            // Condition 2: Inter-block element ordering must be monotonic
            if (currentMin < prevMax) {
                return false;
            }
            
            prevMax = currentMax;
        }
        
        return true;
    }
}
