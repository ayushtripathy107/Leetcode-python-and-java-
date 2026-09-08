class Solution {
    public int countCommas(int n) {
        int totalCommas = 0;
        
        // Every factor of 1000 introduces an extra comma threshold
        // e.g., numbers >= 1,000 get +1 comma, >= 1,000,000 get another +1 comma
        for (long i = 1000; i <= n; i *= 1000) {
            totalCommas += (n - i + 1);
        }
        
        return totalCommas;
    }
}
