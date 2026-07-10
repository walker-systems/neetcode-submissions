// import java.util.ArrayDeque;
// import java.util.Deque;
// import java.util.LinkedList;
// import java.util.List;

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        // LinkedList allows O(1) insertions at the front
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) return result;
        
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            
            // Add to the FRONT of the list to implicitly reverse the output
            result.addFirst(curr.val);
            
            // Push Left first, then Right (the opposite of standard Preorder)
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
        }
        
        return result;
    }
}