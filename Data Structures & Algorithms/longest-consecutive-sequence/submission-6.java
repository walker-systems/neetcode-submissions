

class Solution {
    public int longestConsecutive(int[] nums) {
        // Handle the edge case of an empty array
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        UnionFind uf = new UnionFind(nums);
        
        // Connect consecutive elements
        for (int num : nums) {
            // We only need to look "up" to connect the whole chain. 
            // Looking for num - 1 would be redundant.
            if (uf.contains(num + 1)) {
                uf.union(num, num + 1);
            }
        }
        
        return uf.getMaxSize();
    }
    
    // Custom Disjoint Set configured for this specific problem
    class UnionFind {
        private Map<Integer, Integer> parent;
        private Map<Integer, Integer> size; // Tracks the length of the sequence
        private int maxSize; // Tracks the absolute longest sequence we've built
        
        public UnionFind(int[] nums) {
            parent = new HashMap<>();
            size = new HashMap<>();
            maxSize = 1; // If there is at least one number, max size is 1
            
            // Initialize every unique number as an isolated component
            for (int num : nums) {
                if (!parent.containsKey(num)) {
                    parent.put(num, num);
                    size.put(num, 1);
                }
            }
        }
        
        public boolean contains(int num) {
            return parent.containsKey(num);
        }
        
        public int find(int x) {
            // Path Compression
            if (parent.get(x) != x) {
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }
        
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            // If they are already in the same sequence, do nothing
            if (rootX == rootY) {
                return;
            }
            
            // Union by Size (Merge the smaller sequence into the larger one)
            int sizeX = size.get(rootX);
            int sizeY = size.get(rootY);
            
            if (sizeX > sizeY) {
                parent.put(rootY, rootX);
                size.put(rootX, sizeX + sizeY); // Add the sizes together
                maxSize = Math.max(maxSize, sizeX + sizeY); // Update global max
            } else {
                parent.put(rootX, rootY);
                size.put(rootY, sizeX + sizeY); // Add the sizes together
                maxSize = Math.max(maxSize, sizeX + sizeY); // Update global max
            }
        }
        
        public int getMaxSize() {
            return maxSize;
        }
    }
}