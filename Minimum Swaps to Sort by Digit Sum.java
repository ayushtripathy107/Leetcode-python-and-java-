import java.util.Arrays;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        // Each row stores: [digit_sum, number_value, original_index]
        int[][] elements = new int[n][3];
        
        for (int i = 0; i < n; i++) {
            elements[i][0] = getDigitSum(nums[i]);
            elements[i][1] = nums[i];
            elements[i][2] = i;
        }
        
        // Sort by digit sum first; if equal, sort by the number value
        Arrays.sort(elements, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        
        boolean[] visited = new boolean[n];
        int totalSwaps = 0;
        
        // Trace cycles to compute minimum swaps
        for (int i = 0; i < n; i++) {
            // Skip if already processed or already in the correct position
            if (visited[i] || elements[i][2] == i) {
                continue;
            }
            
            int cycleSize = 0;
            int j = i;
            
            while (!visited[j]) {
                visited[j] = true;
                j = elements[j][2]; // Move to the original index of the current element
                cycleSize++;
            }
            
            if (cycleSize > 1) {
                totalSwaps += (cycleSize - 1);
            }
        }
        
        return totalSwaps;
    }
    
    // Helper function to calculate the sum of digits
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
