class Graph {
    // Map<Node, Set<Neighbors>>
    private Map<Integer, Set<Integer>> adjList;

    public Graph() {
        this.adjList = new HashMap<>();
    }

    // 1. Add Edge: use 'computeIfAbsent' to create the set lazily
    public void addEdge(int src, int dst) {
        // "If src doesn't exist, create a new HashSet, put it in map, return it. Then add dst."
        adjList.computeIfAbsent(src, HashSet::new).add(dst);
        
        // Ensure 'dst' also exists in the map as a key (even if it has no neighbors yet)
        // This prevents crashes later when we look up 'dst'
        adjList.computeIfAbsent(dst, HashSet::new);
    }

    // 2. Remove Edge: Check null safely
    public boolean removeEdge(int src, int dst) {
        // We cannot use getOrDefault here because we need to modify the REAL set, 
        // not a temporary empty set.
        Set<Integer> neighbors = adjList.get(src);
        
        // If src doesn't exist (null), return false. Else, try to remove dst.
        return neighbors != null && neighbors.remove(dst);
    }

    // 3. Has Path (Recursive DFS)
    public boolean hasPath(int src, int dst) {
        // Fail fast if nodes don't exist
        if (!adjList.containsKey(src) || !adjList.containsKey(dst)) {
            return false;
        }

        Set<Integer> visited = new HashSet<>();
        return dfs(src, dst, visited);
    }

    // Private Helper Method for Recursion
    private boolean dfs(int current, int target, Set<Integer> visited) {
        // 1. Base Case: Found it!
        if (current == target) {
            return true;
        }

        // 2. Base Case: Already visited (Cycle detected)
        // visited.add() returns 'false' if the item was ALREADY in the set
        if (!visited.add(current)) {
            return false;
        }

        // 3. Recursive Step: Check all neighbors
        // Use getOrDefault to handle leaf nodes (null neighbors) safely
        for (int neighbor : adjList.getOrDefault(current, Collections.emptySet())) {
            if (dfs(neighbor, target, visited)) {
                return true; // Bubble up true if ANY path returns true
            }
        }

        return false; // Dead end
    }
}