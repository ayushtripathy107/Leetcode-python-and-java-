import java.util.*;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] ops = new long[n - x + 1];
        
        // Use two TreeMaps to maintain the sliding window of size x
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();
        long sumL = 0, sumR = 0;
        int leftSize = 0, rightSize = 0;
        
        for (int i = 0; i < n; i++) {
            // Add nums[i] to the appropriate multiset
            if (left.isEmpty() || nums[i] <= left.lastKey()) {
                left.put(nums[i], left.getOrDefault(nums[i], 0) + 1);
                sumL += nums[i];
                leftSize++;
            } else {
                right.put(nums[i], right.getOrDefault(nums[i], 0) + 1);
                sumR += nums[i];
                rightSize++;
            }
            
            // Balance sizes: leftSize can be equal to rightSize or rightSize + 1
            while (leftSize > rightSize + 1) {
                int val = left.lastKey();
                removeFreq(left, val);
                sumL -= val;
                leftSize--;
                
                right.put(val, right.getOrDefault(val, 0) + 1);
                sumR += val;
                rightSize++;
            }
            while (rightSize > leftSize) {
                int val = right.firstKey();
                removeFreq(right, val);
                sumR -= val;
                rightSize--;
                
                left.put(val, left.getOrDefault(val, 0) + 1);
                sumL += val;
                leftSize++;
            }
            
            // When window reaches size x, calculate cost
            int start = i - x + 1;
            if (start >= 0) {
                long median = left.lastKey();
                ops[start] = sumR - sumL + median * (leftSize - rightSize);
                
                // Remove the element leaving the window (nums[start])
                int outVal = nums[start];
                if (outVal <= median) {
                    removeFreq(left, outVal);
                    sumL -= outVal;
                    leftSize--;
                } else {
                    removeFreq(right, outVal);
                    sumR -= outVal;
                    rightSize--;
                }
                
                // Re-balance after removal
                while (leftSize > rightSize + 1) {
                    int val = left.lastKey();
                    removeFreq(left, val);
                    sumL -= val;
                    leftSize--;
                    
                    right.put(val, right.getOrDefault(val, 0) + 1);
                    sumR += val;
                    rightSize++;
                }
                while (rightSize > leftSize) {
                    int val = right.firstKey();
                    removeFreq(right, val);
                    sumR -= val;
                    rightSize--;
                    
                    left.put(val, left.getOrDefault(val, 0) + 1);
                    sumL += val;
                    leftSize++;
                }
            }
        }
        
        // DP with rolling arrays
        long INF = (long) 1e18;
        long[] pre = new long[n + 1];
        long[] cur = new long[n + 1];
        
        for (int j = 1; j <= k; j++) {
            Arrays.fill(cur, INF);
            for (int i = j * x; i <= n; i++) {
                long take = (pre[i - x] == INF) ? INF : pre[i - x] + ops[i - x];
                cur[i] = Math.min(cur[i - 1], take);
            }
            long[] temp = pre;
            pre = cur;
            cur = temp;
        }
        
        return pre[n];
    }
    
    private void removeFreq(TreeMap<Integer, Integer> map, int val) {
        int count = map.get(val);
        if (count == 1) {
            map.remove(val);
        } else {
            map.put(val, count - 1);
        }
    }
}
