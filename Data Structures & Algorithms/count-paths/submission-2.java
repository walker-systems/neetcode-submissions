class Solution {
    public int uniquePaths(int m, int n) {

        int[] currRow = new int[n];
        currRow[n - 1] = 1; 

        for (int r = m - 1; r >= 0; r--) {
            for (int j = n - 2; j >= 0; j--) {
                currRow[j] = currRow[j] + currRow[j + 1];
            }
        }

        return currRow[0]; 
        
    }
}
