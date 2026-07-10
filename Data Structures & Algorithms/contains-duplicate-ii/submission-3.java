class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>(); 

        int L = 0; 

        for (int R = 0; R < nums.length; R++) {
            if (set.contains(nums[R])) {
                return true; 
            }
            if (R - L + 1 > k) {
                set.remove(nums[L]);
                L++; 
            }
            set.add(nums[R]);
        }
        return false;
    }
}