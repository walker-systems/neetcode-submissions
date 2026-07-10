class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int totalSum = 0; 

        int currMax = 0; 
        int globalMax = nums[0]; 

        int currMin = 0; 
        int globalMin = nums[0]; 

        for (int num : nums) {
            totalSum += num; 

            currMax = Math.max(num, currMax + num); 
            globalMax = Math.max(globalMax, currMax);

            currMin = Math.min(num, currMin + num); 
            globalMin = Math.min(globalMin, currMin); 
        }

        if (globalMax < 0) {
            return globalMax; 
        }

        return Math.max(globalMax, totalSum - globalMin); 
    }
}