class Solution {
    public int reverseDegree(String s) {
        int totalSum = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            // Calculate the position in the reversed alphabet ('a' = 26, 'b' = 25, ..., 'z' = 1)
            int reversedAlphabetIndex = 26 - (c - 'a');
            
            // 1-indexed position in the string
            int stringIndex = i + 1;
            
            // Add the product to the total sum
            totalSum += reversedAlphabetIndex * stringIndex;
        }
        
        return totalSum;
    }
}
