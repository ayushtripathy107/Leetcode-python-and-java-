import java.util.Stack;

class Solution {
    public String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char ch : s.toCharArray()) {
            if (ch == ')') {
                // Collect characters inside the current matching parentheses
                StringBuilder sb = new StringBuilder();
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                
                // Pop the opening parenthesis '('
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                
                // Push the reversed characters back onto the stack
                for (int i = 0; i < sb.length(); i++) {
                    stack.push(sb.charAt(i));
                }
            } else {
                // Push normal characters and '(' onto the stack
                stack.push(ch);
            }
        }
        
        // Build the final result from the stack
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        
        // Reverse because stack pops in reverse order
        return result.reverse().toString();
    }
}
