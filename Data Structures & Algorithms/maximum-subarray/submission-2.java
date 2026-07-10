class Solution {
    public int maxSubArray(int[] nums) {
        // Initialize with the first element.
        // WARNING: Do NOT initialize with 0, because the answer might be negative 
        // (e.g., input [-5, -2, -9] -> max is -2).
        int currentSum = nums[0];
        int maxSum = nums[0];

        // Iterate starting from the 2nd element
        for (int i = 1; i < nums.length; i++) {
            
            // DECISION: Should I start a new subarray here (nums[i]), 
            // or extend the existing one (currentSum + nums[i])?
            // Logic: If currentSum is negative, start fresh. If positive, extend.
            currentSum = Math.max(nums[i], currentSum + nums[i]);

            // Always track the global maximum found so far
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}