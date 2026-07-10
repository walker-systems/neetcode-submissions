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
class Solution {

    private  Deque<TreeNode> stack; 
    private  List<Integer> result;

    public List<Integer> preorderTraversal(TreeNode root) {
        result = new ArrayList<>(); 
        stack = new ArrayDeque<>(); 
        TreeNode curr = root; 
        
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                result.add(curr.val);
                if (curr.right != null) {
                    stack.push(curr.right); 
                }
                curr = curr.left; 
            } else {
                curr = stack.pop(); 
            }
        }

        return result; 
    }

}