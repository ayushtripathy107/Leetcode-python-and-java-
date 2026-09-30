class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] ans = new int[n];
        int depth = 0;
        
        for (int i = 0; i < n; i++) {
            if (seq.charAt(i) == '(') {
                depth++;
                ans[i] = depth % 2; // Assign to A (0) or B (1) based on current depth
            } else {
                ans[i] = depth % 2; // Keep it in the same group as its matching '('
                depth--;
            }
        }
        
        return ans;
    }
}
