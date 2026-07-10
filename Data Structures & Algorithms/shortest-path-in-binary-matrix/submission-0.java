class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1 || grid[grid.length - 1][grid[0].length - 1] == 1) {
            return -1; 
        }

        int rows = grid.length; 
        int cols = grid[0].length; 

        // right, down, left, up, down+right, down+left, up+right, up+left
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        int length = 1; 

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int size = queue.size(); 

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll(); 
                int r = curr[0];
                int c = curr[1]; 

                if (r == grid.length - 1 && c == grid[0].length - 1) {
                    return length; 
                }

                for (int[] dir : directions) {
                    int newR = r + dir[0]; 
                    int newC = c + dir[1];
                    if (Math.min(newR, newC) >= 0
                        && newR < grid.length && newC < grid[0].length
                        && grid[newR][newC] == 0) {
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