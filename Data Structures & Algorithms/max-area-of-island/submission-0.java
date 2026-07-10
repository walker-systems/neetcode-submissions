class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0; 
        }

        int rows = grid.length; 
        int cols = grid[0].length; 

        int maxArea = 0; 
        int curr = 0; 

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    curr = dfs(grid, r, c); 
                    if (curr > maxArea) { maxArea = curr; }
                }
            }
            
        }
        return maxArea; 
    }

    private int dfs(int[][] grid, int r, int c) {
        int rows = grid.length; 
        int cols = grid[0].length; 

        if (r < 0 || c < 0 || r == rows || c == cols || grid[r][c] == 0) {
            return 0;
        }
        grid[r][c] = 0; 
        int currArea = 1;
        currArea += dfs(grid, r + 1, c); 
        currArea += dfs(grid, r - 1, c); 
        currArea += dfs(grid, r, c + 1); 
        currArea += dfs(grid, r, c - 1); 
        
        return currArea;

    }
}
