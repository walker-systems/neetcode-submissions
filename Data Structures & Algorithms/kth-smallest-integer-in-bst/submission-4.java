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
    public int kthSmallest(TreeNode root, int k) {
        int[] temp = new int[2];
        temp[0] = k;
        dfs(root, temp); 
        return temp[1];
    }
    private void dfs(TreeNode node, int[] temp) {
        if (node == null) {
            return;
        }

        dfs(node.left, temp); 
        temp[0]--; 
        if (temp[0] == 0) {
            temp[1] = node.val;
            return;
        }
        dfs(node.right, temp);
    }
}
