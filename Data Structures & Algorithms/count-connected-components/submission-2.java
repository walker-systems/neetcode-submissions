class Solution {
    public int countComponents(int n, int[][] edges) {
        int count = n; 
        UnionFind uf = new UnionFind(n); 

        for (int[] edge : edges) {
            if (uf.union(edge[0], edge[1])) {
                count--; 
            }
        } 
        return count;
    }
}

class UnionFind {        

    int n; 
    int[] parents;
    int[] rank; 
    public UnionFind(int n) {
        this.n = n;
        this.parents = new int[n]; // index = vertex, value = parent
        this.rank = new int[n];    // index = vertex, value = rank 
        Arrays.fill(parents, -1);   
        Arrays.fill(rank, 1); 
    }



    public int find(int i) {
        if (parents[i] == -1) {
            parents[i] = i; 
        }

        if (parents[i] != i) {
            parents[i] = find(parents[i]); 
        }
        return parents[i]; 
    }

    public boolean union(int v1, int v2) {
        int rootV1 = find(v1); 
        int rootV2 = find(v2);

        if (rootV1 == rootV2) {
            return false;
        }

        if (rank[rootV1] > rank[rootV2]) {
            parents[rootV2] = rootV1; 
        } else if (rank[rootV2] > rank[rootV1]) {
            parents[rootV1] = rootV2; 
        } else {
            parents[rootV2] = rootV1;
            rank[rootV1]++; 
        }
        return true;
    }



}
