class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int count = 0;
        int lastEnded = -1; // Keeps track of the end index of the last chosen palindrome

        // Iterate through all possible centers of palindromes
        for (int i = 0; i < 2 * n - 1; i++) {
            // Determine the left and right boundaries for center expansion
            int l = i / 2;
            int r = l + i % 2;

            // Expand outwards from the center
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                // If the current palindrome is completely to the right of the last chosen one
                if (l > lastEnded) {
                    int length = r - l + 1;
                    if (length >= k) {
                        count++;
                        lastEnded = r; // Update the boundary
                        break; // Move to the next center greedily
                    }
                } else {
                    // If it overlaps with the last chosen palindrome on the left, 
                    // expanding further won't help clear the boundary.
                    break; 
                }
                l--;
                r++;
            }
        }
        return count;
    }
}
