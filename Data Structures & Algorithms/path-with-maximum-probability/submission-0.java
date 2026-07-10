// package leetcode.graphs;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.PriorityQueue;

public class Solution {

    // Edge in the adjacency list:
    // "to" is the neighbor node
    // "probability" is the edge success probability
    private record Edge(int to, double probability) {}

    // State stored in the max-heap:
    // "node" is the current node
    // "probability" is the best probability found so far for reaching that node
    private record State(int node, double probability) {}

    public double maxProbability(int n, int[][] edges, double[] succProb, int startNode, int endNode) {
        // Build an undirected graph because each edge can be traversed both ways.
        List<List<Edge>> graph = buildGraph(n, edges, succProb);

        // bestProbability[node] = highest probability found so far to reach "node"
        double[] bestProbability = new double[n];
        bestProbability[startNode] = 1.0; // Probability of being at the start is 100%

        // Max-heap by probability.
        // We always want to process the currently most promising node first.
        PriorityQueue<State> maxHeap = new PriorityQueue<>(
                (a, b) -> Double.compare(b.probability(), a.probability())
        );

        maxHeap.offer(new State(startNode, 1.0));

        while (!maxHeap.isEmpty()) {
            State current = maxHeap.poll();
            int currentNode = current.node();
            double currentProbability = current.probability();

            // Lazy deletion:
            // If this heap entry is worse than the best known probability for this node,
            // it is stale and should be ignored.
            if (currentProbability < bestProbability[currentNode]) {
                continue;
            }

            // Dijkstra property:
            // In a max-heap version, the first time we pop the end node,
            // we have found the maximum probability path to it.
            if (currentNode == endNode) {
                return currentProbability;
            }

            // Relax neighbors
            for (Edge edge : graph.get(currentNode)) {
                int nextNode = edge.to();
                double newProbability = currentProbability * edge.probability();

                if (newProbability > bestProbability[nextNode]) {
                    bestProbability[nextNode] = newProbability;
                    maxHeap.offer(new State(nextNode, newProbability));
                }
            }
        }

        // If endNode is unreachable, the answer is 0.0
        return 0.0;
    }

    private List<List<Edge>> buildGraph(int n, int[][] edges, double[] succProb) {
        List<List<Edge>> graph = new ArrayList<>(n);

        for (int node = 0; node < n; node++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            double probability = succProb[i];

            graph.get(from).add(new Edge(to, probability));
            graph.get(to).add(new Edge(from, probability));
        }

        return graph;
    }
}
