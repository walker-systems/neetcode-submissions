class Solution {
    public int countComponents(int n, int[][] edges) {
        // Initialize Union-Find with n nodes (0 to n-1)
        UnionFind uf = new UnionFind(n);
        
        // Process each edge
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }
        
        // Return the final number of isolated groups
        return uf.getComponents();
    }
    
    // Internal Disjoint Set Class
    private class UnionFind {
        private int[] parent;
        private int[] rank;
        private int components;
        
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            components = n; // Start with n isolated components
            
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }
        
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }
        
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            // If they are already in the same component, do nothing
            if (rootX == rootY) {
                return; 
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
            
            // We successfully merged two groups, so the total count drops by 1
            components--;
        }
        
        public int getComponents() {
            return components;
        }
    }
}



