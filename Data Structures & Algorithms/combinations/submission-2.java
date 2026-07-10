class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>(); 
        backtrack(result, new ArrayList<>(), 1, n, k); 
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> current, int start, int n, int k) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current)); 
            return;
        }

        int needed = k - current.size(); 
        int limit = n - needed + 1; 

        for (int i = start; i <= limit; i++) {
            current.add(i); 
            backtrack(result, current, i + 1, n, k); 
            current.remove(current.size() - 1); 
        }
    }
}