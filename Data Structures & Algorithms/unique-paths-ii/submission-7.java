class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        
        int[][] cache = new int[obstacleGrid.length][obstacleGrid[0].length];

        return topDown(0, 0, cache, obstacleGrid);
    }

    private int topDown(int r, int c, int[][] cache, int[][] grid) {
        int m = cache.length;
        int n = cache[0].length; 

        if (r < 0 || c < 0 ||
            r == m || c == n ||
            grid[r][c] == 1) { return 0; } 
        
        if (r == m - 1 && c == n - 1) {
            return 1;
        }

        if (cache[r][c] != 0) {
            return cache[r][c];
        }

        cache[r][c] = topDown(r + 1, c, cache, grid) +
                      topDown(r, c + 1, cache, grid);
        
        return cache[r][c];
    }
}