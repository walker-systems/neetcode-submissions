class Solution {
    public int countComponents(int n, int[][] edges) {
        int count = n; 
        UnionFind<Integer> uf = new UnionFind<>(); 
        Map<Integer, List<Integer>> mapVertexToRoot = new HashMap<>();

        for (int[] edge : edges) {
            if (uf.union(edge[0], edge[1])) {
                count--; 
            }
        } 
        return count;
    }
}

class UnionFind<T> {                                    // Main purpose: associate all items with their connected root in input order
    private Map<T, T> parent = new HashMap<>();         // parent map = (item, item's parent)
    private Map<T, Integer> rank = new HashMap<>();     // rank map = (item, item's rank)

    public T find(T x) {
        parent.putIfAbsent(x, x);                       // Initially, an element is its own parent
        rank.putIfAbsent(x, 1);                         // All elements start with rank of 1
        if (!parent.get(x).equals(x)) {                 // If an item is not its own parent...
            parent.put(x, find(parent.get(x)));             // Replace item's parent with the root (the parent of the parent of the...)                                                        
        }                                                   // result: parent map = (item, item's root)
        return parent.get(x);                           // Get the root or the item itself if it's its own parent
    }

    public boolean union(T v1, T v2) {
        T rootV1 = find(v1);                              // rootV1 = root of item X
        T rootV2 = find(v2);                              // rootV2 = root of item Y
        if (rootV1.equals(rootV2)) return false;                // if both items have the same root, no union necessary - already unified

        if (rank.get(rootV1) > rank.get(rootV2)) {        // If item X's root has a higher rank than rootV2's root...
            parent.put(rootV2, rootV1);                     // replace rootV2's parent slot with item X's root
        } else if (rank.get(rootV1) < rank.get(rootV2)) { // Else if item X's root has a lower rank than rootV2...
            parent.put(rootV1, rootV2);                     // Replace item X's parent slot with item Y's root
        } else {                                          // If same rank, 
            parent.put(rootV2, rootV1);                     // pick one to be the parent (rootV1)
            rank.put(rootV1, rank.get(rootV1) + 1);         // Increment that parent's rank (rootV1)
        }
        return true;
    }

}
