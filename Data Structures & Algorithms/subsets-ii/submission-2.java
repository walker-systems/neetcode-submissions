class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>(); 
        Arrays.sort(nums); 
        result.add(new ArrayList<>()); 

        int previousSize = 0; 

        for (int i = 0; i < nums.length; i++) {
            int startIndex = 0; 

            if (i > 0 && nums[i] == nums[i - 1]) {
                startIndex = previousSize; 
            }

            previousSize = result.size(); 

            for (int j = startIndex; j < previousSize; j++) {
                List<Integer> newSubset = new ArrayList<>(result.get(j));
                newSubset.add(nums[i]); 
                result.add(newSubset);
            }
        }

        return result; 
    }
}
