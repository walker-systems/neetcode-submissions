class Solution {
    public int uniquePaths(int m, int n) {
        
        int[][] cache = new int[m][n]; 

        return topDown(m, n, 0, 0, cache);
    }

    private int topDown (int rows, int cols, int r, int c, int[][] cache) {

        if (r < 0 || c < 0) return 0; 
        if (r == rows || c == cols) return 0; 
        if (r == rows - 1 && c == cols - 1) return 1; 
        if (cache[r][c] != 0) return cache[r][c];

        cache[r][c] = topDown(rows, cols, r + 1, c, cache) + 
                        topDown(rows, cols, r, c + 1, cache);

        return cache[r][c];
    }
}
