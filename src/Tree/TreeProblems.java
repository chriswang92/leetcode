package Tree;

import sun.reflect.generics.tree.Tree;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class TreeProblems {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);
        //maxDepth(node)
        System.out.println(sortedArrayToBST(new int[]{0,1,2,3,4,5}));//-10,-3,0,5,9
    }
    //108. Convert Sorted Array to Binary Search Tree
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0 || nums == null)
            return null;
        TreeNode res = null,curr = null;
        int mid = 0;
        if (nums.length % 2 == 1)
            mid = (int)Math.ceil((double)nums.length/2) - 1;
        else
            mid = (int)Math.ceil((double)nums.length/2);
        res = new TreeNode(nums[mid]);
        curr = res;
        for (int i = mid - 1; i >= 0; i--) {
            curr.left = new TreeNode(nums[i]);
            curr = curr.left;
        }
        curr = res;
        for (int i = mid + 1; i < nums.length; i ++) {
            curr.right = new TreeNode(nums[i]);
            curr = curr.right;
        }
        return res;
    }
    //104. Maximum Depth of Binary Tree
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        //else if (root.left == null) { return maxDepth(root.right) + 1; }
        //else if (root.right == null) { return maxDepth(root.left) + 1; }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

}
