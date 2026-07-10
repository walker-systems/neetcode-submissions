class Solution {
    public int climbStairs(int n) {
        // Base Case protection for tiny inputs
        if (n == 1) return 1;
        if (n == 2) return 2;

        // 1. Initialize Table
        // Size n + 1 so we can store answer for 'n' at index 'n'
        int[] dp = new int[n + 1];

        // 2. Seed the Base Cases
        dp[1] = 1; // 1 way to reach step 1
        dp[2] = 2; // 2 ways to reach step 2

        // 3. Fill Table
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // 4. Return Top
        return dp[n];
    }
}