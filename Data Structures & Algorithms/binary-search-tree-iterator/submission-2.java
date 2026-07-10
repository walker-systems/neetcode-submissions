// import java.util.ArrayDeque;
// import java.util.Queue;

class BSTIterator {
    // 1. Queue to hold the pre-computed in-order traversal
    private final Queue<Integer> elements;

    public BSTIterator(TreeNode root) {
        elements = new ArrayDeque<>();
        // 2. Flatten the tree completely at the time of creation
        buildInorder(root);
    }
    
    public int next() {
        return elements.poll();
    }
    
    public boolean hasNext() {
        return !elements.isEmpty();
    }
    
    /**
     * Standard recursive in-order traversal (Left, Root, Right).
     */
    private void buildInorder(TreeNode node) {
        if (node == null) return;
        
        buildInorder(node.left);
        elements.add(node.val);
        buildInorder(node.right);
    }
}