// import java.util.ArrayDeque;
// import java.util.ArrayList;
// import java.util.Deque;
// import java.util.List;

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // ArrayDeque is the optimal, thread-unsafe, high-performance stack
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        TreeNode lastVisited = null;
        
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                // 1. Dive as far left as possible
                stack.push(curr);
                curr = curr.left;
            } else {
                // 2. We hit the bottom. Peek at the parent node.
                TreeNode peekNode = stack.peek();
                
                // 3. If a right child exists AND we haven't visited it yet, pivot right
                if (peekNode.right != null && lastVisited != peekNode.right) {
                    curr = peekNode.right;
                } else {
                    // 4. Both subtrees are fully processed. Safe to record the node.
                    result.add(peekNode.val);
                    lastVisited = stack.pop();
                }
            }
        }
        
        return result;
    }
}

