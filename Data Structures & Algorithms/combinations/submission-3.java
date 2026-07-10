// import java.util.ArrayList;
// import java.util.List;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        // Start the recursive descent at number 1
        backtrack(result, new ArrayList<>(), 1, n, k);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> current, int start, int n, int k) {
        // 1. Base Case: The combination has reached the required length
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        // 2. The Mathematical Pruning Guard
        // How many more numbers do we need to reach length 'k'?
        int needed = k - current.size();
        
        // What is the absolute highest number we can start from and still have 
        // enough remaining numbers to finish the combination?
        int limit = n - needed + 1;

        // 3. Iterate through choices, stopping at our mathematically calculated limit
        for (int i = start; i <= limit; i++) {
            current.add(i);
            
            // Move to the next number
            backtrack(result, current, i + 1, n, k);
            
            // Backtrack
            current.remove(current.size() - 1);
        }
    }
}