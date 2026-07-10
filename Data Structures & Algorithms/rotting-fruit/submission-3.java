class Solution {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length; 
        int cols = grid[0].length;

         int remainingFresh = 0; 

        Queue<int[]> rotten = new ArrayDeque<>(); 
        boolean[][] visited = new boolean[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    rotten.add(new int[]{r, c}); 
                    // visited[r][c] = true;
                }  else if (grid[r][c] == 1) {
                    remainingFresh++; 
                }
            }
        }

        if (remainingFresh == 0) {
            return 0; 
        }

       

        int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

        int time = 0; 

        while (!rotten.isEmpty()) {
            int size = rotten.size(); 
            if (remainingFresh == 0) {
                    return time;
                }

            for (int i = 0; i < size; i++) {
                int[] curr = rotten.poll(); 
                int r = curr[0]; 
                int c = curr[1]; 

                for (int[] dir : directions) {
                    int newR = r + dir[0]; 
                    int newC = c + dir[1]; 

                    if (newR >= 0 && newC >= 0 && newR < rows && newC < cols
                        // && !visited[newR][newC] 
                        && grid[newR][newC] == 1) {
                            rotten.add(new int[]{newR, newC});
                            // visited[newR][newC] = true;
                            grid[newR][newC] = 2; 
                            remainingFresh--; 
                        }
                    
                }
            }
            time++; 
        }
        return -1;
    }
}
