// package leetcode.graphs;

// import java.util.*;

public class Solution {

    // Record represents our state in the Priority Queue.
    // maxElevation: The highest water level we had to brave to get to this cell.
    public record PqNode(int maxElevation, int row, int col) implements Comparable<PqNode> {
        @Override
        public int compareTo(PqNode other) {
            // We want the Min-Heap to prioritize the path with the LOWEST bottleneck.
            return Integer.compare(this.maxElevation, other.maxElevation);
        }
    }

    // LeetCode Platform Note: 
    // The signature here exactly matches LeetCode's requirements. 
    // No signature or array unpacking changes are needed for this one.
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        
        // Instead of a Map/Set for visited nodes, a 2D boolean array is the 
        // fastest and most memory-efficient choice for fixed grids.
        boolean[][] visited = new boolean[n][n];
        PriorityQueue<PqNode> pq = new PriorityQueue<>();
        
        // Start at top-left. The initial max elevation is simply the height of the starting square.
        pq.offer(new PqNode(grid[0][0], 0, 0));
        
        // Standard trick for moving Up, Down, Left, Right
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!pq.isEmpty()) {
            PqNode current = pq.poll();
            int r = current.row();
            int c = current.col();
            
            // Lazy Deletion: If we already locked in the best path to this cell, skip it.
            if (visited[r][c]) {
                continue;
            }
            
            // Lock in this cell. Because we use a Min-Heap, the first time we pop a cell, 
            // it is guaranteed to be via the path with the absolute lowest maximum elevation.
            visited[r][c] = true;
            
            // If we reached the bottom-right corner, we are done.
            if (r == n - 1 && c == n - 1) {
                return current.maxElevation();
            }
            
            // Explore all 4 valid neighbors
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                // Check bounds for BOTH row and column, and ensure we haven't permanently visited the neighbor
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    // "Relaxing" the path: The bottleneck to get to the neighbor is the greater of:
                    // 1. The bottleneck to get to the current cell.
                    // 2. The physical elevation of the neighbor cell itself.
                    int nextMax = Math.max(current.maxElevation(), grid[nr][nc]);
                    pq.offer(new PqNode(nextMax, nr, nc));
                }
            }
        }
        
        return -1; // Should never reach here given problem constraints
    }
}