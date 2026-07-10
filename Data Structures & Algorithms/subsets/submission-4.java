class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>(); 
        List<Integer> current = new ArrayList<>(); 

        subsetHelper(0, nums.length, nums, current, result); 

        return result; 
    }

    public void subsetHelper(int i, int n, int[] src, List<Integer> curr, List<List<Integer>> res) {
        
        if (i >= n) {
            res.add(new ArrayList<>(curr));
            return; 
        }

        curr.add(src[i]); 
        subsetHelper(i + 1, n, src, curr, res); 
        curr.remove(curr.size() - 1); 
        subsetHelper(i + 1, n, src, curr, res); 
    }
}
