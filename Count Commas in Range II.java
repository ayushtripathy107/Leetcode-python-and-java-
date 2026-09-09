class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long start = 1000;
        long commasPerNumber = 1;

        // Loop through each comma-bracket interval
        while (start <= n) {
            // The interval ends right before the next comma group threshold (e.g., 999,999)
            long end = Math.min(n, start * 1000 - 1);
            
            // Number of integers in the current range [start, end]
            long count = end - start + 1;
            
            // Add the total commas contributed by this range
            totalCommas += count * commasPerNumber;
            
            // Move to the next magnitude range
            start *= 1000;
            commasPerNumber++;
        }

        return totalCommas;
    }
}
