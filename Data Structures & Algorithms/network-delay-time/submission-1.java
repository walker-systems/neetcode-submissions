// package leetcode.graphs;

// import java.util.*;

public class Solution {

    // Record for the adjacency list (where does this edge go, and how long does it take?)
    public record Edge(int target, int time) {}
    
    // Record for the Priority Queue (which node are we at, and what is the total time from the source?)
    public record PqNode(int node, int time) implements Comparable<PqNode> {
        @Override
        public int compareTo(PqNode other) {
            return Integer.compare(this.time, other.time);
        }
    }

    // LeetCode Platform Note: 
    // The signature here exactly matches LeetCode's default for this problem. 
    // No signature changes should be needed when pasting into their editor.
    public int networkDelayTime(int[][] times, int n, int k) {
        
        // 1. Build the Adjacency List
        // Note: Nodes are 1-indexed (1 to n), so we initialize accordingly.
        Map<Integer, List<Edge>> adj = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            adj.put(i, new ArrayList<>());
        }
        
        for (int[] time : times) {
            int u = time[0]; // Source
            int v = time[1]; // Target
            int w = time[2]; // Time (Weight)
            adj.get(u).add(new Edge(v, w));
        }

        // 2. Initialize Dijkstra's structures
        // Instead of a map of shortest distances, we just need a visited set 
        // to track who has received the signal.
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<PqNode> pq = new PriorityQueue<>();
        
        // Start the signal at node 'k' at time 0
        pq.offer(new PqNode(k, 0));
        
        int maxTime = 0; // Tracks the time it takes for the LAST node to receive the signal
        
        // 3. Process the Priority Queue
        while (!pq.isEmpty()) {
            PqNode current = pq.poll();
            
            // Lazy Deletion: If we've already finalized this node, ignore the stale path.
            if (visited.contains(current.node())) {
                continue;
            }
            
            // Mark the node as having received the signal
            visited.add(current.node());
            
            // Update the total elapsed time. 
            // Because it's a Min-Heap, time only increases or stays the same.
            maxTime = Math.max(maxTime, current.time());
            
            // Relax the edges
            for (Edge neighbor : adj.get(current.node())) {
                if (!visited.contains(neighbor.target())) {
                    int newTime = current.time() + neighbor.time();
                    pq.offer(new PqNode(neighbor.target(), newTime));
                }
            }
        }
        
        // 4. Verification
        // If the size of our visited set equals 'n', all nodes got the signal.
        return visited.size() == n ? maxTime : -1;
    }
}