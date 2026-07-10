// import java.util.ArrayDeque;
// import java.util.ArrayList;
// import java.util.List;

class Solution {
    public List<Integer> topologicalSort(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int vertex = 0; vertex < n; vertex++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[n];

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
            indegree[to]++;
        }

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int vertex = 0; vertex < n; vertex++) {
            if (indegree[vertex] == 0) {
                queue.offer(vertex);
            }
        }

        List<Integer> topologicalOrder = new ArrayList<>(n);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            topologicalOrder.add(current);

            for (int neighbor : graph.get(current)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return topologicalOrder.size() == n ? topologicalOrder : List.of();
    }
}


