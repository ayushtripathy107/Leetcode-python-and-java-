class Solution {
    public int maxLength(int[] nums) {
        int maxVal = 0;
        int maxLcm = 1;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
            maxLcm = lcm(maxLcm, num);
        }
        
        int maxPossibleProd = maxLcm * maxVal;
        int n = nums.length;
        int longest = 0;

        for (int i = 0; i < n; i++) {
            int prod = 1;
            int currentGcd = 0;
            int currentLcm = 1;

            for (int j = i; j < n; j++) {
                prod *= nums[j];
                if (prod > maxPossibleProd) {
                    break;
                }
                
                currentGcd = gcd(currentGcd, nums[j]);
                currentLcm = lcm(currentLcm, nums[j]);

                if (prod == currentLcm * currentGcd) {
                    longest = Math.max(longest, j - i + 1);
                }
            }
        }

        return longest;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }
}
