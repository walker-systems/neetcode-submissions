

class Graph {
    // Adjacency List: Map<Node, Set<Neighbors>>
    // We use a HashSet for neighbors to make removeEdge() O(1) instead of O(N)
    private Map<Integer, Set<Integer>> adjList;

    public Graph() {
        this.adjList = new HashMap<>();
    }

    // 1. Add Edge - O(1)
    public void addEdge(int src, int dst) {
        // Ensure both vertices exist in the graph first
        adjList.putIfAbsent(src, new HashSet<>());
        adjList.putIfAbsent(dst, new HashSet<>());
        
        // Add the directed edge src -> dst
        // HashSet automatically handles the "no multiple edges" constraint
        adjList.get(src).add(dst);
    }

    // 2. Remove Edge - O(1)
    public boolean removeEdge(int src, int dst) {
        // If source or dest doesn't exist, we can't remove an edge
        if (!adjList.containsKey(src) || !adjList.containsKey(dst)) {
            return false;
        }
        
        // .remove() returns true if the item was present, false otherwise
        return adjList.get(src).remove(dst);
    }

    // 3. Has Path - O(V + E) using BFS
    public boolean hasPath(int src, int dst) {
        // Edge Case: If start/end nodes don't exist in our map
        if (!adjList.containsKey(src) || !adjList.containsKey(dst)) {
            return false;
        }
        
        // BFS Setup
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        
        queue.add(src);
        visited.add(src);
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            // Found the target?
            if (current == dst) {
                return true;
            }
            
            // Explore neighbors
            for (int neighbor : adjList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        
        return false;
    }
}