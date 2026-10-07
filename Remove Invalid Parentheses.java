import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) return result;
        
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        queue.add(s);
        visited.add(s);
        
        boolean foundValidAtThisLevel = false;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            // Process the current level completely
            for (int i = 0; i < levelSize; i++) {
                String current = queue.poll();
                
                // If it's valid, add it to our results and signal to stop going deeper
                if (isValid(current)) {
                    result.add(current);
                    foundValidAtThisLevel = true;
                }
                
                // If we already found a valid string at this level, don't generate next states
                if (foundValidAtThisLevel) continue;
                
                // Generate all possible strings by removing one parenthesis
                for (int j = 0; j < current.length(); j++) {
                    char c = current.charAt(j);
                    if (c != '(' && c != ')') continue; // Skip letters
                    
                    // Create substring omitting the character at index j
                    String nextState = current.substring(0, j) + current.substring(j + 1);
                    
                    if (!visited.contains(nextState)) {
                        visited.add(nextState);
                        queue.add(nextState);
                    }
                }
            }
            
            // Break early once we've fully processed the level containing valid answers
            if (foundValidAtThisLevel) {
                break;
            }
        }
        
        return result;
    }
    
    // Helper function to check if a string has balanced parentheses
    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) return false; // More closing than opening at any point
            }
        }
        return count == 0;
    }
}
