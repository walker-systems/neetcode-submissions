class Solution {
    public int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n); 

        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]); 
        }

        return uf.getComponents(); 
    }

    private static class UnionFind {
        private int[] parent; 
        private int[] rank; 
        private int components; 

        public UnionFind(int n) {
            parent = new int[n]; 
            rank = new int[n]; 
            components = n; 

            for (int i = 0; i < n; i++) {
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

        public void union(int x, int y) {
            int rootX = find(x); 
            int rootY = find(y);

            if (rootX == rootY) {
                return;
            }

            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX; 
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY; 
            } else {
                parent[rootY] = rootX;
                rank[rootX]++; 
            }
            components--;
        }

        public int getComponents() {
            return components;
        }
    }
}
