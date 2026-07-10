class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        // The problem states there are 'n' nodes and 'n' edges.
        // Nodes are 1-indexed (1 to n), so we make our arrays size n + 1.
        int n = edges.length;
        UnionFind uf = new UnionFind(n + 1);
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            
            // If union returns false, it means u and v were ALREADY connected.
            // Adding this edge creates a cycle! This is our redundant edge.
            if (!uf.union(u, v)) {
                return edge;
            }
        }
        
        return new int[0]; // Should never be reached based on problem constraints
    }
    
    // Our trusty Disjoint Set class (embedded as an inner class for LeetCode)
    private class UnionFind {
        private int[] parent;
        private int[] rank;
        
        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            // Initialize: every node is its own boss
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }
        
        public int find(int x) {
            if (parent[x] != x) {
                // Path compression
                parent[x] = find(parent[x]); 
            }
            return parent[x];
        }
        
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            // The magic check: Are they already in the same family?
            if (rootX == rootY) {
                return false; 
            }
            
            // Union by Rank
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            
            return true;
        }
    }
}


