import java.util.Arrays;

class Solution {
    public int minOperations(String s) {
        int n = s.length();

        char[] arr = s.toCharArray();
        Arrays.sort(arr);

        char min = arr[0];
        char max = arr[n - 1];

        // Already sorted
        if (s.equals(new String(arr))) {
            return 0;
        }

        // Length 2 cannot be sorted
        if (n == 2) {
            return -1;
        }

        // First is maximum and last is minimum
        if (s.charAt(0) == max && s.charAt(n - 1) == min) {
            int minCount = 0;
            int maxCount = 0;

            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == min) minCount++;
                if (s.charAt(i) == max) maxCount++;
            }

            if (minCount > 1 || maxCount > 1) {
                return 2;
            }

            return 3;
        }

        // One operation is enough
        if (s.charAt(0) == min || s.charAt(n - 1) == max) {
            return 1;
        }

        return 2;
    }
}
