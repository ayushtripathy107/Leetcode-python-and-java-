class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long c1 = cost1;
        long c2 = cost2;
        long cb = costBoth;
        long n1 = need1;
        long n2 = need2;

        // Case 1: Buy all items separately
        long option1 = n1 * c1 + n2 * c2;

        // Case 2: Use type 3 items up to the smaller need, then fill the rest separately
        long minNeed = Math.min(n1, n2);
        long option2 = minNeed * cb + (n1 - minNeed) * c1 + (n2 - minNeed) * c2;

        // Case 3: Use type 3 items to cover the larger need completely
        long maxNeed = Math.max(n1, n2);
        long option3 = maxNeed * cb;

        return Math.min(option1, Math.min(option2, option3));
    }
}
