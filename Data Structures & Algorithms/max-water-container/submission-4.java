class Solution {
    public int maxArea(int[] heights) {
        int maxArea = 0; 
        int left = 0; 
        int right = heights.length - 1; 

        int tallestLeft = 0; 
        int tallestRight = 0; 

        while (left < right) {
            tallestLeft = Math.max(tallestLeft, heights[left]); 
            tallestRight = Math.max(tallestRight, heights[right]); 

            int currHeight = Math.min(tallestLeft, tallestRight); 
            int currWidth = right - left; 
            int currArea = currHeight * currWidth; 

            maxArea = Math.max(maxArea, currArea); 

            if (tallestLeft < tallestRight) {
                left++; 
            } else {
                right--; 
            }
        }
        return maxArea; 
    }
}
