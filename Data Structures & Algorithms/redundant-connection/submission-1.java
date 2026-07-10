class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length; 
        UnionFind uf = new UnionFind(n + 1); 

        for (int[] edge : edges) {
            int u = edge[0]; 
            int v = edge[1]; 

            if (!uf.union(u, v)) {
                return edge;
            }
        }
        return new int[0]; 
    }

    private class UnionFind {
        private int[] parent; 
        private int[] rank; 

        public UnionFind(int size) {
            parent = new int[size]; 
            rank = new int[size]; 

            for (int i = 0; i < size; i++) {
                parent[i] = i; 
                rank[i] = 0; 
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); 
            }
            return parent[x]; 
        }

        public boolean union(int x, int y) {
            int rootX = find(x); 
            int rootY = find(y); 

            if (rootX == rootY) {
                return false;
            }

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
