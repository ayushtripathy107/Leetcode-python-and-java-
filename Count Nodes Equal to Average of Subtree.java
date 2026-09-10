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
    private int matchingNodesCount = 0;

    public int averageOfSubtree(TreeNode root) {
        matchingNodesCount = 0;
        calculateSumAndCount(root);
        return matchingNodesCount;
    }

    // Helper function that returns an array: [sum, count] of the subtree
    private int[] calculateSumAndCount(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        // Post-order traversal: visit left and right subtrees first
        int[] leftResult = calculateSumAndCount(node.left);
        int[] rightResult = calculateSumAndCount(node.right);

        // Calculate total sum and total node count for the current subtree
        int totalSum = leftResult[0] + rightResult[0] + node.val;
        int totalCount = leftResult[1] + rightResult[1] + 1;

        // Integer division automatically rounds down to the nearest integer
        if (totalSum / totalCount == node.val) {
            matchingNodesCount++;
        }

        return new int[]{totalSum, totalCount};
    }
}
