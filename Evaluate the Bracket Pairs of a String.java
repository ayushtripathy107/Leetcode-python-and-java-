import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Step 1: Store the knowledge pairs in a HashMap for O(1) lookups
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }
        
        StringBuilder result = new StringBuilder();
        StringBuilder currentKey = new StringBuilder();
        boolean inBracket = false;
        
        // Step 2: Parse the string sequentially
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            if (ch == '(') {
                inBracket = true;
            } else if (ch == ')') {
                inBracket = false;
                // Look up the key inside the map, default to "?" if not found
                String keyStr = currentKey.toString();
                result.append(map.getOrDefault(keyStr, "?"));
                // Reset the key builder for the next bracket pair
                currentKey.setLength(0);
            } else {
                if (inBracket) {
                    currentKey.append(ch);
                } else {
                    result.append(ch);
                }
            }
        }
        
        return result.toString();
    }
}
