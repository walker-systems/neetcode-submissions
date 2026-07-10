class Solution {
    int[] memo;
    
    public int climbStairs(int n) {
        memo = new int[n + 1];
        return climb(0, n);
    }

    public int climb(int i, int n) {
        
        if (i >= n) {
            if (i == n) {
                return 1;
            } else {
                return 0;
            }
        }

        if (memo[i] > 0) {
            return memo[i];
        } else {
            memo[i] = climb(i + 1, n) + climb(i + 2, n);
        }
        return memo[i];
    }


}
