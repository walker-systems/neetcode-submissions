// import java.util.ArrayList;
// import java.util.List;

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode curr = root;
        
        while (curr != null) {
            if (curr.left == null) {
                // No left subtree to process. Process current and move right.
                result.add(curr.val);
                curr = curr.right;
            } else {
                // Find the in-order predecessor
                TreeNode predecessor = curr.left;
                while (predecessor.right != null && predecessor.right != curr) {
                    predecessor = predecessor.right;
                }
                
                if (predecessor.right == null) {
                    // Preorder modification: Process the node BEFORE diving left
                    result.add(curr.val); 
                    
                    // Create the temporary thread back to the parent
                    predecessor.right = curr;
                    curr = curr.left;
                } else {
                    // The thread already exists, meaning we have returned from the left subtree.
                    // Break the thread to restore the original tree structure.
                    predecessor.right = null;
                    curr = curr.right;
                }
            }
        }
        
        return result;
    }
}


