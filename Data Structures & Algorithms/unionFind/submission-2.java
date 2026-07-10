class UnionFind {

    private Map<Integer, Integer> parent; 
    private Map<Integer, Integer> rank; 
    private int numComponents; 

    public UnionFind(int n) {
        parent = new HashMap<>(); 
        rank = new HashMap<>(); 
        numComponents = n; 

        for (int i = 0; i < n; i++) {
            parent.put(i, i); 
            rank.put(i, 0); 
        }
    }

    public int find(int x) {
        if (parent.get(x) != x) {
            parent.put(x, find(parent.get(x))); 
        }
        return parent.get(x); 
    }

    public boolean isSameComponent(int x, int y) {
        return find(x) == find(y); 
    }

    public boolean union(int x, int y) {
        int rootX = find(x); 
        int rootY = find(y); 

        if (rootX == rootY) {
            return false;
        }

        int rankX = rank.get(rootX); 
        int rankY = rank.get(rootY); 

        if (rankX > rankY) {
            parent.put(rootY, rootX); 
        } else if (rankX < rankY) {
            parent.put(rootX, rootY); 
        } else {
            parent.put(rootY, rootX); 
            rank.put(rootX, rankX + 1); 
        }

        numComponents--; 
        return true; 
    }

    public int getNumComponents() {
        return numComponents; 
    }
}
