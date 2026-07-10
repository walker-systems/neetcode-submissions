class Solution {
    public int uniquePaths(int m, int n) {
        // Represents the "Bottom" row initially
        int[] row = new int[n];
        Arrays.fill(row, 1); 

        // Iterate upwards for remaining rows (m-1 times)
        // We skip the very last row because we already initialized it to 1s
        for (int i = 0; i < m - 1; i++) {
            
            // Iterate backwards or forwards? For this 1D optimization, 
            // we usually iterate right-to-left if we want to mimic the 2D logic 
            // strictly, but for pure summing, left-to-right also works here.
            // Strict logic: row[j] = row[j] (value from below) + row[j+1] (value from right)
            
            for (int j = n - 2; j >= 0; j--) {
                // New value = (Old value which represents DOWN) + (Neighbor value which represents RIGHT)
                row[j] = row[j] + row[j + 1];
            }
        }
        
        return row[0];
    }
}


