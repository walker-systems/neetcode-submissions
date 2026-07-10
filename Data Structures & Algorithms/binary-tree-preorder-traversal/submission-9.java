/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
// import java.util.ArrayDeque;
// import java.util.ArrayList;
// import java.util.Deque;
// import java.util.List;

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        // ArrayDeque is the modern, thread-unsafe, high-performance stack implementation
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            result.add(curr.val); // 1. Process Root
            
            // 2. Push Right (so it is processed last)
            if (curr.right != null) {
                stack.push(curr.right);
            }
            // 3. Push Left (so it sits at the top and is processed next)
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
        
        return result;
    }
}

