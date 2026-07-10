class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int maxSum = nums[0]; 
        int currMax = nums[0]; 
        int currMin = nums[0]; 
        int minSum = nums[0]; 
        int totalSum = nums[0]; 

        for (int i = 1; i < nums.length; i++) {
            currMax = Math.max(nums[i], nums[i] + currMax); 
            maxSum = Math.max(currMax, maxSum);

            currMin = Math.min(nums[i], nums[i] + currMin); 
            minSum = Math.min(currMin, minSum);

            totalSum += nums[i]; 
        }
        if (totalSum == minSum) return maxSum; 
        
        return Math.max(maxSum, totalSum - minSum); 
    }
}