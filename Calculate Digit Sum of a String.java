class Solution {
    public String digitSum(String s, int k) {
        // Repeat the process until the length of s is less than or equal to k
        while (s.length() > k) {
            StringBuilder nextRound = new StringBuilder();
            
            // Iterate through the string in steps of size k
            for (int i = 0; i < s.length(); i += k) {
                int sum = 0;
                
                // Calculate the digit sum for the current group of up to k characters
                for (int j = i; j < Math.min(i + k, s.length()); j++) {
                    sum += s.charAt(j) - '0';
                }
                
                // Append the group's sum to the new string
                nextRound.append(sum);
            }
            
            // Update s for the next round
            s = nextRound.toString();
        }
        
        return s;
    }
}
