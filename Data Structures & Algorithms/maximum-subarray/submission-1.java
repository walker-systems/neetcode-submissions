class Solution {
    public int maxSubArray(int[] nums) {
        int currSum = 0; 
        int maxSum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            currSum = Math.max(currSum, 0); 
            currSum += nums[i]; 
            if (currSum > maxSum) {
                maxSum = currSum;
            }
        }
        return maxSum;
    }
}
