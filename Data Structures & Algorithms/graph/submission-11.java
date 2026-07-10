

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

    // 3. Has Path (BFS): use 'getOrDefault' for safe loops
    public boolean hasPath(int src, int dst) {
        // Fast fail if nodes are missing entirely
        if (!adjList.containsKey(src) || !adjList.containsKey(dst)) {
            return false;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        
        queue.add(src);
        visited.add(src);
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            if (current == dst) return true;
            
            // KEY FIX: Use getOrDefault to return an empty set if 'current' has no neighbors.
            // This prevents the NullPointerException if 'current' is a leaf node.
            for (int neighbor : adjList.getOrDefault(current, Collections.emptySet())) {
                if (visited.add(neighbor)) { // .add() returns true if it was NOT present
                    queue.add(neighbor);
                }
            }
        }
        
        return false;
    }
}