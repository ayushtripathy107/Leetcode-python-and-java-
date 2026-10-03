import java.util.Stack;

class Solution {
    public int longestValidParentheses(String s) {
        int maxLength = 0;
        Stack<Integer> stack = new Stack<>();
        
        // Push -1 as a base index for calculating valid substring lengths
        stack.push(-1);
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // Store the index of the opening parenthesis
                stack.push(i);
            } else {
                // Pop the last unmatched opening parenthesis or base index
                stack.pop();
                
                if (stack.isEmpty()) {
                    // If empty, the current ')' is unmatched; push its index as the new base
                    stack.push(i);
                } else {
                    // Calculate the length of the current valid substring
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }
        
        return maxLength;
    }
}
