class SegmentTree {
    
    // 1. Define our Tree Node
    class Node {
        int start, end; // The range this node covers
        int sum;        // The sum of elements in this range
        Node left, right;
        
        public Node(int start, int end) {
            this.start = start;
            this.end = end;
            this.sum = 0;
        }
    }
    
    private Node root;

    // 2. Initialize the Tree
    public SegmentTree(int[] arr) {
        if (arr != null && arr.length > 0) {
            // Build the tree covering the entire array from index 0 to length-1
            root = buildTree(arr, 0, arr.length - 1);
        }
    }

    private Node buildTree(int[] arr, int start, int end) {
        Node node = new Node(start, end);
        
        // Base case: Leaf node (a single element)
        if (start == end) {
            node.sum = arr[start];
            return node;
        }
        
        // Recursive case: Divide range in half
        int mid = start + (end - start) / 2;
        node.left = buildTree(arr, start, mid);
        node.right = buildTree(arr, mid + 1, end);
        
        // The sum of this node is the sum of its two halves
        node.sum = node.left.sum + node.right.sum;
        
        return node;
    }

    // 3. Update a value
    public void update(int idx, int val) {
        updateHelper(root, idx, val);
    }

    private void updateHelper(Node node, int idx, int val) {
        // Base case: We found the exact leaf node to update
        if (node.start == node.end) {
            node.sum = val;
            return;
        }
        
        // Find which half the index lives in and recurse down
        int mid = node.start + (node.end - node.start) / 2;
        if (idx <= mid) {
            updateHelper(node.left, idx, val);
        } else {
            updateHelper(node.right, idx, val);
        }
        
        // Phase 2: The Unwind! Update this node's sum on the way back up
        node.sum = node.left.sum + node.right.sum;
    }

    // 4. Query a range
    public int query(int l, int r) {
        return queryHelper(root, l, r);
    }

    private int queryHelper(Node node, int start, int end) {
        // Base case: The node's range perfectly matches our target range
        if (node.start == start && node.end == end) {
            return node.sum;
        }
        
        int mid = node.start + (node.end - node.start) / 2;
        
        // Scenario A: The target range is entirely in the left child
        if (end <= mid) {
            return queryHelper(node.left, start, end);
        } 
        // Scenario B: The target range is entirely in the right child
        else if (start > mid) {
            return queryHelper(node.right, start, end);
        } 
        // Scenario C: The range spans across both children! Split the query.
        else {
            int leftSum = queryHelper(node.left, start, mid);
            int rightSum = queryHelper(node.right, mid + 1, end);
            return leftSum + rightSum;
        }
    }
}