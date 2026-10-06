class Solution {
    public int minAddToMakeValid(String s) {
        int openNeed = 0;
        int closeNeed = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            if (ch == '(') {
                // We have an opening parenthesis that needs a matching ')'
                closeNeed++;
            } else {
                // We encountered a ')'
                if (closeNeed > 0) {
                    // It successfully matches a previously unmatched '('
                    closeNeed--;
                } else {
                    // No unmatched '(' available, so we need to add a '('
                    openNeed++;
                }
            }
        }

        // The total additions required is the sum of unmatched '(' and ')'
        return openNeed + closeNeed;
    }
}
