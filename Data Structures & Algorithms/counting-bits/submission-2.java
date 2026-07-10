class Solution {
    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        result[0] = 0; 

        for (int i = 1; i <= n; i++) {
            int currCount = 0;
            int j = i;
            while (j > 0) {
                if ((j & 1) == 1) {
                    currCount++;
                }
                j = j >> 1;
            }
            result[i] = currCount;
        }
        return result;
    }
}
