class Graph {

    private Map<Integer, Set<Integer>> adjList; 

    public Graph() {
        this.adjList = new HashMap<>(); 
    }

    public void addEdge(int src, int dst) {
        adjList.computeIfAbsent(src, HashSet::new).add(dst); 
        adjList.computeIfAbsent(dst, HashSet::new);
    }

    public boolean removeEdge(int src, int dst) {
        Set<Integer> neighbors = adjList.get(src); 
        return neighbors != null && neighbors.remove(dst); 
    }

    public boolean hasPath(int src, int dst) {
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

            for (int neighbor : adjList.getOrDefault(current, Collections.emptySet())) {
                if (visited.add(neighbor)) {
                    queue.add(neighbor); 
                }
            }
        }
        return false; 
    }
}
