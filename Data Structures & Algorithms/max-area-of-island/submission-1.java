class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0; 
        int rows = grid.length;
        int cols = grid[0].length; 

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    int currentArea = dfs(grid, r, c); 
                    maxArea = Math.max(maxArea, currentArea); 
                }
            }
        }
        return maxArea;
    }

    private int dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0) {
            return 0; 
        }

        grid[r][c] = 0; 

        return 1 + dfs(grid, r + 1, c)
                 + dfs(grid, r - 1, c)
                 + dfs(grid, r, c + 1)
                 + dfs(grid, r, c - 1);
    }
}
