public class Solution {

    /**
     * Generates all unique permutations of an array that might contain duplicates.
     * Time Complexity: O(N * N!) in the worst case, but significantly faster in practice due to pruning.
     * Space Complexity: O(N) for the recursion stack and temporary path.
     *
     * @param nums The input array of integers (may contain duplicates).
     * @return A list containing all unique permutations.
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        // 1. CRITICAL: Sort the array so duplicates are adjacent
        Arrays.sort(nums);
        
        boolean[] used = new boolean[nums.length];
        backtrack(nums, new ArrayList<>(), used, result);
        
        return result;
    }

    private void backtrack(int[] nums, List<Integer> currentPermutation, boolean[] used, List<List<Integer>> result) {
        // Base Case: The permutation is complete
        if (currentPermutation.size() == nums.length) {
            result.add(new ArrayList<>(currentPermutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // Skip if the number is already used in the current path
            if (used[i]) {
                continue;
            }

            // 2. THE PRUNING STEP
            // If it's a duplicate of the previous number, AND the previous number was NOT used 
            // in the current branch, we skip it. Why? Because we already explored all 
            // combinations with that number in this exact position during the previous loop iteration.
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            // Choose
            currentPermutation.add(nums[i]);
            used[i] = true;
            
            // Explore
            backtrack(nums, currentPermutation, used, result);
            
            // Un-choose (Backtrack)
            currentPermutation.remove(currentPermutation.size() - 1);
            used[i] = false;
        }
    }
}