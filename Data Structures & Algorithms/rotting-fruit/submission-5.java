class Solution {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0; 

        int rows = grid.length; 
        int cols = grid[0].length; 
        Queue<int[]> queue = new ArrayDeque<>(); 
        int freshCount = 0; 

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c}); 
                } else if (grid[r][c] == 1) {
                    freshCount++; 
                }
            }
        }

        if (freshCount == 0) return 0; 

        int minutes = 0; 
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; 

        while (!queue.isEmpty()) {
            int size = queue.size(); 
            boolean rottedSomething = false; 

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll(); 
                int r = curr[0]; 
                int c = curr[1]; 

                for (int[] dir : directions) {
                    int newR = r + dir[0]; 
                    int newC = c + dir[1]; 

                    if (newR >= 0 && newC >= 0 && newR < rows && newC < cols
                        && grid[newR][newC] == 1) {
                            grid[newR][newC] = 2; 
                            freshCount--; 
                            queue.offer(new int[]{newR, newC}); 
                            rottedSomething = true; 
                        }
                }
            }

            if (rottedSomething) {
                minutes++; 
            }
        }
        return freshCount == 0 ? minutes : -1; 
    }
}
