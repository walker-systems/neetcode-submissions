class Solution {
    public int maxArea(int[] heights) {
        int maxArea = 0; 
        int left = 0; 
        int right = heights.length - 1; 

        while (left < right) {
            int currHeight = Math.min(heights[left], heights[right]); 
            int currWidth = right - left; 
            int currArea = currHeight * currWidth; 

            maxArea = Math.max(maxArea, currArea); 

            if (heights[left] < heights[right]) {
                left++; 
            } else {
                right--; 
            }
        }
        return maxArea; 
    }
}
