// import java.util.ArrayList;
// import java.util.List;

class Solution {
    /**
     * Generates all possible permutations of an array of unique integers.
     * Time Complexity: O(N * N!) where N is the length of the array.
     * Space Complexity: O(N) for the recursion stack and temporary path.
     *
     * @param nums The input array of unique integers.
     * @return A list containing all possible permutations.
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Defensive programming: handle null or empty input gracefully
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        // boolean arrays in Java default to false, which is exactly what we need
        boolean[] used = new boolean[nums.length];
        
        // Start the backtracking process
        backtrack(nums, new ArrayList<>(), used, result);
        
        return result;
    }

    /**
     * Helper method to perform the recursive backtracking.
     */
    private void backtrack(int[] nums, List<Integer> currentPermutation, boolean[] used, List<List<Integer>> result) {
        // Base Case: If our current path size equals the input array size, we have a complete permutation
        if (currentPermutation.size() == nums.length) {
            // CRITICAL: We must create a *new* ArrayList containing the current elements.
            // If we just added 'currentPermutation', future modifications would corrupt our saved results.
            result.add(new ArrayList<>(currentPermutation));
            return;
        }

        // Iterate through all possible choices in the array
        for (int i = 0; i < nums.length; i++) {
            // Skip this number if it is already participating in the current permutation
            if (used[i]) {
                continue;
            }

            // 1. Choose: Add the number to our path and mark it as used
            currentPermutation.add(nums[i]);
            used[i] = true;

            // 2. Explore: Recursively build the rest of the permutation
            backtrack(nums, currentPermutation, used, result);

            // 3. Un-choose (Backtrack): Remove the last added number and mark it as unused
            // This allows the loop to pick a different number for this position on the next iteration
            currentPermutation.remove(currentPermutation.size() - 1);
            used[i] = false;
        }
    }
}