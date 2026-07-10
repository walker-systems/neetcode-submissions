class Solution {
    public int climbStairs(int n) {
        // Create cache. Size is n+1 so we can use index 'n' comfortably.
        // Default value of int array is 0.
        int[] memo = new int[n + 1];
        return dfs(n, memo);
    }

    private int dfs(int n, int[] memo) {
        // 1. Base Cases
        if (n == 1) return 1;
        if (n == 2) return 2;

        // 2. Check Cache (Have we solved this before?)
        if (memo[n] != 0) {
            return memo[n];
        }

        // 3. Compute and Store
        memo[n] = dfs(n - 1, memo) + dfs(n - 2, memo);
        return memo[n];
    }
}