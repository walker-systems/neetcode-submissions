class Solution {

    public int longestConsecutive(int[] nums) {

        if (nums.length < 1) {
            return 0; 
        }

        UnionFind uf = new UnionFind(); 
        Set<Integer> numSet = new HashSet<>(); 
        int maxSize = 1; 

        for (int n : nums) {
            numSet.add(n); 
        }

        for (int n : numSet) {
            if (numSet.contains(n + 1)) {
                maxSize = Math.max(maxSize, uf.union(n, n + 1)); 
            }
        }

        return maxSize; 
    }

    private static class UnionFind {

        private Map<Integer, Integer> parent = new HashMap<>(); 
        private Map<Integer, Integer> size = new HashMap<>(); 

        public int find(int x) {
            parent.putIfAbsent(x, x); 
            size.putIfAbsent(x, 1); 

            if (parent.get(x) != x) {
                parent.put(x, find(parent.get(x))); 
            }
            return parent.get(x); 
        }

        public int union(int x, int y) {
            int rootX = find(x); 
            int rootY = find(y); 
            
            if (rootX != rootY) {
                if (size.get(rootX) > size.get(rootY)) {
                    parent.put(rootY, rootX); 
                    size.put(rootX, size.get(rootX) + size.get(rootY)); 
                    return size.get(rootX);
                } else {
                    parent.put(rootX, rootY); 
                    size.put(rootY, size.get(rootY) + size.get(rootX)); 
                    return size.get(rootY);
                }
            } else {
                return 1;
            }
        }
    }
}
