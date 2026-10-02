import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, String current, int open, int close, int max) {
        // Base case: string is complete
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }

        // Add open bracket if we have more to use
        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max);
        }
        
        // Add close bracket if it won't break well-formed rules
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, max);
        }
    }
}
