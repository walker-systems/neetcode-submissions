class Solution {
    public int countPaths(int[][] grid) {
        return dfs(grid, 0, 0); 
    }

    private int dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 1) {
            return 0; 
        }

        if (r == grid.length - 1 && c == grid[0].length - 1) {
            return 1; 
        }

        grid[r][c] = 1; 

        int count = 0; 
        count += dfs(grid, r + 1, c); // Down 
        count += dfs(grid, r - 1, c); // Up 
        count += dfs(grid, r, c + 1); // Right 
        count += dfs(grid, r, c - 1); // Left

        grid[r][c] = 0; 
        
        return count;  
    }
}
