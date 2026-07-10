class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length; 

        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }

        int[][] directions = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        Queue<int[]> queue = new ArrayDeque<>(); 
        queue.add(new int[]{0, 0}); 

        grid[0][0] = 1; 

        int length = 1; 

        while (!queue.isEmpty()) {
            int size = queue.size(); 

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll(); 
                int r = curr[0]; 
                int c = curr[1]; 

                if (r == n - 1 && c == n - 1) {
                    return length; 
                }

                for (int[] dir : directions) {
                    int newR = r + dir[0]; 
                    int newC = c + dir[1]; 

                    if (newR >= 0 && newC >= 0 && newR < n && newC < n && grid[newR][newC] == 0) {
                        queue.add(new int[]{newR, newC});
                        grid[newR][newC] = 1;
                    }
                }
            }
            length++; 
        }
        return -1; 
    }
}