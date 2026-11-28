import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 0;
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        // 1. Count occurrences of each factor
        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        // 2. Sieve to count how many items have a factor divisible by f
        int[] multiplesCount = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            if (freq[f] == 0) continue;
            for (int m = f; m <= maxFactor; m += f) {
                multiplesCount[f] += freq[m];
            }
        }

        // 3. Sort items by price ascending to greedily take cheapest 2-for-1 deals
        Arrays.sort(items, (a, b) -> Integer.compare(a[1], b[1]));

        long remBudget = budget;
        long totalItems = 0;

        for (int[] item : items) {
            int factor = item[0];
            long price = item[1];

            // Only take deals strictly cheaper than buying 2 copies of the cheapest item
            if (price >= 2 * minPrice) {
                break;
            }

            long maxDeals = multiplesCount[factor] - 1;
            long canBuy = Math.min(maxDeals, remBudget / price);

            totalItems += 2 * canBuy;
            remBudget -= canBuy * price;

            if (remBudget < price) {
                break;
            }
        }

        // 4. Spend remaining budget on single copies of the cheapest item
        totalItems += remBudget / minPrice;

        return (int) totalItems;
    }
}
