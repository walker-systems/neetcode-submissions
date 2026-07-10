// import java.util.*;

// Change 1:
// Keep the class name exactly as "Solution" because the NeetCode / LeetCode
// editor expects that name.
class Solution {

    // Change 2:
    // The method signature must stay exactly as the editor gives it:
    // public int minimumSpanningTree(List<List<Integer>> edges, int n)
    // So instead of int[][] edges, we read from List<List<Integer>>.
    public int minimumSpanningTree(List<List<Integer>> edges, int n) {

        // Build adjacency list:
        // graph[u] contains int[] {neighbor, weight}
        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Change 3:
        // Each edge comes in as a List<Integer> of size 3:
        // [u, v, w]
        // We convert that into an undirected adjacency list.
        for (List<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            int w = edge.get(2);

            graph.get(u).add(new int[]{v, w});
            graph.get(v).add(new int[]{u, w});
        }

        boolean[] inMst = new boolean[n];

        // Min-heap storing:
        // int[] {weight, vertex}
        // Prim's always picks the cheapest edge that brings in a new vertex.
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Start from node 0 with cost 0
        minHeap.offer(new int[]{0, 0});

        int totalWeight = 0;
        int visitedCount = 0;

        while (!minHeap.isEmpty() && visitedCount < n) {
            int[] current = minHeap.poll();
            int edgeWeight = current[0];
            int vertex = current[1];

            // Lazy deletion:
            // If this vertex is already in the MST, skip this stale heap entry.
            if (inMst[vertex]) {
                continue;
            }

            inMst[vertex] = true;
            totalWeight += edgeWeight;
            visitedCount++;

            for (int[] neighbor : graph.get(vertex)) {
                int nextVertex = neighbor[0];
                int nextWeight = neighbor[1];

                if (!inMst[nextVertex]) {
                    minHeap.offer(new int[]{nextWeight, nextVertex});
                }
            }
        }

        // If we did not include all vertices, the graph is disconnected.
        return visitedCount == n ? totalWeight : -1;
    }
}