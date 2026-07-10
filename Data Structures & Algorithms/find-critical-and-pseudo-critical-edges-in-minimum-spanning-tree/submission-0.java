// package leetcode.graphs;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Comparator;
// import java.util.List;

public class Solution {

    private static final int DISCONNECTED = Integer.MAX_VALUE;
    private static final int NO_EDGE = -1;

    private record Edge(int from, int to, int weight, int originalIndex) {}

    private static final class UnionFind {
        private final int[] parent;
        private final int[] size;

        private UnionFind(int n) {
            parent = new int[n];
            size = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        private int find(int node) {
            if (parent[node] != node) {
                parent[node] = find(parent[node]);
            }
            return parent[node];
        }

        private boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);

            if (rootA == rootB) {
                return false;
            }

            if (size[rootA] < size[rootB]) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }

            parent[rootB] = rootA;
            size[rootA] += size[rootB];
            return true;
        }
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        Edge[] sortedEdges = buildAndSortEdges(edges);
        int baseWeight = kruskalMstWeight(n, sortedEdges, NO_EDGE, NO_EDGE);

        List<Integer> criticalEdges = new ArrayList<>();
        List<Integer> pseudoCriticalEdges = new ArrayList<>();

        for (int sortedIndex = 0; sortedIndex < sortedEdges.length; sortedIndex++) {
            Edge edge = sortedEdges[sortedIndex];

            int weightWithoutEdge = kruskalMstWeight(n, sortedEdges, sortedIndex, NO_EDGE);
            if (weightWithoutEdge > baseWeight) {
                criticalEdges.add(edge.originalIndex());
                continue;
            }

            int weightWithForcedEdge = kruskalMstWeight(n, sortedEdges, NO_EDGE, sortedIndex);
            if (weightWithForcedEdge == baseWeight) {
                pseudoCriticalEdges.add(edge.originalIndex());
            }
        }

        return List.of(criticalEdges, pseudoCriticalEdges);
    }

    private Edge[] buildAndSortEdges(int[][] edges) {
        Edge[] sortedEdges = new Edge[edges.length];

        for (int i = 0; i < edges.length; i++) {
            sortedEdges[i] = new Edge(edges[i][0], edges[i][1], edges[i][2], i);
        }

        Arrays.sort(
                sortedEdges,
                Comparator.comparingInt(Edge::weight)
                        .thenComparingInt(Edge::originalIndex)
        );

        return sortedEdges;
    }

    private int kruskalMstWeight(int n, Edge[] sortedEdges, int bannedSortedIndex, int forcedSortedIndex) {
        UnionFind unionFind = new UnionFind(n);
        int totalWeight = 0;
        int edgesUsed = 0;

        if (forcedSortedIndex != NO_EDGE) {
            Edge forcedEdge = sortedEdges[forcedSortedIndex];
            if (unionFind.union(forcedEdge.from(), forcedEdge.to())) {
                totalWeight += forcedEdge.weight();
                edgesUsed++;
            }
        }

        for (int sortedIndex = 0; sortedIndex < sortedEdges.length && edgesUsed < n - 1; sortedIndex++) {
            if (sortedIndex == bannedSortedIndex || sortedIndex == forcedSortedIndex) {
                continue;
            }

            Edge edge = sortedEdges[sortedIndex];
            if (unionFind.union(edge.from(), edge.to())) {
                totalWeight += edge.weight();
                edgesUsed++;
            }
        }

        return edgesUsed == n - 1 ? totalWeight : DISCONNECTED;
    }
}




