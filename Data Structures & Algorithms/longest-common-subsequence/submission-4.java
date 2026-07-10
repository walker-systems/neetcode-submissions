class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1.length() < text2.length()) {
            return longestCommonSubsequence(text2, text1); 
        }

        int m = text1.length(); 
        int n = text2.length(); 

        int[] dp = new int[n + 1];

        for (int i = m - 1; i >= 0; i--) {
            int prevDiagonal = 0; 

            for (int j = n - 1; j >= 0; j--) {
                int currentVal = dp[j];

                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[j] = 1 + prevDiagonal; 
                } else {
                    dp[j] = Math.max(dp[j], dp[j + 1]);
                }

                prevDiagonal = currentVal; 
            }
        }
        return dp[0]; 
    }
}
