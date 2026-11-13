import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;
        long cost = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        int j = n - 1;

        for (int i = n - 1; i >= 0; i--) {
            // Merge smaller elements to the right under the new ceiling nums[i]
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                int l = dq.pollLast();
                int r = dq.isEmpty() ? j + 1 : dq.peekLast();
                cost += (long) (r - l) * (nums[i] - nums[l]);
            }
            dq.offerLast(i);

            // Shrink window from the right until cost is within budget k
            while (cost > k) {
                cost -= nums[dq.peekFirst()] - nums[j];
                if (dq.peekFirst() == j) {
                    dq.pollFirst();
                }
                j--;
            }

            // All subarrays starting at i and ending in [i, j] are valid
            ans += (j - i + 1);
        }

        return ans;
    }
}
