class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>(); 
        int max = 0; 

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]); 
        }

        for (int s : set) {
            if (!set.contains(s - 1)) {
                int count = 0; 
                int currNum = s; 
                while (set.contains(currNum)) {
                    currNum++; 
                    count++; 
                    max = Math.max(max, count); 
                }
            }
        }
        return max; 
    }
}
