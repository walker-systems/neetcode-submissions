class Solution {
    public int trap(int[] height) {
        int left = 0; 
        int tallestLeft = 0; 
        
        int right = height.length - 1; 
        int tallestRight = 0; 

        int totalWater = 0; 

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= tallestLeft) {
                    tallestLeft = height[left]; 
                } else {
                    totalWater += tallestLeft - height[left]; 
                }
                left++; 
            } else {
                if (height[right] >= tallestRight) {
                    tallestRight = height[right]; 
                } else {
                    totalWater += tallestRight - height[right]; 
                }
                right--; 
            }
        }
        return totalWater;
    }
}
