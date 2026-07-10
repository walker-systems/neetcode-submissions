// import java.util.*;

public class Solution {

    public record Edge(int target, int weight) {}
    
    public record PqNode(int node, int distance) implements Comparable<PqNode> {
        @Override
        public int compareTo(PqNode other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    // FIX 1: Changed int[][] to List<List<Integer>>
    public Map<Integer, Integer> shortestPath(int n, List<List<Integer>> edges, int src) {
        Map<Integer, List<Edge>> adj = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adj.put(i, new ArrayList<>());
        }
        
        // FIX 2: Changed array indexing to List.get()
        for (List<Integer> edge : edges) {
            adj.get(edge.get(0)).add(new Edge(edge.get(1), edge.get(2)));
        }

        Map<Integer, Integer> shortest = new HashMap<>();
        PriorityQueue<PqNode> pq = new PriorityQueue<>();
        
        pq.offer(new PqNode(src, 0));
        
        while (!pq.isEmpty()) {
            PqNode current = pq.poll();
            
            if (shortest.containsKey(current.node())) {
                continue;
            }
            
            shortest.put(current.node(), current.distance());
            
            for (Edge neighbor : adj.get(current.node())) {
                if (!shortest.containsKey(neighbor.target())) {
                    pq.offer(new PqNode(neighbor.target(), current.distance() + neighbor.weight()));
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            shortest.putIfAbsent(i, -1);
        }
        
        return shortest;
    }
}