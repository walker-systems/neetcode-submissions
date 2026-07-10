class Solution {
    public int numIslands(char[][] grid) {
        int ROWS = grid.length;
        int COLS = grid[0].length;
        int islandCount = 0; 
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (grid[i][j] == '1') {
                    islandCount++;
                    dfs(grid, i, j);
                }
            }
        }
        return islandCount; 
    }

    private void dfs(char[][] grid, int r, int c) {

        if (!(Math.min(r, c) < 0 || r == grid.length || c == grid[0].length)) {
            if (grid[r][c] == '0') {
                return;
            } else {
                grid[r][c] = '0';
                dfs(grid, r + 1, c);
                dfs(grid, r - 1, c);
                dfs(grid, r, c + 1); 
                dfs(grid, r, c - 1); 
            }
        }
        
    }
}
