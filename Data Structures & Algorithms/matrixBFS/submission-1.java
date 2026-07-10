class Solution {
    public int shortestPath(int[][] grid) {
        // 1. Edge Case: If Start or End is blocked
        if (grid[0][0] == 1 || grid[grid.length - 1][grid[0].length - 1] == 1) {
            return -1;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        
        // 2. Directions array (Right, Down, Left, Up)
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        // 3. Queue for BFS: Stores coordinates {r, c}
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});
        
        // 4. Mark Start as Visited (Block it with '1' to save space)
        grid[0][0] = 1; 

        int length = 0; // Steps taken

        // 5. BFS Loop
        while (!queue.isEmpty()) {
            int size = queue.size(); // Process this entire "layer"
            
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                
                // Check if we reached the goal
                if (r == rows - 1 && c == cols - 1) {
                    return length;
                }
                
                // Explore Neighbors
                for (int[] dir : directions) {
                    int newR = r + dir[0];
                    int newC = c + dir[1];
                    
                    // Check Bounds & if it's Land (0)
                    if (newR >= 0 && newC >= 0 && newR < rows && newC < cols && grid[newR][newC] == 0) {
                        queue.add(new int[]{newR, newC});
                        grid[newR][newC] = 1; // Mark visited immediately!
                    }
                }
            }
            length++; // We are taking one step deeper
        }
        
        return -1; // Goal unreachable
    }
}




