class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int openNeeded = 0; // Tracks the number of '(' we currently need to match with '))'
        int i = 0;
        int n = s.length();

        while (i < n) {
            if (s.charAt(i) == '(') {
                // Every '(' requires two consecutive ')'
                openNeeded += 2;
                
                // If we accumulated an odd number of needed ')', it means a previous '('
                // only got one ')' before this new '(' appeared. We must insert 1 ')' immediately.
                if (openNeeded % 2 != 0) {
                    insertions++;
                    openNeeded--;
                }
                i++;
            } else {
                // We encountered a ')'
                // Check if it's a double close consecutive pair '))'
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    openNeeded -= 2;
                    i += 2; // Move past both ')'
                } else {
                    // Single ')' found, we need to insert another ')' to make it a pair
                    insertions++;
                    openNeeded -= 2;
                    i++; // Move past the single ')'
                }

                // If openNeeded drops below 0, it means we have extra ')' without a matching '('
                // We must insert 1 '(' to balance it
                if (openNeeded < 0) {
                    insertions++;
                    openNeeded += 2; // The newly inserted '(' accounts for the 2 ')' we just processed
                }
            }
        }

        // Any remaining openNeeded represents unmatched '(' that need ')' inserted at the end
        return insertions + openNeeded;
    }
}
