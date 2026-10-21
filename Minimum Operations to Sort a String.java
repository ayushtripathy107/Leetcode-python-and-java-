import java.util.Arrays;

class Solution {
    public int minOperations(String s) {
        int n = s.length();
        if (n <= 1) return 0;
        
        // Check if already sorted
        boolean isSorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                isSorted = false;
                break;
            }
        }
        if (isSorted) return 0;
        
        // Find the correct sorted characters
        char[] sortedChars = s.toCharArray();
        Arrays.sort(sortedChars);
        
        // Condition for 1 operation
        if (s.charAt(0) == sortedChars[0] || s.charAt(n - 1) == sortedChars[n - 1]) {
            return 1;
        }
        
        // Condition for impossible (-1)
        // If the first char is the absolute maximum, or last char is absolute minimum
        if (s.charAt(0) == sortedChars[n - 1] || s.charAt(n - 1) == sortedChars[0]) {
            return -1;
        }
        
        // Condition for 2 operations
        if (s.charAt(0) < s.charAt(n - 1)) {
            return 2;
        }
        
        // Otherwise, 3 operations are sufficient
        return 3;
    }
}
