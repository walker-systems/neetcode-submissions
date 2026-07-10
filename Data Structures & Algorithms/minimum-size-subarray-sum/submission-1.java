class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int currSum = 0;
        int currLength = nums.length + 1;
        int L = 0; 

        for (int i = 0; i < nums.length; i++) {
            currSum += nums[i]; 
            while (currSum >= target) {
                currLength = Math.min(currLength, i - L + 1);
                currSum -= nums[L];
                L++;
            }
        }
        return (currLength != nums.length + 1) ? currLength : 0;
    }
}