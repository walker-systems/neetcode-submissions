// import java.util.ArrayDeque;
// import java.util.Deque;

class BSTIterator {
    // Declared as final to prevent reassignment after initialization
    private final Deque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        // Initialize the stack with the path to the smallest element
        pushAllLeft(root);
    }
    
    public int next() {
        // The top of the stack is strictly the next smallest element
        TreeNode curr = stack.pop();
        
        // If the node has a right subtree, we must prepare the 
        // next smallest elements from that branch
        if (curr.right != null) {
            pushAllLeft(curr.right);
        }
        
        return curr.val;
    }
    
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    
    /**
     * Helper method to aggressively push all left children onto the stack.
     * This ensures the smallest element of the current subtree sits at the top.
     */
    private void pushAllLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}