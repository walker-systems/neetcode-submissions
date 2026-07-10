class Solution {
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2 * n];
        for (int i = 0; i < 2; i++) {
            System.arraycopy(nums, 0, ans, i * n, n);
        }
        return ans;
    }
}