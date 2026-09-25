import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        // Use a TreeSet to automatically sort elements and remove duplicates
        Set<String> resultSet = evaluate(expression);
        return new ArrayList<>(resultSet);
    }

    private Set<String> evaluate(String expr) {
        Set<String> set = new TreeSet<>();
        
        // Find the first occurrence of an opening brace
        int firstOpen = expr.indexOf('{');
        
        // Base Case: No braces found, it's a plain string. Return it as a single-element set.
        if (firstOpen == -1) {
            set.add(expr);
            return set;
        }

        // Find the matching closing brace for 'firstOpen'
        int i = firstOpen;
        int braceCount = 0;
        while (i < expr.length()) {
            if (expr.charAt(i) == '{') braceCount++;
            else if (expr.charAt(i) == '}') braceCount--;
            
            if (braceCount == 0) break;
            i++;
        }
        int matchingClose = i;

        // Split the expression into three parts: prefix, interior of braces, and suffix
        String prefix = expr.substring(0, firstOpen);
        String inner = expr.substring(firstOpen + 1, matchingClose);
        String suffix = expr.substring(matchingClose + 1);

        // Split the 'inner' content by top-level commas only
        List<String> innerParts = splitByTopLevelCommas(inner);

        // Evaluate prefix and suffix sets recursively
        Set<String> prefixSet = evaluate(prefix);
        Set<String> suffixSet = evaluate(suffix);

        // Process each option inside the braces
        for (String part : innerParts) {
            Set<String> innerSet = evaluate(part);
            
            // Perform cartesian product: prefix * inner * suffix
            for (String pre : prefixSet) {
                for (String in : innerSet) {
                    for (String suf : suffixSet) {
                        set.add(pre + in + suf);
                    }
                }
            }
        }

        return set;
    }

    // Helper method to split strings by commas that are not nested inside other braces
    private List<String> splitByTopLevelCommas(String s) {
        List<String> parts = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int braceCount = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{') braceCount++;
            else if (c == '}') braceCount--;

            if (c == ',' && braceCount == 0) {
                parts.add(sb.toString());
                sb.setLength(0); // Clear buffer
            } else {
                sb.append(c);
            }
        }
        parts.add(sb.toString());
        return parts;
    }
}
