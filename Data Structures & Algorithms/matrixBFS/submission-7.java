class Solution {
    public int shortestPath(int[][] grid) {
        if (grid[0][0] == 1 || grid[grid.length - 1][grid[0].length - 1] == 1) {
            return -1; 
        }

        int rows = grid.length; 
        int cols = grid[0].length; 

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        int length = 0; 

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
                    int rowNew = r + dir[0]; 
                    int colNew = c + dir[1]; 

                    if (rowNew >= 0 && rowNew < rows
                        && colNew >= 0 && colNew < cols
                        && grid[rowNew][colNew] == 0) {
                            queue.add(new int[]{rowNew, colNew});
                            grid[rowNew][colNew] = 1;
                        }
                }
            }
            length++;
        }
        return -1; 
    }
}
