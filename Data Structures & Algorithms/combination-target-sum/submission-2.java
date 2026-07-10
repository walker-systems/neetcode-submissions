class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>(); 
        Arrays.sort(nums); 
        backtrack(nums, target, 0, new ArrayList<>(), result); 
        return result;
    }

    private void backtrack(int[] candidates, int remaining, int start, List<Integer> currentComb, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(currentComb)); 
            return;
        }

        if (remaining < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {

            if (remaining - candidates[i] < 0) {
                break; // Since it's sorted, all future numbers are too big too.
            }
            currentComb.add(candidates[i]);

            backtrack(candidates, remaining - candidates[i], i, currentComb, result);

            currentComb.remove(currentComb.size() - 1);
        }
    }
}
