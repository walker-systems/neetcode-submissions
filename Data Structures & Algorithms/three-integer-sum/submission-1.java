class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); 
        List<List<Integer>> triplets = new ArrayList<>(); 

        for (int first = 0; first < nums.length - 2; first++) {
            if (nums[first] > 0) {
                break;
            }

            if (first > 0 && nums[first] == nums[first - 1]) {
                continue; 
            }

            int left = first + 1; 
            int right = nums.length - 1; 

            while (left < right) {
                int sum = nums[first] + nums[left] + nums[right]; 

                if (sum < 0) {
                    left++; 
                } else if (sum > 0) {
                    right--; 
                } else {
                    triplets.add(List.of(
                        nums[first],
                        nums[left],
                        nums[right]));
                    left++; 
                    right--; 

                    while (left < right 
                            && nums[left] == nums[left - 1]) {
                        left++; 
                    }
                    while (left < right
                            && nums[right] == nums[right + 1]) {
                        right--; 
                    }
                }
            }
        }
        return triplets; 
    }
}
