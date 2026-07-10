// import java.util.ArrayList;
// import java.util.List;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Create a boolean array to track which numbers are currently in our path
        boolean[] used = new boolean[nums.length];
        
        backtrack(result, new ArrayList<>(), nums, used);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> current, int[] nums, boolean[] used) {
        // Base Case: The permutation contains all numbers
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Notice the loop ALWAYS starts at 0, not at a 'start' index
        for (int i = 0; i < nums.length; i++) {
            // If the number is already in our current permutation, skip it
            if (used[i]) {
                continue;
            }

            // 1. The Choice
            used[i] = true;
            current.add(nums[i]);
            
            // 2. The Exploration
            backtrack(result, current, nums, used);
            
            // 3. The Undo (Backtrack)
            used[i] = false;
            current.remove(current.size() - 1);
        }
    }
}