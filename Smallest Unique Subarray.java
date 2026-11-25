import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final long BASE1 = 1315423911L;
    private static final long MOD1 = 1000000007L;
    private static final long BASE2 = 1313131L;
    private static final long MOD2 = 1000000009L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int low = 1, high = n, ans = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hasUniqueSubarray(nums, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUniqueSubarray(int[] nums, int len) {
        int n = nums.length;
        Map<Long, Integer> freq = new HashMap<>();

        long h1 = 0, h2 = 0;
        long p1 = 1, p2 = 1;

        for (int i = 0; i < len - 1; i++) {
            p1 = (p1 * BASE1) % MOD1;
            p2 = (p2 * BASE2) % MOD2;
        }

        for (int i = 0; i < n; i++) {
            // Add current element to the rolling hash
            h1 = (h1 * BASE1 + nums[i]) % MOD1;
            h2 = (h2 * BASE2 + nums[i]) % MOD2;

            if (i >= len - 1) {
                // Combine both hashes into a single 64-bit key
                long combinedHash = (h1 << 32) | h2;
                freq.put(combinedHash, freq.getOrDefault(combinedHash, 0) + 1);

                // Remove the leftmost element of the current window
                int leftVal = nums[i - len + 1];
                h1 = (h1 - (leftVal * p1) % MOD1 + MOD1) % MOD1;
                h2 = (h2 - (leftVal * p2) % MOD2 + MOD2) % MOD2;
            }
        }

        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}
