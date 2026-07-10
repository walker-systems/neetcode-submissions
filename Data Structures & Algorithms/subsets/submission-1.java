

public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, int start, List<Integer> currentSubset, List<List<Integer>> result) {
        // 1. Snapshot: Add whatever we have currently to the result
        // CRITICAL: Must make a NEW copy, otherwise we save a reference to an empty list later.
        result.add(new ArrayList<>(currentSubset));

        // 2. Iterate: Try adding every remaining number
        for (int i = start; i < nums.length; i++) {
            
            // A. Make a choice (Add number)
            currentSubset.add(nums[i]);

            // B. Explore (Recurse with i + 1)
            // Passing 'i + 1' ensures we never reuse the same element or go backwards
            backtrack(nums, i + 1, currentSubset, result);

            // C. Undo the choice (Backtrack)
            currentSubset.remove(currentSubset.size() - 1);
        }
    }
}