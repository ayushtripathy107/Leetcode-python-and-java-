class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // Check if there is an overlap along both X and Y axes
        return rec1[0] < rec2[2] && rec2[0] < rec1[2] &&
               rec1[1] < rec2[3] && rec2[1] < rec1[3];
    }
}
