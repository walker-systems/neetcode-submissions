class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> set = new HashSet<>(); 
        Arrays.sort(nums); 
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] == -nums[k]) {
                        List<Integer> triplet = new ArrayList<>(); 
                        triplet.add(nums[i]); 
                        triplet.add(nums[j]); 
                        triplet.add(nums[k]); 
                        set.add(triplet); 
                    }
                }
            }
        }
        List<List<Integer>> result = new ArrayList<>(set); 
        return result; 
    }
}
