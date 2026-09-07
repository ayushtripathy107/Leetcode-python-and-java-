class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        int n = s.length();
        
        // dp[i] stores the number of distinct subsequences of s[0...i-1] (including the empty string)
        int[] dp = new int[n + 1];
        dp[0] = 1; // Base case: empty string
        
        // Tracks the last seen 1-based index for each character 'a' through 'z'
        int[] lastSeen = new int[26];
        java.util.Arrays.fill(lastSeen, -1);
        
        for (int i = 1; i <= n; i++) {
            char c = s.charAt(i - 1);
            int charIdx = c - 'a';
            
            // Double the number of subsequences by either appending c or not
            dp[i] = (dp[i - 1] * 2) % MOD;
            
            // If this character has appeared before, subtract the duplicates
            if (lastSeen[charIdx] != -1) {
                int prevIdx = lastSeen[charIdx];
                dp[i] = (dp[i] - dp[prevIdx - 1] + MOD) % MOD;
            }
            
            // Record the current 1-based position of this character
            lastSeen[charIdx] = i;
        }
        
        // Subtract 1 to exclude the empty subsequence
        return (dp[n] - 1 + MOD) % MOD;
    }
}
