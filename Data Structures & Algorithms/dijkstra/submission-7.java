// package leetcode.graphs;

// import java.util.*;

public class Solution {

    // Modern Java: Records provide a clean, immutable way to structure data.
    // Edge represents a directed connection from the current node.
    public record Edge(int target, int weight) {}
    
    // PqNode represents the current path distance to a specific node.
    // Implementing Comparable allows the PriorityQueue to sort automatically by distance.
    public record PqNode(int node, int distance) implements Comparable<PqNode> {
        @Override
        public int compareTo(PqNode other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    // --- NEETCODE PLATFORM CHANGE ---
    // Change signature to: public Map<Integer, Integer> shortestPath(int n, List<List<Integer>> edges, int src) {
    public Map<Integer, Integer> shortestPath(int n, List<List<Integer>> edges, int src) {
        
        // 1. Build the Adjacency List
        Map<Integer, List<Edge>> adj = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adj.put(i, new ArrayList<>());
        }
        
        // --- NEETCODE PLATFORM CHANGE ---
        // Change loop to: 
        // for (List<Integer> edge : edges) {
        //     adj.get(edge.get(0)).add(new Edge(edge.get(1), edge.get(2)));
        // }
        for (List<Integer> edge : edges) {
            adj.get(edge.get(0)).add(new Edge(edge.get(1), edge.get(2)));
        }

        // 2. Initialize Data Structures for Dijkstra
        Map<Integer, Integer> shortest = new HashMap<>();
        PriorityQueue<PqNode> pq = new PriorityQueue<>();
        
        // Start the algorithm from the source node with a distance of 0
        pq.offer(new PqNode(src, 0));
        
        // 3. Process the Priority Queue
        while (!pq.isEmpty()) {
            PqNode current = pq.poll();
            
            // If the node is already in the map, we've already found its absolute shortest path.
            // This implicitly handles "stale" elements in the queue (Lazy Deletion).
            if (shortest.containsKey(current.node())) {
                continue;
            }
            
            // Finalize the shortest distance for the current node
            shortest.put(current.node(), current.distance());
            
            // Relax the edges: check all outgoing connections
            for (Edge neighbor : adj.get(current.node())) {
                // Only add to queue if we haven't already finalized the shortest path to the target
                if (!shortest.containsKey(neighbor.target())) {
                    int newDistance = current.distance() + neighbor.weight();
                    pq.offer(new PqNode(neighbor.target(), newDistance));
                }
            }
        }
        
        // 4. Handle Unreachable Nodes
        // Any node not reached gets a distance of -1 as per requirements.
        for (int i = 0; i < n; i++) {
            shortest.putIfAbsent(i, -1);
        }
        
        return shortest;
    }
}