class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // Feasibility check: if sum is greater than the max possible sum of digits
        if (sum > 9 * num) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        
        // Append as many 9s as possible
        int nines = sum / 9;
        for (int i = 0; i < nines; i++) {
            sb.append('9');
        }
        
        // Append the remainder digit if it exists
        int remainder = sum % 9;
        if (remainder > 0) {
            sb.append(remainder);
        }
        
        // Pad the remaining length with zeros
        while (sb.length() < num) {
            sb.append('0');
        }
        
        return sb.toString();
    }
}
