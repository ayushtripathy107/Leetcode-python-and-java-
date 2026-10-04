class Solution {
    public boolean checkValidString(String s) {
        int cmin = 0; // Minimum possible open parentheses
        int cmax = 0; // Maximum possible open parentheses
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                cmin++;
                cmax++;
            } else if (c == ')') {
                cmin--;
                cmax--;
            } else if (c == '*') {
                cmin--; // '*' acts as ')'
                cmax++; // '*' acts as '('
                // If '*' acts as an empty string, cmin and cmax remain unchanged from previous state, 
                // which is naturally covered within this expanded range.
            }
            
            // If cmax becomes negative, there are too many ')' brackets that cannot be balanced
            if (cmax < 0) {
                return false;
            }
            
            // cmin cannot be less than 0 because we cannot have negative open brackets at any point
            if (cmin < 0) {
                cmin = 0;
            }
        }
        
        // The string is valid if it is possible to have exactly 0 open parentheses at the end
        return cmin == 0;
    }
}
