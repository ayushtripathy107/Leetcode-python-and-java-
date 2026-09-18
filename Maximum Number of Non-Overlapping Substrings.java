import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);
        
        // Step 1: Record the first and last occurrence of each character
        for (int i = 0; i < n; i++) {
            int charIdx = s.charAt(i) - 'a';
            if (first[charIdx] == -1) {
                first[charIdx] = i;
            }
            last[charIdx] = i;
        }
        
        List<int[]> intervals = new ArrayList<>();
        
        // Step 2: Extend intervals to meet condition 2
        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) continue;
            
            int start = first[i];
            int end = last[i];
            boolean isValid = true;
            
            for (int j = start; j <= end; j++) {
                int charIdx = s.charAt(j) - 'a';
                // Expand the right boundary if needed
                end = Math.max(end, last[charIdx]);
                // If a character inside starts before our interval, this start index is invalid
                if (first[charIdx] < start) {
                    isValid = false;
                    break;
                }
            }
            
            if (isValid) {
                intervals.add(new int[]{start, end});
            }
        }
        
        // Step 3: Sort intervals by end index to maximize non-overlapping segments
        Collections.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        
        List<String> result = new ArrayList<>();
        int prevEnd = -1;
        
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            
            // If the current interval doesn't overlap with the last chosen one
            if (start > prevEnd) {
                result.add(s.substring(start, end + 1));
                prevEnd = end;
            }
        }
        
        return result;
    }
}
