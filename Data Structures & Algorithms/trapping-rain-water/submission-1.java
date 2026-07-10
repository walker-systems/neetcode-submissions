class Solution {
    public int trap(int[] height) {
        int n = height.length; 
        if (n == 0) return 0; 

        int[] maxLeft = new int[n]; 
        maxLeft[0] = height[0]; 
        for (int i = 1; i < n; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i]); 
        }

        int[] maxRight = new int[n]; 
        maxRight[n - 1] = height[n - 1]; 
        for (int i = n - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], height[i]); 
        }

        int totalWater = 0; 
        for (int i = 0; i < n; i++) {
            int waterLevel = Math.min(maxLeft[i], maxRight[i]); 
            totalWater += waterLevel - height[i]; 
        }
        return totalWater;
    }
}
