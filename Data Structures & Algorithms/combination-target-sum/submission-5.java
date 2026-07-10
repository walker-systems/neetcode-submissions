class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>(); 
        Arrays.sort(nums); 
        backtrack(result, new ArrayList<>(), nums, target, 0); 
        return result; 
    }

    private void backtrack(List<List<Integer>> result, List<Integer> current, int[] candidates, int remain, int start) {
        if (remain == 0) {
            result.add(new ArrayList<>(current)); 
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (remain - candidates[i] < 0) {
                break;
            }

            current.add(candidates[i]); 
            backtrack(result, current, candidates, remain - candidates[i], i); 
            current.remove(current.size() - 1); 
        }
    }
}
