class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Find the closest point on the rectangle to the circle center
        int closestX = Math.max(x1, Math.min(xCenter, x2));
        int closestY = Math.max(y1, Math.min(yCenter, y2));
        
        // Calculate the distance components between the circle center and this closest point
        int distanceX = xCenter - closestX;
        int distanceY = yCenter - closestY;
        
        // Calculate the squared distance
        int squaredDistance = (distanceX * distanceX) + (distanceY * distanceY);
        
        // If the squared distance is less than or equal to the squared radius, they overlap
        return squaredDistance <= (radius * radius);
    }
}
